package com.example.myapplication.ui

/*created
 by
 Harsha Devnani*/

import MoviesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.MoviesLoadStateAdapter
import com.example.myapplication.databinding.FragmentMoviesBinding
import com.example.myapplication.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {

    val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        setupRecyclerView()

        // Observe movie flow and update adapter
        observeMovieFlow()
    }

    fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter { movieId ->
            navigateToMovieDetails(movieId)
        }

        binding.recyclerViewMovies.apply {
            val gridLayoutManager = GridLayoutManager(requireContext(), 3)
            layoutManager = gridLayoutManager
            adapter = moviesAdapter.withLoadStateFooter(MoviesLoadStateAdapter())
        }
    }

    private fun observeMovieFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieFlow.collectLatest { pagingData ->
                moviesAdapter.submitData(pagingData)
            }
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}
