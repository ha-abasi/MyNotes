package ir.hamedabasi.android.kotlin.compose.mon_notey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.toColorInt
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteViewModel
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNote(viewModel: NoteViewModel){
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedColor by remember {
        mutableStateOf(Color.Red)
    }



    // get state of dialog visibility :
    val enabledStateFlow by viewModel.displayEditDialog.collectAsState()




    if (enabledStateFlow){
        BasicAlertDialog(onDismissRequest = {
            viewModel.setDisplayEditDialog(false)
        },
            properties = DialogProperties(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {

            Column {
                Text("Edit/New Note", color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Spacer(modifier = Modifier.height(10.dp))


                TextField(title, { title = it}, label = { Text("Title")}, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                TextField(description, { description = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    val note = Note(
                        id = 0,
                        title = title,
                        description = description,
                        color = selectedColor.toArgb() // convert Color object to integer represent (Alpha and RGB values)
                    )

                    viewModel.insert(note)

                    viewModel.setDisplayEditDialog(false)
                }) {
                    Text("OK")
                }
            }

        }
    }

}