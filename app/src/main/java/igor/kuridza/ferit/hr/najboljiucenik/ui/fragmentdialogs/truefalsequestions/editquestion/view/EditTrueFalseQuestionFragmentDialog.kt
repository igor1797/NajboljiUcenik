package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.editquestion.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.base.BaseFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.editquestion.viewodel.EditTrueFalseQuestionViewModel
import kotlinx.android.synthetic.main.fragment_add_new_true_false_question_dialog.newTrueFalseAnswerSpinner
import kotlinx.android.synthetic.main.fragment_add_new_true_false_question_dialog.trueFalseCategoryTypeSpinner
import kotlinx.android.synthetic.main.fragment_edit_true_false_question_dialog.*

@AndroidEntryPoint
class EditTrueFalseQuestionFragmentDialog: BaseFragmentDialog() {

    private lateinit var documentSnapshot: DocumentSnapshot
    private lateinit var trueFalseQuestion: QuestionTrueFalse

    private val editTrueFalseQuestionViewModel: EditTrueFalseQuestionViewModel by lazy {
        ViewModelProvider(this).get(
            EditTrueFalseQuestionViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_edit_true_false_question_dialog

    override fun initUI() {
        setSpinnerOptions()
        setValues()
        observeState()
        handleInputChanges()
        btnEditTrueFalseQuestion.onClick {
            if (areInputsChanged()) {
                editTrueFalseQuestion()
                dismiss()
            } else
                dismiss()
        }
    }

    private fun areInputsChanged(): Boolean{
        val newTrueFalseQuestion = editTrueFalseQuestion.text.toString()
        val newTrueFalseAnswer =  newTrueFalseAnswerSpinner.getTrueFalseSelection()
        val newTrueFalseCategoryGame = trueFalseCategoryTypeSpinner.getTrueFalseSelectedCategoryGame()
        return !(newTrueFalseQuestion == trueFalseQuestion.question && newTrueFalseAnswer == trueFalseQuestion.answer && newTrueFalseCategoryGame == trueFalseQuestion.categoryGame)
    }

    fun setDocumentSnapshot(documentSnapshot: DocumentSnapshot){
        this.documentSnapshot = documentSnapshot
        this.trueFalseQuestion = documentSnapshot.toObject(QuestionTrueFalse::class.java)!!
    }

    private fun setValues(){
        editTrueFalseQuestion.setText(trueFalseQuestion.question)
        trueFalseCategoryTypeSpinner.setTrueFalseSelectedCategoryGame(trueFalseQuestion.categoryGame)
        newTrueFalseAnswerSpinner.setTrueFalseSelection(trueFalseQuestion.answer)
    }

    private fun setSpinnerOptions(){
        trueFalseCategoryTypeSpinner.setOptions(context!!, GEOGRAPHY_TRUE_FALSE, CROATIAN_TRUE_FALSE)
        newTrueFalseAnswerSpinner.setTrueFalseOptions(context!!, option1 = true, option2 = false)
    }

    private fun editTrueFalseQuestion(){
        editTrueFalseQuestionViewModel.editTrueFalseQuestion(
            editTrueFalseQuestion.text.toString(),
            newTrueFalseAnswerSpinner.getTrueFalseSelection(),
            trueFalseCategoryTypeSpinner.getTrueFalseSelectedCategoryGame(),
            documentSnapshot
        )
    }

    private fun observeState(){
        editTrueFalseQuestionViewModel.editTrueFalseQuestionFormState.observe(this, Observer {
            val editTrueFalseQuestionState = it ?: return@Observer

            btnEditTrueFalseQuestion.isEnabled = editTrueFalseQuestionState.isDataValid

            if(editTrueFalseQuestionState.questionError != null){
                editTrueFalseQuestion.error = getString(editTrueFalseQuestionState.questionError)
            }
        })
    }

    private fun handleInputChanges(){
        editTrueFalseQuestion.afterTextChanged {
            editTrueFalseQuestionViewModel.editTrueFalseQuestionDataChanged(
                editTrueFalseQuestion.text.toString()
            )
        }
    }


    companion object{
        @JvmStatic
        fun newInstance() = EditTrueFalseQuestionFragmentDialog()
    }
}