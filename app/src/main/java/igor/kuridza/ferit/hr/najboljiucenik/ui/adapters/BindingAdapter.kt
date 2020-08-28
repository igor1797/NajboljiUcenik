package igor.kuridza.ferit.hr.najboljiucenik.ui.adapters



import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import igor.kuridza.ferit.hr.najboljiucenik.R

object BindingAdapter {
    /**
     *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
     *  whenever any of the attribute changes.
     */
    @BindingAdapter(value = ["app:progressScaled", "android:max", "android:numberOfQuestion"], requireAll = true)
    @JvmStatic fun setProgress(progressBar: ProgressBar, currentNumberOfQuestion: Int, max: Int, numberOfQuestion: Int) {
        progressBar.progress = (currentNumberOfQuestion*max/(numberOfQuestion))
    }

    @BindingAdapter("app:visibility")
    @JvmStatic fun setVisibility(view: View, value: Char) {
        view.visibility = if (value == ' ') View.INVISIBLE else View.VISIBLE
    }

    @BindingAdapter("app:visibility")
    @JvmStatic fun setVisibility(view: View, size: Int) {
        view.visibility = if (size == 0) View.INVISIBLE else View.VISIBLE
    }


    @BindingAdapter("app:countDownTextColor")
    @JvmStatic fun setColor(view: TextView, remainingTime: Long) {
        if(remainingTime<=3L){
            view.setTextColor(ContextCompat.getColor(view.context, R.color.colorRed))
        }else{
            view.setTextColor(ContextCompat.getColor(view.context, R.color.colorBlack))
        }
    }
}