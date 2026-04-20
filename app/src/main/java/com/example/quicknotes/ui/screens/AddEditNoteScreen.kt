package com.example.quicknotes.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.quicknotes.ui.viewmodel.NoteViewModel
import com.example.quicknotes.NoteValidator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    viewModel: NoteViewModel,
    noteId: Int?,
    onSaveDone: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val notes by viewModel.notes.collectAsState()
    val existingNote = notes.find { it.id == noteId }

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(existingNote) {
        if (existingNote != null) {
            title = existingNote.title
            content = existingNote.content
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (noteId == null) "Add Note" else "Edit Note")
                },

                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },

                actions = {

                    // SHARE BUTTON
                    IconButton(onClick = {
                        shareNote(context, title, content)
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }

                    // SAVE BUTTON
                    TextButton(onClick = {
                        if (!NoteValidator.isValidNote(title, content)) {
                            errorMessage = "Please enter both title and content."
                        } else {
                            if (noteId == null) {
                                viewModel.addNote(title.trim(), content.trim())
                            } else {
                                viewModel.updateNote(noteId, title.trim(), content.trim())
                            }
                            onSaveDone()
                        }
                    }) {
                        Text("Save")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            TextField(
                value = title,
                onValueChange = {
                    title = it
                    errorMessage = ""
                },
                placeholder = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = content,
                onValueChange = {
                    content = it
                    errorMessage = ""
                },
                placeholder = { Text("Start writing your note...") },
                modifier = Modifier.fillMaxSize()
            )

            if (errorMessage.isNotBlank()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

fun shareNote(context: Context, title: String, content: String) {
    val text = "$title\n\n$content"

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }

    context.startActivity(Intent.createChooser(intent, "Share note via"))
}