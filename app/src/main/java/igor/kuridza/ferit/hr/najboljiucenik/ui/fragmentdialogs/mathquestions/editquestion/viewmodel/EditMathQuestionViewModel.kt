package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.editquestion.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.mathquestions.model.MathQuestionFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EditMathQuestionViewModel @ViewModelInject constructor(
    private val firebaseMathInteractor: FirebaseMathInteractor
): ViewModel(){

    private val _editMathQuestionForm = MutableLiveData<MathQuestionFormState>()
    val editMathQuestionForm: LiveData<MathQuestionFormState> = _editMathQuestionForm

    fun editMathQuestionDataChanged(question: String, answer: String){
        when {
            question.isEmpty() -> {
                _editMathQuestionForm.value = MathQuestionFormState(questionError = R.string.fieldCantBeEmptyText)
            }
            answer.isEmpty() -> {
                _editMathQuestionForm.value = MathQuestionFormState(answerError = R.string.fieldCantBeEmptyText)
            }
            else -> {
                _editMathQuestionForm.value = MathQuestionFormState(isDataValid = true)
            }
        }
    }

    fun editMathQuestion(question: String, answer: String, categoryGame: String, documentSnapshot: DocumentSnapshot) = viewModelScope.launch(IO){
        val mathQuestion = MathQuestion(question = question, answer = answer, categoryGame = categoryGame)
        firebaseMathInteractor.editMathQuestion(mathQuestion, documentSnapshot)
    }
}