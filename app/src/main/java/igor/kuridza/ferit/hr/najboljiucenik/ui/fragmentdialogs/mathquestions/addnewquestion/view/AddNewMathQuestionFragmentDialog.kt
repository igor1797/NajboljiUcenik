package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.addnewquestion.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.base.BaseFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.addnewquestion.viewmodel.AddNewMathQuestionViewModel
import kotlinx.android.synthetic.main.fragment_add_new_math_question_dialog.*
import kotlinx.android.synthetic.main.fragment_edit_math_question_dialog.newMathAnswer
import kotlinx.android.synthetic.main.fragment_edit_math_question_dialog.newMathQuestion


@AndroidEntryPoint
class AddNewMathQuestionFragmentDialog : BaseFragmentDialog() {

    private val addNewMathQuestionViewModel: AddNewMathQuestionViewModel by lazy {
        ViewModelProvider(this).get(AddNewMathQuestionViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_new_math_question_dialog

    override fun initUI() {
        setSpinnerOptions()
        btnAddMathQuestion.onClick {
            saveMathQuestion()
            dismiss()
        }

        observeState()
        handleInputChanges()
    }

    private fun setSpinnerOptions(){
        mathCategoryTypeSpinner.setOptions(context!!, ADDITION_SUBTRACTION, DIVISION_MULTIPLICATION)
    }

    private fun saveMathQuestion(){
        addNewMathQuestionViewModel.addNewMathQuestion(
            newMathQuestion.text.toString(),
            newMathAnswer.text.toString(),
            mathCategoryTypeSpinner.getMathSelectedCategoryGame()
        )
    }

    private fun observeState(){
        addNewMathQuestionViewModel.addMathQuestionFormState.observe(this, Observer {
            val addNewMathQuestionState = it ?: return@Observer

            btnAddMathQuestion.isEnabled = addNewMathQuestionState.isDataValid

            if(addNewMathQuestionState.questionError != null){
                newMathQuestion.error = getString(addNewMathQuestionState.questionError)
            }

            if(addNewMathQuestionState.answerError != null){
                newMathAnswer.error = getString(addNewMathQuestionState.answerError)
            }
        })
    }

    private fun handleInputChanges(){
        newMathQuestion.afterTextChanged {
            addNewMathQuestionViewModel.newMathQuestionDataChanged(
                newMathQuestion.text.toString(),
                newMathAnswer.text.toString()
            )
        }

        newMathAnswer.afterTextChanged {
            addNewMathQuestionViewModel.newMathQuestionDataChanged(
                newMathQuestion.text.toString(),
                newMathAnswer.text.toString()
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddNewMathQuestionFragmentDialog()
    }
}