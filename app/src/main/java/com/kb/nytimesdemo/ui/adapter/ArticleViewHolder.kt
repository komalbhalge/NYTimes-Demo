package com.kb.moviedb.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kb.nytimes.model.Articles
import com.kb.nytimesdemo.databinding.ItemPostArticleBinding

class ArticleViewHolder(
    val context: Context?,
    val binding: ItemPostArticleBinding,
    val articles: List<Articles>,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    init {

        itemView.rootView.setOnClickListener { onItemClicked.invoke(absoluteAdapterPosition) }
        //binding.tvUrl.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //Handle item click
        when(v.id){
            //binding.tvUrl.id -> openURLInBrowser(binding.tvUrl.text.toString())
        }

    }
    fun openURLInBrowser(url:String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context?.startActivity(intent)
    }
}