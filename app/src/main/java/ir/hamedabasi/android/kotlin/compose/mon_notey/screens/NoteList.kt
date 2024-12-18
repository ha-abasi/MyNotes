package ir.hamedabasi.android.kotlin.compose.mon_notey.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.NoteViewModel
import ir.hamedabasi.android.kotlin.compose.mon_notey.db.entities.Note

@Composable
fun NoteList(viewModel: NoteViewModel, modifier: Modifier, noteList: List<Note>){

    val columnCount = if (noteList.isEmpty()) 1 else 2;

    Column {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(columnCount),
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 10.dp,
        ) {
            items(noteList){
                    note -> NoteListItem(note, viewModel)
            }
            if (noteList.isEmpty()) {
                items(listOf("empty-list")){
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
                        Text("The List Is EMPTY !", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
            }
        }
        EditNote(viewModel)
    }
}