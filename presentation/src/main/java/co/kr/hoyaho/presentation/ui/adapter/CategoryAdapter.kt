package co.kr.hoyaho.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.kr.hoyaho.presentation.databinding.ItemCategoryBinding
import co.kr.hoyaho.presentation.ui.category.CategoryFragmentDirections
import co.kr.hoyaho.presentation.ui.model.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categories = Category.values()

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            val nameLowerCase = item.name.lowercase()

            binding.image.setImageResource(item.image)
            binding.title.text = nameLowerCase

            binding.container.setOnClickListener { view ->
                view.findNavController()
                    .navigate(CategoryFragmentDirections.actionCategoryToCategoryNews(nameLowerCase.replaceFirstChar { it.uppercase() }))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categories[position])

    override fun getItemCount(): Int = categories.size
}
