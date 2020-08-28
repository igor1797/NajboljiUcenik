package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.proverbs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor

class ProverbQuestionsViewModel @ViewModelInject constructor(
    private val firebaseProverbInteractor: FirebaseProverbInteractor
): ViewModel(){

    fun getProverbFirebaseQuery(): Query{
        return firebaseProverbInteractor.getProverbFirebasePath()
    }

    fun deleteProverbQuestion(documentSnapshot: DocumentSnapshot){
        firebaseProverbInteractor.deleteProverbQuestion(documentSnapshot)
    }
}