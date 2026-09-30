package com.mohit.machinenotes.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinenotes.presentation.viewmodel.NoteViewModel

@Composable
fun NoteList(
    viewModel: NoteViewModel, onAdd: () -> Unit, onEdit: (Int) -> Unit
) {

    val notes = viewModel.notes.collectAsState().value
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState().value
    val isError = viewModel.isLoading.collectAsState().value


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::searchNote,
        )

        Spacer(modifier = Modifier.heightIn(6.dp))
        Button(onClick = { onAdd() }) { Text("Add +") }
        Spacer(modifier = Modifier.heightIn(6.dp))


        LazyColumn() {
            items(notes) { allNotes ->
                Text(text = allNotes.title)
                Text(text = allNotes.content)
                Button(onClick = { onEdit(allNotes.id.toInt()) }) { Text("Edit") }
                Button(onClick = {
                    viewModel.deleteNote(allNotes)
                }) { Text("Delete") }
            }
        }

    }


}