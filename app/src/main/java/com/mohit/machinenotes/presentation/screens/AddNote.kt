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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohit.machinenotes.presentation.viewmodel.NoteViewModel

@Composable
fun AddNote(
    noteViewModel: NoteViewModel,
    onBack: () -> Unit
) {

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

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
            noteViewModel.insertNote(
                title = title, content = content
            )
            onBack()
        }
        ) { Text("Insert") }
    }
}
