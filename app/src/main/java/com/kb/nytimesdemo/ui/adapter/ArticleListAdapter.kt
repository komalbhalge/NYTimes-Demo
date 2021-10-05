package com.kb.moviedb.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kb.nytimes.model.Articles
import com.kb.nytimesdemo.databinding.ItemPostArticleBinding
import com.kb.nytimesdemo.utils.DATE_FORMAT
import com.kb.nytimesdemo.utils.NO_DATA
import com.kb.nytimesdemo.utils.RECEIVING_DATE_FORMAT
import java.text.SimpleDateFormat

class ArticleListAdapter(
    private val onItemClicked: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    var articlesList = mutableListOf<Articles>()

    fun setArticles(articles: List<Articles>) {
        this.articlesList = articles.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(parent.context, binding, articlesList, onItemClicked)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articlesList[position]
        with(holder.binding) {
            itemArticleTitle.text = article.title
            itemPostDescription.text = article.abstract_tx
            itemPostAuthor.text = getFormattedDate(article.published_date)
            val media = article.media
            if (!media.isNullOrEmpty()) {
                val metadata =media.get(0).media_metadata
                if (!metadata.isNullOrEmpty()) {
                    Glide.with(itemView.context)
                        .load(metadata.get(0).url)
                        .into(itemArticleImage)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    @SuppressLint("SimpleDateFormat")
    private fun getFormattedDate(date: String?): String {
        date?.let {
            val parser = SimpleDateFormat(RECEIVING_DATE_FORMAT)
            val formatter = SimpleDateFormat(DATE_FORMAT)
            return formatter.format(parser.parse(date))
        }
        return NO_DATA
    }

}