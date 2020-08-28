package igor.kuridza.ferit.hr.najboljiucenik.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion

@Dao
interface MathQuestionDao {

    @Query("Select* FROM MathQuestion WHERE categoryGame == :categoryName ORDER BY RANDOM() LIMIT 10")
    fun get10MathQuestionsForCategory(categoryName: String): List<MathQuestion>

    @Query("Delete FROM MathQuestion")
    suspend fun clearAllMathQuestions()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMathQuestion(question: MathQuestion)
}