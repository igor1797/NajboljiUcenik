package igor.kuridza.ferit.hr.najboljiucenik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionTrueFalse(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val categoryGame: String = "",
    val question: String = "",
    val answer: Boolean = true
)