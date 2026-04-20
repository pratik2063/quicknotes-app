package com.example.quicknotes

object NoteValidator {

    fun isValidTitle(title: String): Boolean {
        return title.trim().isNotEmpty()
    }

    fun isValidContent(content: String): Boolean {
        return content.trim().isNotEmpty()
    }

    fun isValidNote(title: String, content: String): Boolean {
        return isValidTitle(title) && isValidContent(content)
    }
}