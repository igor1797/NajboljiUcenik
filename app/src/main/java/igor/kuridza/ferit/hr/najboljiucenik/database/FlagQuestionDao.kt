package igor.kuridza.ferit.hr.najboljiucenik.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igor.kuridza.ferit.hr.najboljiucenik.model.FlagQuestion

@Dao
interface FlagQuestionDao {

    @Query("Select* FROM FlagQuestion ORDER BY RANDOM() LIMIT 10")
    fun get10FlagQuestions(): List<FlagQuestion>

    @Query("Delete FROM FlagQuestion")
    suspend fun clearAllFlagQuestions()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFlagQuestion(question: FlagQuestion)
}