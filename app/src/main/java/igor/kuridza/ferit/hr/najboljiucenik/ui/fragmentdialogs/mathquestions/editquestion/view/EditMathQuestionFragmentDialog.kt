package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.editquestion.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.common.*
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.base.BaseFragmentDialog
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.editquestion.viewmodel.EditMathQuestionViewModel
import kotlinx.android.synthetic.main.fragment_edit_math_question_dialog.*
import kotlinx.android.synthetic.main.fragment_edit_math_question_dialog.newMathAnswer
import kotlinx.android.synthetic.main.fragment_edit_math_question_dialog.newMathQuestion

@AndroidEntryPoint
class EditMathQuestionFragmentDialog : BaseFragmentDialog() {

    private lateinit var mathQuestion: MathQuestion
    private lateinit var documentSnapshot: DocumentSnapshot


    private val editMathQuestionViewModel: EditMathQuestionViewModel by lazy {
        ViewModelProvider(this).get(EditMathQuestionViewModel::class.java)
    }

    override fun getLayoutResourceId(): Int = R.layout.fragment_edit_math_question_dialog

    override fun initUI() {
        setSpinnerOptions()
        setInputs()
        setBtnSaveChangesOnClickListener()
        observeState()
        handleInputChanges()
    }

    fun setDocumentSnapshot(documentSnapshot: DocumentSnapshot){
        this.documentSnapshot = documentSnapshot
        this.mathQuestion = documentSnapshot.toObject(MathQuestion::class.java)!!
    }

    private fun setInputs(){
        newMathQuestion.setText(mathQuestion.question)
        newMathAnswer.setText(mathQuestion.answer)
        editMathCategoryTypeSpinner.setMathSelectedCategoryGame(mathQuestion.categoryGame)
    }

    private fun setBtnSaveChangesOnClickListener(){
        btnSaveChangesForMathQuestion.onClick {
            if (areInputsChanged()) {
                saveMathQuestionChanges()
                dismiss()
            } else
                dismiss()
        }
    }

    private fun areInputsChanged(): Boolean{
        val newMathQuestion = newMathQuestion.text.toString()
        val newMathAnswer = newMathAnswer.text.toString()
        val newMathCategoryGame = editMathCategoryTypeSpinner.getMathSelectedCategoryGame()
        return !(newMathQuestion == mathQuestion.question && newMathAnswer == mathQuestion.answer && newMathCategoryGame == mathQuestion.categoryGame)
    }

    private fun setSpinnerOptions(){
        editMathCategoryTypeSpinner.setOptions(context!!, ADDITION_SUBTRACTION, DIVISION_MULTIPLICATION)
    }

    private fun saveMathQuestionChanges(){
        editMathQuestionViewModel.editMathQuestion(
            newMathQuestion.text.toString(),
            newMathAnswer.text.toString(),
            editMathCategoryTypeSpinner.getMathSelectedCategoryGame(),
            documentSnapshot
        )
    }

    private fun observeState(){
        editMathQuestionViewModel.editMathQuestionForm.observe(this, Observer {
            val editMathQuestionState = it ?: return@Observer

            btnSaveChangesForMathQuestion.isEnabled = editMathQuestionState.isDataValid

            if(editMathQuestionState.questionError != null){
                newMathQuestion.error = getString(editMathQuestionState.questionError)
            }

            if(editMathQuestionState.answerError != null){
                newMathAnswer.error = getString(editMathQuestionState.answerError)
            }
        })
    }

    private fun handleInputChanges(){
        newMathQuestion.afterTextChanged {
            editMathQuestionViewModel.editMathQuestionDataChanged(
                newMathQuestion.text.toString(),
                newMathAnswer.text.toString()
            )
        }

        newMathAnswer.afterTextChanged {
            editMathQuestionViewModel.editMathQuestionDataChanged(
                newMathQuestion.text.toString(),
                newMathAnswer.text.toString()
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = EditMathQuestionFragmentDialog()
    }
}