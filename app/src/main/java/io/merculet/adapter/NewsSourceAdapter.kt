package io.merculet.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.merculet.adapter.viewholder.NewsSourceViewHolder
import io.merculet.db.SourceEntity
import io.merculet.R
import io.merculet.inflate

/**
 * Created by Aaron on 01/11/17.
 */
class NewsSourceAdapter(private val listener: (View, SourceEntity) -> Unit, private var sourceList: List<SourceEntity>) : RecyclerView.Adapter<NewsSourceViewHolder>() {


    override fun getItemCount(): Int {
        return sourceList.size
    }

    private fun getItem(position: Int): SourceEntity {
        return sourceList[position]
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) =
            holder.bind(getItem(position), listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder =
            NewsSourceViewHolder(parent.inflate(R.layout.layout_news_source_single))

    fun updateDataSet(data: List<SourceEntity>){
        sourceList = data
        notifyDataSetChanged()
    }
}