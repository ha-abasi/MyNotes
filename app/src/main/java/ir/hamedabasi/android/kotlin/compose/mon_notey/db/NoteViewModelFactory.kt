package ir.hamedabasi.android.kotlin.compose.mon_notey.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.repositories.NotesRepository
import kotlinx.coroutines.launch

/*
    this factory is required in order to inject dependencies for our viewModel
 */
class NoteViewModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory {

    /*
        Note:
            If viewModel required additional parameters like repository
            or context, you need to created ViewModelProvider.Factory
            to take care of instantiation. remember viewModel needs to
            be instantiated without parameters by default.

     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("It Only Assignable To NoteViewModel !")
    }
}