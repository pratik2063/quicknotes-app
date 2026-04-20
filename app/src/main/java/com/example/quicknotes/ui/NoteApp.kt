package com.example.quicknotes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quicknotes.ui.screens.AddEditNoteScreen
import com.example.quicknotes.ui.screens.NoteListScreen
import com.example.quicknotes.ui.viewmodel.NoteViewModel

@Composable
fun NoteApp(viewModel: NoteViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            NoteListScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate("add")
                },
                onEditClick = { noteId ->
                    navController.navigate("edit/$noteId")
                }
            )
        }

        composable("add") {
            AddEditNoteScreen(
                viewModel = viewModel,
                noteId = null,
                onSaveDone = {
                    navController.popBackStack()
                }
            )
        }

        composable("edit/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments
                ?.getString("noteId")
                ?.toIntOrNull()

            AddEditNoteScreen(
                viewModel = viewModel,
                noteId = noteId,
                onSaveDone = {
                    navController.popBackStack()
                }
            )
        }
    }
}
