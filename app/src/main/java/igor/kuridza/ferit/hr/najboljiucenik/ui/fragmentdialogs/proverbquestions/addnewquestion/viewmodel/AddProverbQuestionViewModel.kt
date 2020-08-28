package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.addnewquestion.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.proverbquestions.model.ProverbQuestionFormState

class AddProverbQuestionViewModel @ViewModelInject constructor(
    private val firebaseProverbInteractor: FirebaseProverbInteractor
): ViewModel(){

    private val _addProverbQuestionForm = MutableLiveData<ProverbQuestionFormState>()
    val addProverbQuestionFormState: LiveData<ProverbQuestionFormState> = _addProverbQuestionForm

    fun newProverbQuestionDataChanged(question: String, answer: String, letters: String){
        when {
            question.isEmpty() -> {
                _addProverbQuestionForm.value = ProverbQuestionFormState(questionError = R.string.fieldCantBeEmptyText)
            }
            answer.isEmpty() -> {
                _addProverbQuestionForm.value = ProverbQuestionFormState(answerError = R.string.fieldCantBeEmptyText)
            }
            letters.isEmpty() -> {
                _addProverbQuestionForm.value = ProverbQuestionFormState(lettersError = R.string.fieldCantBeEmptyText)
            }
            letters.length>11 ->{
                _addProverbQuestionForm.value = ProverbQuestionFormState(lettersError = R.string.fieldMustContain11LettersText)
            }
            letters.length<11 ->{
                _addProverbQuestionForm.value = ProverbQuestionFormState(lettersError = R.string.fieldMustContain11LettersText)
            }
            else -> {
                _addProverbQuestionForm.value = ProverbQuestionFormState(isDataValid = true)
            }
        }
    }

    fun addNewProverbQuestion(question: String, answer: String, letters: String){
        val proverbQuestion = ProverbQuestion(question = question, answer = answer, letters = letters)
        firebaseProverbInteractor.addProverbQuestion(proverbQuestion)
    }

}