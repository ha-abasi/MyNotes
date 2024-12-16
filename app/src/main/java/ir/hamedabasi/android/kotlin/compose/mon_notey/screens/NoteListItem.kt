package ir.hamedabasi.android.kotlin.compose.mon_notey.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    Row(modifier = Modifier.padding(5.dp)){
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(contentColor = Color(note.color)),
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Column(modifier = Modifier.padding(10.dp)){
                Text("${note.title}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(6.dp))
                Text("${note.description}", fontSize = 13.sp, fontWeight = FontWeight.Normal, color = Color.Black)
            }
        }
    }
}