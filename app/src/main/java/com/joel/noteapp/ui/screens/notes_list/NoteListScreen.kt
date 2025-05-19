package com.joel.noteapp.ui.screens.notes_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joel.noteapp.data.entity.Note

@Composable
fun NoteListScreen(
    onAddNote : () -> Unit,
    noteListViewModel: NoteListViewModel
) {

    val uiState = noteListViewModel.uiState.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddNote()
                }
            ) {
                Icon(Icons.Default.Add, "add_icon")
            }
        }
    ) {  it ->
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            when(uiState){
                is NoteListUIState.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                uiState.errorMessage
                            )
                            Button(
                                onClick = {
                                    noteListViewModel.observeNotes()
                                }
                            ) {
                                Text("Retry")
                            }
                        }
                    }

                }
                NoteListUIState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }

                }
                is NoteListUIState.Success -> {
                    LazyColumn {
                       items(uiState.notes){ note ->
                           NoteCard(
                               note = note,
                               onNoteClick = {

                               },
                               onDeleteClick = {

                               }
                           )
                       }
                    }
                }
            }
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    onNoteClick : (Note) -> Unit,
    onDeleteClick : (Note) -> Unit
){

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .clickable {
                onNoteClick(note)
                //TODO() - NAVIGATING TO NOTES_EDIT PAGE
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    note.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    note.content,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 14.sp
                )
            }
            IconButton(
                onClick = {
                    onDeleteClick(note)
                    //TODO() - DELETING A NOTE
                }
            ) {
                Icon(Icons.Default.Delete, "delete_icon")
            }
        }
    }
}