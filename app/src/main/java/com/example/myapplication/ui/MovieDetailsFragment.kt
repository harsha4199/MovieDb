package com.example.myapplication.ui

/*created
 by
 Harsha Devnani*/

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMovieDetailsBinding
import com.example.myapplication.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private lateinit var binding: FragmentMovieDetailsBinding
    private var movieId: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the movie ID from the arguments
        movieId = requireArguments().getInt("movieId", -1)

        // Check if movie ID is valid
        if (movieId != -1) {
            // Observe loading state
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.isLoading.collect { isLoading ->
                    // Show/hide loader based on loading state
                    binding.loader.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
                }
            }

            // Fetch movie details based on the movie ID
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getMovieDetails(movieId).collect { movieDetails ->
                    binding.movieDetails = movieDetails
                    Glide.with(binding.root)
                        .load("https://image.tmdb.org/t/p/w500"+ movieDetails.posterPath)
                        .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
                        .error(R.drawable.ic_launcher_background) // Error image if loading fails
                        .into(binding.imageViewPoster)

                }
            }
        } else {
            // Handling invalid movie ID
            Log.e("MovieDetailsFragment", "Invalid movie ID")
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
