package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TrueFalseQuestionsViewModel @ViewModelInject constructor(
    private val firebaseTrueFalseInteractor: FirebaseTrueFalseInteractor
): ViewModel(){

    fun getFirebaseQuery(): Query{
        return firebaseTrueFalseInteractor.getFirestorePath()
    }

    fun deleteTrueFalseQuestion(documentSnapshot: DocumentSnapshot) = viewModelScope.launch(IO){
        firebaseTrueFalseInteractor.deleteTrueFalseQuestion(documentSnapshot)
    }
}