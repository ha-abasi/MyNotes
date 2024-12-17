package ir.hamedabasi.android.kotlin.compose.mon_notey.db.repositories

import androidx.lifecycle.LiveData
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteDao
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note


/*
    Repository is the single source of truth for data
    it can :
        - fetch data from network
        - load data from database
 */
class NotesRepository(private val noteDao: NoteDao) {
    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun remove(note: Note){
        return noteDao.remove(note)
    }
    suspend fun update(note: Note){
        return noteDao.update(note)
    }
    suspend fun insert(note: Note){
        return noteDao.insert(note)
    }
}