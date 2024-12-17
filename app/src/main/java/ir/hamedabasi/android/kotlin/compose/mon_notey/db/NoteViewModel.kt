package ir.hamedabasi.android.kotlin.compose.mon_notey.db

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.repositories.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*
    Store and manage UI related data
 */
class NoteViewModel(private val repository: NotesRepository) : ViewModel() {

    /*
        Defining a boolean StateFlow :
     */
    private val _displayEditDialog = MutableStateFlow(false)
    val displayEditDialog : StateFlow<Boolean> = _displayEditDialog.asStateFlow()
    fun setDisplayEditDialog(b: Boolean){
        _displayEditDialog.value = b
    }





    val allNotes = repository.allNotes

    fun insert(note: Note){
        //
        // Note: viewModelScope is a coroutine scope tied to the viewmodel
        // lifecycle. It ensure that all coroutine within, get cancelled
        // if the viewmodel cleared
        //

        //
        // launch is an coroutine builder that make a new coroutine
        // without blocking the current thread !
        //
        //
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    fun remove(note: Note){
        viewModelScope.launch {
            repository.remove(note)
        }
    }
}