package com.example.moviefinder

import android.content.Context
import android.content.SharedPreferences
import com.example.moviefinder.model.Movie
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.kotlin.*

class ExampleUnitTest {

    private lateinit var favouriteManager: FavouriteManager
    private lateinit var mockContext: Context
    private lateinit var mockPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor

    // This variable acts as our fake database in memory
    private var savedJsonString: String = "[]"

    @Before
    fun setUp() {
        // 1. Create fake versions of Android classes
        mockContext = mock()
        mockPreferences = mock()
        mockEditor = mock()

        // 2. Tell the fake context to return the fake preferences
        whenever(mockContext.getSharedPreferences(any(), any())).thenReturn(mockPreferences)

        // 3. Tell the fake preferences how to behave
        whenever(mockPreferences.getString(eq("favourites"), any())).thenAnswer { savedJsonString }
        whenever(mockPreferences.edit()).thenReturn(mockEditor)

        // 4. When putString is called, save the string to our fake variable
        whenever(mockEditor.putString(any(), any())).thenAnswer { invocation ->
            savedJsonString = invocation.getArgument(1) as String
            mockEditor
        }

        // 5. When apply() is called, do nothing (since we already saved it above)
        doNothing().whenever(mockEditor).apply()

        // 6. Initialize your manager with the fake context
        favouriteManager = FavouriteManager(mockContext)
    }

    @Test
    fun addFavourite_savesMovieSuccessfully() {
        // 1. Create a dummy movie (matching your exact Movie data class)
        val testMovie = Movie(
            id = 101,
            title = "The Matrix",
            overview = "A computer hacker learns...",
            poster_path = null,
            vote_average = 8.7,
            release_date = "1999-03-31"
        )

        // 2. Add it to favourites
        favouriteManager.addFavourite(testMovie)

        // 3. Check if it worked
        assertTrue(favouriteManager.isFavourite(101))
        assertEquals(1, favouriteManager.getFavourites().size)
        assertEquals("The Matrix", favouriteManager.getFavourites()[0].title)
    }

    @Test
    fun removeFavourite_deletesMovieSuccessfully() {
        val testMovie = Movie(101, "The Matrix", "Overview", null, 8.7, "1999-03-31")

        // Add it first
        favouriteManager.addFavourite(testMovie)
        assertTrue(favouriteManager.isFavourite(101))

        // Now remove it
        favouriteManager.removeFavourite(101)

        // Check if it's gone
        assertFalse(favouriteManager.isFavourite(101))
        assertEquals(0, favouriteManager.getFavourites().size)
    }

    @Test
    fun addFavourite_doesNotAddDuplicateMovie() {
        val testMovie = Movie(101, "The Matrix", "Overview", null, 8.7, "1999-03-31")

        // Add the same movie twice
        favouriteManager.addFavourite(testMovie)
        favouriteManager.addFavourite(testMovie)

        // Check that it only saved one copy
        assertEquals(1, favouriteManager.getFavourites().size)
    }
}