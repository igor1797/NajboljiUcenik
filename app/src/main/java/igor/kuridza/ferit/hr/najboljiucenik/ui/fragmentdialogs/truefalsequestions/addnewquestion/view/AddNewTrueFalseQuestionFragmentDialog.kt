package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.addnewquestion.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.base.BaseFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.addnewquestion.viewmodel.AddNewTrueFalseQuestionViewModel
import kotlinx.android.synthetic.main.fragment_add_new_true_false_question_dialog.*

@AndroidEntryPoint
class AddNewTrueFalseQuestionFragmentDialog: BaseFragmentDialog() {

    private val addNewTrueFalseQuestionViewModel: AddNewTrueFalseQuestionViewModel by lazy {
        ViewModelProvider(this).get(AddNewTrueFalseQuestionViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_true_false_question_dialog

    override fun initUI() {
        setSpinnerOptions()
        btnAddTrueFalseQuestion.onClick {
            saveMathQuestion()
            dismiss()
        }
        observeState()
        handleInputChanges()
    }

    private fun setSpinnerOptions(){
        trueFalseCategoryTypeSpinner.setOptions(context!!, GEOGRAPHY_TRUE_FALSE, CROATIAN_TRUE_FALSE)
        newTrueFalseAnswerSpinner.setTrueFalseOptions(context!!, option1 = true, option2 = false)
    }

    private fun saveMathQuestion(){
        addNewTrueFalseQuestionViewModel.addNewTrueFalseQuestion(
            newTrueFalseQuestion.text.toString(),
            newTrueFalseAnswerSpinner.getTrueFalseSelection(),
            trueFalseCategoryTypeSpinner.getTrueFalseSelectedCategoryGame()
        )
    }

    private fun observeState(){
        addNewTrueFalseQuestionViewModel.addTrueFalseQuestionFormState.observe(this, Observer {
            val addNewTrueFalseQuestionState = it ?: return@Observer

            btnAddTrueFalseQuestion.isEnabled = addNewTrueFalseQuestionState.isDataValid

            if(addNewTrueFalseQuestionState.questionError != null){
                newTrueFalseQuestion.error = getString(addNewTrueFalseQuestionState.questionError)
            }
        })
    }

    private fun handleInputChanges(){
        newTrueFalseQuestion.afterTextChanged {
            addNewTrueFalseQuestionViewModel.newTrueFalseQuestionDataChanged(
                newTrueFalseQuestion.text.toString()
            )
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = AddNewTrueFalseQuestionFragmentDialog()
    }
}