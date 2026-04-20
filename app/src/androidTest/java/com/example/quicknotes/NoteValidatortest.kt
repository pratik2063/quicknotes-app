package com.example.quicknotes

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NoteValidatorTest {

    @Test
    fun emptyTitle_returnsFalse() {
        assertFalse(NoteValidator.isValidTitle(""))
    }

    @Test
    fun blankTitle_returnsFalse() {
        assertFalse(NoteValidator.isValidTitle("   "))
    }

    @Test
    fun normalTitle_returnsTrue() {
        assertTrue(NoteValidator.isValidTitle("Shopping List"))
    }

    @Test
    fun titleWithSpacesAndText_returnsTrue() {
        assertTrue(NoteValidator.isValidTitle("  Work  "))
    }

    @Test
    fun emptyContent_returnsFalse() {
        assertFalse(NoteValidator.isValidContent(""))
    }

    @Test
    fun blankContent_returnsFalse() {
        assertFalse(NoteValidator.isValidContent("   "))
    }

    @Test
    fun normalContent_returnsTrue() {
        assertTrue(NoteValidator.isValidContent("Buy milk and bread"))
    }

    @Test
    fun contentWithSpacesAndText_returnsTrue() {
        assertTrue(NoteValidator.isValidContent("  Meeting at 5 PM  "))
    }

    @Test
    fun validTitleAndContent_returnsTrue() {
        assertTrue(NoteValidator.isValidNote("Task", "Finish assignment"))
    }

    @Test
    fun invalidTitleAndValidContent_returnsFalse() {
        assertFalse(NoteValidator.isValidNote("", "Finish assignment"))
    }

}
