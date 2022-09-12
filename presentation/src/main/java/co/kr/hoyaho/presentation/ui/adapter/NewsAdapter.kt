package co.kr.hoyaho.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.kr.hoyaho.domain.model.News
import co.kr.hoyaho.presentation.databinding.ItemNewsBinding

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(diffUtil) {

    private var itemClickListener: NewsClickListener? = null

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
        holder.bind(getItem(position))

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areContentsTheSame(oldItem: News, newItem: News) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: News, newItem: News) =
                oldItem.hashCode() == newItem.hashCode()
        }
    }
}

interface NewsClickListener {
    fun navigateToDetail(news: News)
}
