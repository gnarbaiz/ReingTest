package com.gnarbaiz.reingtest.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gnarbaiz.reingtest.R
import com.gnarbaiz.reingtest.networking.responses.Article
import com.gnarbaiz.reingtest.utils.TimeAgoUtil


class ArticleAdapter(
    private val context: Context,
    private val articlesList: ArrayList<Article>,
    private val startWebViewActivity: (String) -> Unit
) :
    RecyclerView.Adapter<ArticleAdapter.ArticleItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        return ArticleItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    fun getData(): ArrayList<Article> {
        return articlesList
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        val article = articlesList[position]
        holder.tvTitle.text = article.story_title?.let { it } ?: article.title
        holder.tvAuthor.text = article.author
        holder.tvDate.text = TimeAgoUtil.covertTimeToText(article.created_at)

        holder.itemView.setOnClickListener {
            article.story_url?.let {
                startWebViewActivity(it)
            }
        }
    }

    fun removeItem(position: Int) {
        articlesList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Article, position: Int) {
        articlesList.add(position, item)
        notifyItemInserted(position)
    }

    open class ArticleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.txtTitle)
        var tvAuthor: TextView = itemView.findViewById(R.id.txtAuthor)
        var tvDate: TextView = itemView.findViewById(R.id.txtDate)

    }

}