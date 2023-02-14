package com.example.newsapp.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.databinding.ItemArticlePreviewBinding
import com.example.newsapp.models.Article
import com.example.newsapp.util.TimeAgo


class YouAdapter : RecyclerView.Adapter<YouAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(var viewBinding: ItemArticlePreviewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
    }


    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {



            Glide.with(this).load(article.urlToImage).apply(
                RequestOptions().transforms(
                    CenterCrop(), RoundedCorners(20)
                )).into( holder.viewBinding.ivArticleImage)

            val timeAgo = TimeAgo()

            val ago = timeAgo.covertTimeToText(article.publishedAt)



            holder.viewBinding.tvSource.text = article.author
            holder.viewBinding.tvTitle.text = article.title
            holder.viewBinding.tvPublishedAt.text = ago

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}













