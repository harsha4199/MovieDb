package com.example.myapplication

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.R
import com.example.myapplication.ui.MovieFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MovieFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun testSetupRecyclerView() {
        // Launch the fragment within a test activity
        val scenario = launchFragmentInContainer<MovieFragment>()

        // Verify RecyclerView setup
        scenario.onFragment { fragment ->
            fragment.setupRecyclerView()
            val recyclerView = fragment.requireView().findViewById<RecyclerView>(R.id.recycler_view_movies)
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            assertEquals(3, layoutManager.spanCount)
        }
    }
}
