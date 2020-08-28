package igor.kuridza.ferit.hr.najboljiucenik.firebase.score

import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.model.PlayerScore


interface ScoreFirebaseInteractor {
    fun getScoreQueryForCategory(categoryType: String): Query
    fun savePlayerScore(playerScore: PlayerScore)
}