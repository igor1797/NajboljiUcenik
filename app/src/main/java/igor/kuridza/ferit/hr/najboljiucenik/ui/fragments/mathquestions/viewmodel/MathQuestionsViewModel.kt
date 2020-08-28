package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.mathquestions.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor

class MathQuestionsViewModel @ViewModelInject constructor(
    private val firebaseMathInteractor: FirebaseMathInteractor
): ViewModel(){

    fun getFirebaseQuery(): Query{
        return firebaseMathInteractor.getFirestorePath()
    }

    fun deleteMathQuestion(documentSnapshot: DocumentSnapshot){
        firebaseMathInteractor.deleteMathQuestion(documentSnapshot)
    }
}