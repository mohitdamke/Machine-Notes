package com.mohit.machinenotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mohit.machinenotes.presentation.screens.AddNote
import com.mohit.machinenotes.presentation.screens.EditNote
import com.mohit.machinenotes.presentation.screens.NoteList
import com.mohit.machinenotes.presentation.viewmodel.NoteViewModel

@Composable
fun NavigationController(
    viewModel: NoteViewModel, navController: NavHostController
) {


    NavHost(
        navController = navController, startDestination = Routes.NoteList
    ) {

        composable(route = Routes.NoteList) {
            NoteList(
                viewModel = viewModel,
                onAdd = {
                    navController.navigate(Routes.AddNote)
                },
                onEdit = { id ->
                    navController.navigate(Routes.editNote(id))
                }
            )
        }

        composable(route = Routes.AddNote) {
            AddNote(
                noteViewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Routes.EditNote) { backstack ->
            val noteId = backstack.arguments?.getString("id")?.toIntOrNull()
            EditNote(
                noteId = noteId ?: 0,
                noteViewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )

        }


    }


}