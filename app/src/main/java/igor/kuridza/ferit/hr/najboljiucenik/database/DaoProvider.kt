package igor.kuridza.ferit.hr.najboljiucenik.database

import androidx.room.Database
import androidx.room.RoomDatabase
import igor.kuridza.ferit.hr.najboljiucenik.model.FlagQuestion
import igor.kuridza.ferit.hr.najboljiucenik.model.MathQuestion
import igor.kuridza.ferit.hr.najboljiucenik.model.ProverbQuestion
import igor.kuridza.ferit.hr.najboljiucenik.model.QuestionTrueFalse

@Database(
    entities = [
        ProverbQuestion::class,
        MathQuestion::class,
        FlagQuestion::class,
        QuestionTrueFalse::class
    ], version = 1, exportSchema = false
)
abstract class DaoProvider : RoomDatabase(){

    abstract fun proverbDao(): ProverbDao
    abstract fun mathQuestionDao(): MathQuestionDao
    abstract fun flagQuestionDao(): FlagQuestionDao
    abstract fun trueFalseQuestionDao(): TrueFalseQuestionDao
}