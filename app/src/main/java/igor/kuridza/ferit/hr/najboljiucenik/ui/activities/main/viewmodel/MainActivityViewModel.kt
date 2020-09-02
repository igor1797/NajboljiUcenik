package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import igor.kuridza.ferit.hr.najboljiucenik.firebase.geography.FirebaseFlagInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    private val firebaseMathInteractor: FirebaseMathInteractor,
    private val firebaseFlagInteractor: FirebaseFlagInteractor,
    private val firebaseTrueFalseInteractor: FirebaseTrueFalseInteractor,
    private val firebaseProverbInteractor: FirebaseProverbInteractor
): ViewModel(){

    fun getQuestions() = viewModelScope.launch(IO){
        firebaseFlagInteractor.getFlagQuestions()
        firebaseMathInteractor.getMathQuestions()
        firebaseTrueFalseInteractor.getTrueFalseQuestions()
        firebaseProverbInteractor.getProverbQuestions()
    }
}