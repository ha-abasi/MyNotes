package ir.hamedabasi.android.kotlin.compose.mon_notey.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteViewModel
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note
import java.util.Stack

@Composable
fun NoteListItem(note: Note, model: NoteViewModel){
    Row(modifier = Modifier.padding(5.dp)){
        Box{
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(contentColor = Color(note.color)),
                border = BorderStroke(1.dp, color = Color.Black)
            ) {
                Column(modifier = Modifier.padding(10.dp, 40.dp, 10.dp, 10.dp)){
                    Text("${note.title}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("${note.description}", fontSize = 13.sp, fontWeight = FontWeight.Normal, color = Color.Black)
                }
            }
            Box(modifier = Modifier.align(Alignment.TopEnd)){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                    EditButton(note, model)
                    DeleteButton(note, model)
                }
            }

        }

    }
}

@Composable
fun DeleteButton(note: Note, model: NoteViewModel){
    IconButton(onClick = {model.remove(note)}) {
        Icon(imageVector = Icons.Default.Delete, tint = Color.Red, contentDescription = "")
    }
}

@Composable
fun EditButton(note: Note, model: NoteViewModel){
    IconButton(onClick = { model.setEditedNote(note); model.setDisplayEditDialog(true)}) {
        Icon(imageVector = Icons.Default.Edit, tint = Color.DarkGray, contentDescription = "")
    }
}