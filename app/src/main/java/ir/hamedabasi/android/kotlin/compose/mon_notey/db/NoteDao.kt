package ir.hamedabasi.android.kotlin.compose.mon_notey.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Query("select * from notes")
    fun getAllNotes() : LiveData<List<Note>>
}