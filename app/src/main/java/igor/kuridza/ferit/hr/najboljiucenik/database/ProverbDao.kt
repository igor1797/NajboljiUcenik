package igor.kuridza.ferit.hr.najboljiucenik.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion

@Dao
interface ProverbDao {

    @Query("Select* FROM ProverbQuestion ORDER BY RANDOM() LIMIT 10")
    fun get10ProverbQuestions(): List<ProverbQuestion>

    @Query("Delete FROM ProverbQuestion")
    suspend fun clearAllQuestions()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(proverbQuestion: ProverbQuestion)
}