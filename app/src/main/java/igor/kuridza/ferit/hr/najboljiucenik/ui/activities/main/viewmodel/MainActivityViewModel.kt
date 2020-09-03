package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.main.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import igor.kuridza.ferit.hr.najboljiucenik.common.dataOlderThanOneDay
import igor.kuridza.ferit.hr.najboljiucenik.firebase.geography.FirebaseFlagInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.prefs.SharedPrefsHelper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class MainActivityViewModel @ViewModelInject constructor(
    private val firebaseMathInteractor: FirebaseMathInteractor,
    private val firebaseFlagInteractor: FirebaseFlagInteractor,
    private val firebaseTrueFalseInteractor: FirebaseTrueFalseInteractor,
    private val firebaseProverbInteractor: FirebaseProverbInteractor,
    private val sharedPrefsHelper: SharedPrefsHelper
): ViewModel(){

    fun checkData(){
        val timeDataStored = sharedPrefsHelper.getTime()
        if(dataOlderThanOneDay(timeDataStored) || timeDataStored == -1L){
            getQuestions()
            Log.d("TAGA", "Getting data")
            sharedPrefsHelper.storeTime(Calendar.getInstance().timeInMillis)
        }
    }

    private fun getQuestions() = viewModelScope.launch(IO) {
        firebaseFlagInteractor.getFlagQuestions()

        firebaseMathInteractor.getMathQuestions()

        firebaseTrueFalseInteractor.getTrueFalseQuestions()

        firebaseProverbInteractor.getProverbQuestions()
    }
}