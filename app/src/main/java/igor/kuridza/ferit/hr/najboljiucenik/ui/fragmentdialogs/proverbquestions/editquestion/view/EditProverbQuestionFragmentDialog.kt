package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.editquestion.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.afterTextChanged
import igor.kuridza.ferit.hr.najboljiucenik.common.onClick
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.base.BaseFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.editquestion.viewmodel.EditProverbQuestionViewModel
import kotlinx.android.synthetic.main.fragment_edit_proverb_question_dialog.*


@AndroidEntryPoint
class EditProverbQuestionFragmentDialog : BaseFragmentDialog() {

    private lateinit var proverbQuestion: ProverbQuestion
    private lateinit var documentSnapshot: DocumentSnapshot

    private val editProverbQuestionViewModel: EditProverbQuestionViewModel by lazy {
        ViewModelProvider(this).get(EditProverbQuestionViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_edit_proverb_question_dialog

    override fun initUI() {
        setInputs()
        setBtnSaveChangesOnClickListener()
        observeState()
        handleInputChanges()
    }

    fun setDocumentSnapshot(documentSnapshot: DocumentSnapshot){
        this.documentSnapshot = documentSnapshot
        this.proverbQuestion = documentSnapshot.toObject(ProverbQuestion::class.java)!!
    }

    private fun setInputs(){
        newProverbQuestion.setText(proverbQuestion.question)
        newProverbAnswer.setText(proverbQuestion.answer)
        newProverbLetters.setText(proverbQuestion.letters)
    }

    private fun setBtnSaveChangesOnClickListener(){
        btnEditProverbQuestion.onClick {
            if (areInputsChanged()) {
                saveProverbQuestionChanges()
                dismiss()
            } else
                dismiss()
        }
    }

    private fun areInputsChanged(): Boolean{
        val newProverbQuestion = newProverbQuestion.text.toString()
        val newProverbAnswer = newProverbAnswer.text.toString()
        val newProverbLetters = newProverbLetters.text.toString()
        return !(newProverbQuestion == proverbQuestion.question && newProverbAnswer == proverbQuestion.answer && newProverbLetters == proverbQuestion.letters)
    }

    private fun saveProverbQuestionChanges(){
        editProverbQuestionViewModel.editProverbQuestion(
            newProverbQuestion.text.toString(),
            newProverbAnswer.text.toString().toLowerCase(),
            newProverbLetters.text.toString().toLowerCase(),
            documentSnapshot
        )
    }

    private fun observeState(){
        editProverbQuestionViewModel.editProverbQuestionForm.observe(this, Observer {
            val editProverbQuestionState = it ?: return@Observer

            btnEditProverbQuestion.isEnabled = editProverbQuestionState.isDataValid

            if(editProverbQuestionState.questionError != null){
                newProverbQuestion.error = getString(editProverbQuestionState.questionError)
            }

            if(editProverbQuestionState.answerError != null){
                newProverbAnswer.error = getString(editProverbQuestionState.answerError)
            }

            if(editProverbQuestionState.lettersError != null){
                newProverbLetters.error = getString(editProverbQuestionState.lettersError)
            }
        })
    }

    private fun handleInputChanges(){
        newProverbQuestion.afterTextChanged {
            editProverbQuestionViewModel.editProverbQuestionDataChanged(
                newProverbQuestion.text.toString(),
                newProverbAnswer.text.toString(),
                newProverbLetters.text.toString()
            )
        }

        newProverbAnswer.afterTextChanged {
            editProverbQuestionViewModel.editProverbQuestionDataChanged(
                newProverbQuestion.text.toString(),
                newProverbAnswer.text.toString(),
                newProverbLetters.text.toString()
            )
        }

        newProverbLetters.afterTextChanged {
            editProverbQuestionViewModel.editProverbQuestionDataChanged(
                newProverbQuestion.text.toString(),
                newProverbAnswer.text.toString(),
                newProverbLetters.text.toString()
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EditProverbQuestionFragmentDialog()
    }
}