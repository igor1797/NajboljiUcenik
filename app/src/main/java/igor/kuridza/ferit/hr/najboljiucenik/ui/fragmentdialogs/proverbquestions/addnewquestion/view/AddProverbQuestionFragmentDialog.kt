package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.addnewquestion.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.afterTextChanged
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.base.BaseFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.addnewquestion.viewmodel.AddProverbQuestionViewModel
import kotlinx.android.synthetic.main.fragment_add_proverb_question_dialog.*

@AndroidEntryPoint
class AddProverbQuestionFragmentDialog : BaseFragmentDialog() {

    private val addProverbQuestionsViewModel: AddProverbQuestionViewModel by lazy {
        ViewModelProvider(this).get(AddProverbQuestionViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_proverb_question_dialog

    override fun initUI() {
        handleInputChanges()
        observeState()
        btnAddNewProverbQuestion.onClick {
            saveProverbQuestion()
            dismiss()
        }
    }

    private fun observeState(){
        addProverbQuestionsViewModel.addProverbQuestionFormState.observe(this, Observer {
            val addNewProverbQuestionState = it ?: return@Observer

            btnAddNewProverbQuestion.isEnabled = addNewProverbQuestionState.isDataValid

            if(addNewProverbQuestionState.questionError != null){
                newProverbQuestion.error = getString(addNewProverbQuestionState.questionError)
            }

            if(addNewProverbQuestionState.answerError != null){
                newProverbAnswer.error = getString(addNewProverbQuestionState.answerError)
            }

            if(addNewProverbQuestionState.lettersError != null){
                newProverbLetters.error = getString(addNewProverbQuestionState.lettersError)
            }
        })
    }

    private fun saveProverbQuestion(){
        addProverbQuestionsViewModel.addNewProverbQuestion(
            newProverbQuestion.text.toString(),
            newProverbAnswer.text.toString().toLowerCase(),
            newProverbLetters.text.toString().toLowerCase()
        )
    }

    private fun handleInputChanges(){
        newProverbQuestion.afterTextChanged {
            addProverbQuestionsViewModel.newProverbQuestionDataChanged(
                newProverbQuestion.text.toString(),
                newProverbAnswer.text.toString(),
                newProverbLetters.text.toString()
            )
        }

        newProverbAnswer.afterTextChanged {
            addProverbQuestionsViewModel.newProverbQuestionDataChanged(
                newProverbQuestion.text.toString(),
                newProverbAnswer.text.toString(),
                newProverbLetters.text.toString()
            )
        }

        newProverbLetters.afterTextChanged {
            addProverbQuestionsViewModel.newProverbQuestionDataChanged(
                newProverbQuestion.text.toString(),
                newProverbAnswer.text.toString(),
                newProverbLetters.text.toString()
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddProverbQuestionFragmentDialog()
    }
}