package igor.kuridza.ferit.hr.najboljiucenik.firebase.score

import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.common.PLAYERS_SCORE_PATH
import igor.kuridza.ferit.hr.najboljiucenik.firebase.base.BaseFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.model.PlayerScore

class ScoreFirebaseInteractorImpl: BaseFirebaseInteractor(), ScoreFirebaseInteractor{

    private val playerScorePath = firestore.collection(PLAYERS_SCORE_PATH)

    override fun getScoreQueryForCategory(categoryType: String): Query {
        return playerScorePath
            .whereEqualTo("categoryType", categoryType)
            .orderBy("score", Query.Direction.DESCENDING)
    }

    override fun savePlayerScore(playerScore: PlayerScore) {
        playerScorePath.add(playerScore)
    }
}