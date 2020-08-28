package igor.kuridza.ferit.hr.najboljiucenik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProverbQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val question: String = "",
    val answer: String = "",
    val letters: String = ""
)