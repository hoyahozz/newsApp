package co.kr.hoyaho.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.presentation.R
import co.kr.hoyaho.presentation.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val news = ArrayList<News>()
    private var itemClickListener: NewsClickListener? = null

    fun submitList(news: List<News>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: NewsClickListener) {
        itemClickListener = listener
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.news = item

            binding.thumbnail.clipToOutline = true

            binding.container.setOnClickListener {
                itemClickListener?.navigateToDetail(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bind(news[position])

    override fun getItemCount(): Int = news.size
}

interface NewsClickListener {
    fun navigateToDetail(news: News)
}
