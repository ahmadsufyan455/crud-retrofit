package com.zerodev.crudretrofit.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zerodev.crudretrofit.databinding.ArticleItemBinding
import com.zerodev.crudretrofit.model.Article
import com.zerodev.crudretrofit.view.UpdateArticleActivity

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val articles = ArrayList<Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun setArticles(list: List<Article>) {
        articles.clear()
        articles.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArticleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.count()

    class ViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.title.text = article.title
            binding.description.text = article.description

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, UpdateArticleActivity::class.java).apply {
                    putExtra(UpdateArticleActivity.EXTRA_ID, article)
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}