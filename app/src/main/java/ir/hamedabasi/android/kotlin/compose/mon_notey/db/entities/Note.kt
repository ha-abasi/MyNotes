package ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notes")
data class Note(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val description: String,
    val color: Int
)
