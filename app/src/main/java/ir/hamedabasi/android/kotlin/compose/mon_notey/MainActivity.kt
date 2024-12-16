package ir.hamedabasi.android.kotlin.compose.mon_notey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteDB
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteViewModel
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteViewModelFactory
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.repositories.NotesRepository
import ir.hamedabasi.android.kotlin.compose.mon_notey.screens.NoteList
import ir.hamedabasi.android.kotlin.compose.mon_notey.ui.theme.MonNoteyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Database
        val db = NoteDB.getInstance(context = applicationContext)

        // Repo
        val repo = NotesRepository(db.noteDao)

        // viewModel Factory:
        val vmFactory = NoteViewModelFactory(repo)

        // viewModel
        val vm = ViewModelProvider(this, vmFactory)[NoteViewModel::class.java]

        /*
            Inserting Dummy Data :

            vm.insert(Note(100, "Take SHAYAN to the school", "You need to take our son to his school !", "#EF12EF".toColorInt()))
            vm.insert(Note(101, "Buy launch", "I didn't cook for launch, so plz get some food on your way !", "#2F12EF".toColorInt()))
       */


        setContent {
            MonNoteyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // observeAsState required -> implementation(libs.androidx.runtime.livedata)
                    //
                    // observeAsState convert live data to state object that can be observed
                    // within Composable function
                    //
                    val notes by vm.allNotes.observeAsState(emptyList())

                    NoteList(Modifier.padding(innerPadding), notes)
                }
            }
        }
    }
}
