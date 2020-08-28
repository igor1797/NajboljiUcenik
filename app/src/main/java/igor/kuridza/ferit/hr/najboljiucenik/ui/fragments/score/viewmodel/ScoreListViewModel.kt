package igor.kuridza.ferit.hr.najboljiucenik.ui.fragments.score.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import igor.kuridza.ferit.hr.najboljiucenik.firebase.score.ScoreFirebaseInteractor

class ScoreListViewModel @ViewModelInject constructor(
    private val scoreFirebaseInteractor: ScoreFirebaseInteractor
): ViewModel(){

    fun getScoreQueryForCategory(categoryType: String): Query{
        return scoreFirebaseInteractor.getScoreQueryForCategory(categoryType)
    }
}