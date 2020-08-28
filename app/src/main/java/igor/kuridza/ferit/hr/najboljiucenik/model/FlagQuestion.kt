package igor.kuridza.ferit.hr.najboljiucenik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlagQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val imageUrl: String = "",
    val answer: String = "",
    val letters: String = ""
)