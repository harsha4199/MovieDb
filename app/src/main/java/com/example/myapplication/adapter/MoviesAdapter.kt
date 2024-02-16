import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemMoviesBinding
import com.example.myapplication.model.ResultsItem
import com.bumptech.glide.Glide
import com.example.myapplication.R

/*Adapter that displays data for movie and shows movie List in grid view and also passes movie id for retrieving movie details*/
class MoviesAdapter(private val onItemClick: (Int) -> Unit) :
    PagingDataAdapter<ResultsItem, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviesBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val resultsItem = getItem(position)
        resultsItem?.let { holder.bind(it) }
    }

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultsItem: ResultsItem) {
            binding.resultItems = resultsItem
            binding.root.setOnClickListener {
                resultsItem.id?.let { movieId ->
                    onItemClick(movieId.toInt())
                }
            }
            binding.executePendingBindings()

            // Load image using Glide
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500" + resultsItem.posterPath)
                .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image while loading
                .error(R.drawable.ic_launcher_background) // Error image if loading fails
                .into(binding.imageViewMoviePoster)
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
    override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
        return oldItem == newItem
    }
}
