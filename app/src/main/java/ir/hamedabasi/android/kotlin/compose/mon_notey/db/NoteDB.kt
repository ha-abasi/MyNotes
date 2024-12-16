package ir.hamedabasi.android.kotlin.compose.mon_notey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note


@Database([Note::class], version = 2)
abstract class NoteDB : RoomDatabase() {

    abstract val noteDao : NoteDao

    companion object {
        // Singleton :
        @Volatile
        private var INSTANCE : NoteDB? = null

        fun getInstance(context: Context) : NoteDB{
            //counter multithreading ..
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context = context,
                        klass = NoteDB::class.java,
                        name = "dbNote"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}