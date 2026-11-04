package com.example.moviefinder

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class MovieFinderLogicTest {

    // Test 1: Simple Data Validation Logic
    @Test
    fun testMovieTitleValidation_EmptyTitle_ReturnsFalse() {
        val emptyTitle = "   "
        val validTitle = "Inception"

        // Logic simulating a check before saving to the database or API
        val isInvalid = emptyTitle.trim().isEmpty()
        val isValid = validTitle.trim().isNotEmpty()

        assertTrue("Empty title should be invalid", isInvalid)
        assertTrue("Valid title should be valid", isValid)
    }

    // Test 2: Simple Collection/List Manipulation (e.g., Watchlist)
    @Test
    fun testWatchlistCount_IncrementsCorrectly() {
        // Simulating a user's watchlist
        val watchlist = mutableListOf<String>()

        assertEquals("Initial watchlist should be empty", 0, watchlist.size)

        // Add a movie
        watchlist.add("The Matrix")

        assertEquals("Watchlist should contain 1 movie", 1, watchlist.size)

        // Ensure logic correctly identifies false scenarios
        assertFalse("Watchlist count should not be 2", watchlist.size == 2)
    }
}