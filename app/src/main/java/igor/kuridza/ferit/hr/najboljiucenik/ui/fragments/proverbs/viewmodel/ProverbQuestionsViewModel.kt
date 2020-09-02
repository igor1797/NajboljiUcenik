package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.proverbs.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ProverbQuestionsViewModel @ViewModelInject constructor(
    private val firebaseProverbInteractor: FirebaseProverbInteractor
): ViewModel(){

    fun getProverbFirebaseQuery(): Query{
        return firebaseProverbInteractor.getProverbFirebasePath()
    }

    fun deleteProverbQuestion(documentSnapshot: DocumentSnapshot) = viewModelScope.launch(IO){
        firebaseProverbInteractor.deleteProverbQuestion(documentSnapshot)
    }
}