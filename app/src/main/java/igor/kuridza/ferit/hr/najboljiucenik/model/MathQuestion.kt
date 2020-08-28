package igor.kuridza.ferit.hr.najboljiucenik.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MathQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val question: String = "",
    val answer: String = "",
    val categoryGame: String = ""
): Parcelable