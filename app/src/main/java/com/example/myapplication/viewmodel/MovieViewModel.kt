package com.example.myapplication.viewmodel

/*created
 by
 Harsha Devnani*/

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.model.ResultsItem
import com.example.myapplication.paging.MoviePagingSource
import com.example.myapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    /**This represents a flow of paginated movie data and
    constructs a Pager with the specified configuration,
    creates a flow of paginated data,
    and caches it in the viewModelScope. **/

    val movieFlow: Flow<PagingData<ResultsItem>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(repository) }
        ).flow
            .cachedIn(viewModelScope)
            .catch { exception ->
                // Handle exceptions here
                // Optionally, you can set isLoading to false here as well
                _isLoading.value = false
            }

    init {
        // Observe the movie flow and update isLoading accordingly
        viewModelScope.launch {
            movieFlow.collectLatest { _ ->
                _isLoading.value = false
            }
        }
    }
}
