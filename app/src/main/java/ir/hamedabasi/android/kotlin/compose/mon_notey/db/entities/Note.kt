package ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String,
    var color: Int
)
