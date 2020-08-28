package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.addnewquestion.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.model.MathQuestionFormState

class AddNewMathQuestionViewModel @ViewModelInject constructor(
    private val firebaseMathInteractor: FirebaseMathInteractor
): ViewModel(){

    private val _addNewMathQuestionForm = MutableLiveData<MathQuestionFormState>()
    val addMathQuestionFormState: LiveData<MathQuestionFormState> = _addNewMathQuestionForm

    fun newMathQuestionDataChanged(question: String, answer: String){
        when {
            question.isEmpty() -> {
                _addNewMathQuestionForm.value = MathQuestionFormState(questionError = R.string.fieldCantBeEmptyText)
            }
            answer.isEmpty() -> {
                _addNewMathQuestionForm.value = MathQuestionFormState(answerError = R.string.fieldCantBeEmptyText)
            }
            else -> {
                _addNewMathQuestionForm.value = MathQuestionFormState(isDataValid = true)
            }
        }
    }

    fun addNewMathQuestion(question: String, answer: String, categoryGame: String){
        val mathQuestion = MathQuestion(question = question, answer = answer, categoryGame = categoryGame)
        firebaseMathInteractor.addMathQuestion(mathQuestion)
    }
}