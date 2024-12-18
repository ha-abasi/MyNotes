package ir.hamedabasi.android.kotlin.compose.mon_notey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
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

    // get state of edited note :
    val editedNote by viewModel.editedNote.collectAsState()
    if (editedNote != null){
        title = editedNote!!.title
        description = editedNote!!.description
        selectedColor = Color(editedNote!!.color)
    }


    fun onSave(){
        if(editedNote == null){
            // It's a new Item
            val newNote = Note(
                id = 0,
                title = title,
                description = description,
                color = selectedColor.toArgb() // convert Color object to integer represent (Alpha and RGB values)
            )
            viewModel.insert(newNote)

        }else{
            // It's an edit
            editedNote!!.title = title
            editedNote!!.description = description
            editedNote!!.color = selectedColor.toArgb()


            viewModel.update(editedNote!!)
        }
        viewModel.setDisplayEditDialog(false)
    }


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


                Spacer(modifier = Modifier.height(10.dp))

                ColorSelector(selectedColor){ color ->
                    selectedColor = color
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {onSave()}) {
                    Text("OK")
                }
            }

        }
    }

}

@Composable
fun ColorSelector(selectecColor: Color, onColorSelect: (color: Color) -> Unit){
    val colors = listOf(
        Color("#88ee12".toColorInt()),
        Color("#EE22EF".toColorInt()),
        Color("#9819EF".toColorInt()),
        Color("#CC8811".toColorInt()),
        Color("#ED77EE".toColorInt()),
        Color("#1DEEFF".toColorInt()),
        Color("#1173EF".toColorInt()),
        Color("#ED8199".toColorInt()),
        Color("#EFEF33".toColorInt()),
        Color("#89E17E".toColorInt()),

    )


    LazyRow {
        items(colors){ color ->
            Box(modifier = Modifier
                .size(40.dp)
                .padding(4.dp)
                .clip(CircleShape)
                .background(color)
                .border(
                    width = if (color == selectecColor) 4.dp else 1.dp,
                    color = if (color == selectecColor) Color.Black else Color.Transparent,
                    shape = CircleShape
                )
                .clickable {
                    onColorSelect(color)
                }
            ){

            }
        }
    }
}