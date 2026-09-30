package com.mohit.machinenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.mohit.machinenotes.navigation.NavigationController
import com.mohit.machinenotes.presentation.viewmodel.NoteViewModel
import com.mohit.machinenotes.ui.theme.MachineNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MachineNotesTheme {
                val viewmodel : NoteViewModel = hiltViewModel()
                val navController = rememberNavController()
                NavigationController(
                    viewModel = viewmodel,
                    navController = navController,
                )
            }
        }
    }
}
