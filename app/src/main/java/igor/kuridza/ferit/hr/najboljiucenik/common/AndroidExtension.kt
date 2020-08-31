package igor.kuridza.ferit.hr.najboljiucenik.common

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import igor.kuridza.ferit.hr.najboljiucenik.R

fun RecyclerView.setDivider(){
    val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
    divider.setDrawable(ContextCompat.getDrawable(this.context, R.drawable.recyclerview_divider)!!)
    this.addItemDecoration(divider)
}

fun String.replaceAt(position: Int, letter: Char): String{
    val stringBuilder = StringBuilder(this)
    stringBuilder[position] = letter
    return stringBuilder.toString()
}

fun String.connect(string2: String): String{
    val stringBuilder = StringBuilder(this)
    stringBuilder.append(string2)
    return stringBuilder.toString()
}

fun FragmentActivity.showFragment(containerId: Int, fragment: Fragment, shouldAddToBackStack: Boolean = false, tag: String = ""){
    supportFragmentManager.beginTransaction().apply {
        if (shouldAddToBackStack)
            addToBackStack(tag)
    }
        .replace(containerId, fragment).commitAllowingStateLoss()
}

fun Spinner.setOptions(context: Context, option1: String, option2: String){
    val list = arrayListOf(option1, option2)
    val spinnerAdapter = ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, list)
    this.adapter = spinnerAdapter
}

fun Spinner.setTrueFalseOptions(context: Context, option1: Boolean, option2: Boolean){
    val list = arrayListOf(option1, option2)
    val spinnerAdapter = ArrayAdapter<Boolean>(context, R.layout.support_simple_spinner_dropdown_item, list)
    this.adapter = spinnerAdapter
}

fun Spinner.getMathSelectedCategoryGame(): String{
    return when(this.selectedItemPosition){
        0 -> ADDITION_SUBTRACTION
        else -> DIVISION_MULTIPLICATION
    }
}

fun Spinner.setMathSelectedCategoryGame(categoryGame: String){
    val position =  when(categoryGame){
        ADDITION_SUBTRACTION -> 0
        else -> 1
    }
    this.setSelection(position)
}

fun Spinner.getTrueFalseSelectedCategoryGame(): String{
    return when(this.selectedItemPosition){
        0 -> GEOGRAPHY_TRUE_FALSE
        else -> CROATIAN_TRUE_FALSE
    }
}

fun Spinner.setTrueFalseSelectedCategoryGame(categoryGame: String){
    val position =  when(categoryGame){
        GEOGRAPHY_TRUE_FALSE -> 0
        else -> 1
    }
    this.setSelection(position)
}

fun Spinner.getTrueFalseSelection(): Boolean{
    return when(this.selectedItemPosition){
        0 -> true
        else -> false
    }
}

fun Spinner.setTrueFalseSelection(trueFalseSelection: Boolean){
    val position =  when(trueFalseSelection){
        true -> 0
        else -> 1
    }
    this.setSelection(position)
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}



