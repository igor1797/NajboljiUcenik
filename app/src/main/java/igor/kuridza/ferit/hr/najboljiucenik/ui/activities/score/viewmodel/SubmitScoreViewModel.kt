package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import igor.kuridza.ferit.hr.najboljiucenik.firebase.score.ScoreFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.PlayerScore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SubmitScoreViewModel @ViewModelInject constructor(
    private val scoreFirebaseInteractor: ScoreFirebaseInteractor
): ViewModel(){

    fun submitScore(nickname: String, categoryType: String, score: Long) = viewModelScope.launch(IO){
        val playerScore = PlayerScore(nickname,categoryType, score.toInt())
        scoreFirebaseInteractor.savePlayerScore(playerScore)
    }
}