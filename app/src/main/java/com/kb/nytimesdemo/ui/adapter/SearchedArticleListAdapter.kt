package com.kb.moviedb.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kb.nytimes.model.Doc
import com.kb.nytimesdemo.databinding.ItemPostArticleBinding
import com.kb.nytimesdemo.utils.DATE_FORMAT
import com.kb.nytimesdemo.utils.NO_DATA
import com.kb.nytimesdemo.utils.RECEIVING_DATE_FORMAT
import java.text.SimpleDateFormat

class SearchedArticleListAdapter(private val onItemClicked: (position: Long) -> Unit) :
    RecyclerView.Adapter<SearchedArticleViewHolder>() {

    var articlesList = mutableListOf<Doc>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedArticleViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostArticleBinding.inflate(inflater, parent, false)
        return SearchedArticleViewHolder(parent.context, binding, articlesList, onItemClicked)
    }

    override fun onBindViewHolder(holder: SearchedArticleViewHolder, position: Int) {
        val article = articlesList[position]
//        with(holder.binding) {
//            tvTitle.text = article.headline.main
//            tvPublishDate.text = getFormattedDate(article.pub_date)
//            tvUrl.text = article.web_url
//        }

    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    fun addAll(articles: List<Doc>) {
        articlesList.clear()
        articlesList.addAll(articles)
        notifyDataSetChanged()
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