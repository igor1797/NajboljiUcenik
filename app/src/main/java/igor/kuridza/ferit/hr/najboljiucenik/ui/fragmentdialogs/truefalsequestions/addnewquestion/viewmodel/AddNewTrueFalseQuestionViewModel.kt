package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.addnewquestion.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.model.TrueFalseQuestionFormState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddNewTrueFalseQuestionViewModel @ViewModelInject constructor(
    private val firebaseTrueFalseInteractor: FirebaseTrueFalseInteractor
): ViewModel(){

    private val _addNewTrueFalseQuestionForm = MutableLiveData<TrueFalseQuestionFormState>()
    val addTrueFalseQuestionFormState: LiveData<TrueFalseQuestionFormState> = _addNewTrueFalseQuestionForm

    fun newTrueFalseQuestionDataChanged(question: String){
        when {
            question.isEmpty() -> {
                _addNewTrueFalseQuestionForm.value = TrueFalseQuestionFormState(questionError = R.string.fieldCantBeEmptyText)
            }
            else -> {
                _addNewTrueFalseQuestionForm.value = TrueFalseQuestionFormState(isDataValid = true)
            }
        }
    }

    fun addNewTrueFalseQuestion(question: String, answer: Boolean, categoryGame: String) = viewModelScope.launch(IO){
        val trueFalseQuestion = QuestionTrueFalse(question = question, answer = answer, categoryGame = categoryGame)
        firebaseTrueFalseInteractor.addTrueFalseQuestion(trueFalseQuestion)
    }
}