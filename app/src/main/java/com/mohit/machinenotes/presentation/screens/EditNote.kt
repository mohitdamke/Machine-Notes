package com.mohit.machinenotes.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinenotes.presentation.viewmodel.NoteViewModel

@Composable
fun EditNote(
    noteId: Int,
    noteViewModel: NoteViewModel,
    onBack: () -> Unit
) {

    val selectedNote = noteViewModel.selectedNotes.collectAsState().value
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    LaunchedEffect(noteId) {
        noteViewModel.getNoteById(noteId)
    }
    LaunchedEffect(selectedNote) {
        selectedNote?.let {
            title = it.title
            content = it.content
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it }
        )
        Spacer(Modifier.heightIn(6.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it }
        )

        Spacer(Modifier.heightIn(10.dp))

        Button(onClick = {
            selectedNote?.let {
                noteViewModel.updateNote(
                    it.copy(
                        title = title,
                        content = content
                    )
                )
            }
            onBack()
        }
        ) { Text("Insert") }

    }
}