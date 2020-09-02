package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.mathquestions.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MathQuestionsViewModel @ViewModelInject constructor(
    private val firebaseMathInteractor: FirebaseMathInteractor
): ViewModel(){

    fun getFirebaseQuery(): Query{
        return firebaseMathInteractor.getFirestorePath()
    }

    fun deleteMathQuestion(documentSnapshot: DocumentSnapshot) = viewModelScope.launch(IO){
        firebaseMathInteractor.deleteMathQuestion(documentSnapshot)
    }
}