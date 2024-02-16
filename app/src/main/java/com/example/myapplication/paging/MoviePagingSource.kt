package com.example.myapplication.paging

/*created
 by
 Harsha Devnani*/

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.model.ResultsItem
import com.example.myapplication.repository.MovieRepository
import retrofit2.HttpException
import java.io.IOException

/** class encapsulates the logic for loading paginated movie data
    from the repository and provides it to the Paging library
    It handles successful and error cases and determines the
    refresh key for data reloading **/

class MoviePagingSource(private val repository: MovieRepository) :
    PagingSource<Int, ResultsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        return try {
            val pageNumber = params.key ?: 1
            val response = repository.getPopularMovies(pageNumber)
            if (response.isSuccessful) {
                val movieResponse = response.body() ?: throw IOException("Movie list is null")
                val movies = movieResponse.results ?: emptyList()
                LoadResult.Page(
                    data = movies,
                    prevKey = if (pageNumber == 1) null else pageNumber - 1,
                    nextKey = if (movies.isEmpty()) null else pageNumber + 1
                )
            } else {
                LoadResult.Error(IOException("Unexpected response: ${response.code()} ${response.message()}"))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return null
    }
}
