package igor.kuridza.ferit.hr.najboljiucenik.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse

@Dao
interface TrueFalseQuestionDao {

    @Query("Select* FROM QuestionTrueFalse WHERE categoryGame == :categoryName ORDER BY RANDOM() LIMIT 100")
    fun get100TrueFalseQuestionsForCategory(categoryName: String): List<QuestionTrueFalse>

    @Query("Select* FROM QuestionTrueFalse WHERE categoryGame == :categoryName ORDER BY RANDOM() LIMIT 10")
    fun get10TrueFalseQuestionsForCategory(categoryName: String): List<QuestionTrueFalse>

    @Query("Delete FROM QuestionTrueFalse")
    suspend fun clearAllQuestionTrueFalse()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTrueFalseQuestion(question: QuestionTrueFalse)
}