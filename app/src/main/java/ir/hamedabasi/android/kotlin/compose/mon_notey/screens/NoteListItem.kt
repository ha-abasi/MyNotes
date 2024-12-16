package ir.hamedabasi.android.kotlin.compose.mon_notey.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note

@Composable
fun NoteListItem(note: Note){
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(contentColor = Color(note.color)),
        border = BorderStroke(1.dp, color = Color.Black)
    ) {
        Column {
            Text("Title : ${note.title}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Description : ${note.description}", fontSize = 13.sp, fontWeight = FontWeight.Normal)
        }
    }
}