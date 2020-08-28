package igor.kuridza.ferit.hr.najboljiucenik.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import igor.kuridza.ferit.hr.najboljiucenik.common.DB_NAME
import igor.kuridza.ferit.hr.najboljiucenik.model.FlagQuestion
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse

@Database(entities = [ProverbQuestion::class, MathQuestion::class, FlagQuestion::class, QuestionTrueFalse::class], version = 1, exportSchema = false)
abstract class DaoProvider : RoomDatabase(){

    abstract fun proverbDao(): ProverbDao
    abstract fun mathQuestionDao(): MathQuestionDao
    abstract fun flagQuestionDao(): FlagQuestionDao
    abstract fun trueFalseQuestionDao(): TrueFalseQuestionDao

    companion object {
        @Volatile
        private var instance: DaoProvider? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DaoProvider::class.java,
                DB_NAME
            ).allowMainThreadQueries().build()
    }
}