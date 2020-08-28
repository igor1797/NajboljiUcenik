package igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.editquestion.viewodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import igor.kuridza.ferit.hr.najboljiucenik.R
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse
import igor.kuridza.ferit.hr.najboljiucenik.ui.fragmentdialogs.truefalsequestions.model.TrueFalseQuestionFormState

class EditTrueFalseQuestionViewModel @ViewModelInject constructor(
    private val firebaseTrueFalseInteractor: FirebaseTrueFalseInteractor
): ViewModel() {

    private val _editNewTrueFalseQuestionForm = MutableLiveData<TrueFalseQuestionFormState>()
    val editTrueFalseQuestionFormState: LiveData<TrueFalseQuestionFormState> = _editNewTrueFalseQuestionForm

    fun editTrueFalseQuestionDataChanged(question: String){
        when {
            question.isEmpty() -> {
                _editNewTrueFalseQuestionForm.value = TrueFalseQuestionFormState(questionError = R.string.fieldCantBeEmptyText)
            }
            else -> {
                _editNewTrueFalseQuestionForm.value = TrueFalseQuestionFormState(isDataValid = true)
            }
        }
    }

    fun editTrueFalseQuestion(question: String, answer: Boolean, categoryGame: String, documentSnapshot: DocumentSnapshot){
        val trueFalseQuestion = QuestionTrueFalse(question = question, answer = answer, categoryGame = categoryGame)
        firebaseTrueFalseInteractor.editTrueFalseQuestion(trueFalseQuestion, documentSnapshot)
    }
}