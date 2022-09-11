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
            binding.title.text = item.title
            binding.writer.text = item.author
            binding.time.text = item.elapsed
            binding.thumbnail.clipToOutline = true

            Glide.with(binding.root.context)
                .load(item.imgUrl)
                .placeholder(R.color.gray)
                .error(R.drawable.img_not_found)
                .into(binding.thumbnail)

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
