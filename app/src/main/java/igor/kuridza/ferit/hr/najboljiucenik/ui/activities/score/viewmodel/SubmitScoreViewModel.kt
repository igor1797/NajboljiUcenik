package igor.kuridza.ferit.hr.najboljiucenik.ui.activities.score.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import igor.kuridza.ferit.hr.najboljiucenik.firebase.score.ScoreFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.PlayerScore

class SubmitScoreViewModel @ViewModelInject constructor(
    private val scoreFirebaseInteractor: ScoreFirebaseInteractor
): ViewModel(){

    fun submitScore(nickname: String, categoryType: String, score: Long){
        val playerScore = PlayerScore(nickname,categoryType, score.toInt())
        scoreFirebaseInteractor.savePlayerScore(playerScore)
    }
}