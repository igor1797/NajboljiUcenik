package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.editquestion.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.model.ProverbQuestionFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EditProverbQuestionViewModel @ViewModelInject constructor(
    private val proverbInteractor: FirebaseProverbInteractor
): ViewModel() {

    private val _editProverbQuestionForm = MutableLiveData<ProverbQuestionFormState>()
    val editProverbQuestionForm: LiveData<ProverbQuestionFormState> = _editProverbQuestionForm

    fun editProverbQuestionDataChanged(question: String, answer: String, letters: String){
        when {
            question.isEmpty() -> {
                _editProverbQuestionForm.value = ProverbQuestionFormState(questionError = R.string.fieldCantBeEmptyText)
            }
            answer.isEmpty() -> {
                _editProverbQuestionForm.value = ProverbQuestionFormState(answerError = R.string.fieldCantBeEmptyText)
            }
            letters.isEmpty() -> {
                _editProverbQuestionForm.value = ProverbQuestionFormState(lettersError = R.string.fieldCantBeEmptyText)
            }
            letters.length>11 ->{
                _editProverbQuestionForm.value = ProverbQuestionFormState(lettersError = R.string.fieldMustContain11LettersText)
            }
            letters.length<11 ->{
                _editProverbQuestionForm.value = ProverbQuestionFormState(lettersError = R.string.fieldMustContain11LettersText)
            }
            else -> {
                _editProverbQuestionForm.value = ProverbQuestionFormState(isDataValid = true)
            }
        }
    }

    fun editProverbQuestion(question: String, answer: String, letters: String, documentSnapshot: DocumentSnapshot) = viewModelScope.launch(IO){
        val proverbQuestion = ProverbQuestion(question = question, answer = answer, letters = letters)
        proverbInteractor.editProverbQuestion(proverbQuestion, documentSnapshot)
    }
}