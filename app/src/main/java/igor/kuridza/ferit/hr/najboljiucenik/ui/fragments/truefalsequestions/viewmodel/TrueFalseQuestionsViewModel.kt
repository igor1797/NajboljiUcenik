package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.truefalsequestions.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor

class TrueFalseQuestionsViewModel @ViewModelInject constructor(
    private val firebaseTrueFalseInteractor: FirebaseTrueFalseInteractor
): ViewModel(){

    fun getFirebaseQuery(): Query{
        return firebaseTrueFalseInteractor.getFirestorePath()
    }

    fun deleteTrueFalseQuestion(documentSnapshot: DocumentSnapshot){
        firebaseTrueFalseInteractor.deleteTrueFalseQuestion(documentSnapshot)
    }
}