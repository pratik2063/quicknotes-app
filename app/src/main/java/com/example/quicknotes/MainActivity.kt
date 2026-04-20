package com.example.quicknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.quicknotes.data.NoteDatabase
import com.example.quicknotes.data.NoteRepository
import com.example.quicknotes.ui.NoteApp
import com.example.quicknotes.ui.theme.QuickNotesTheme
import com.example.quicknotes.ui.viewmodel.NoteViewModel
import com.example.quicknotes.ui.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            "notes_db"
        ).build()

        val repository = NoteRepository(db.noteDao())

        enableEdgeToEdge()
        setContent {
            QuickNotesTheme {
                val noteViewModel: NoteViewModel = viewModel(
                    factory = NoteViewModelFactory(repository)
                )
                NoteApp(viewModel = noteViewModel)
            }
        }
    }
}
