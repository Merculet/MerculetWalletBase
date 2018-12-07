package io.merculet.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import io.merculet.db.SourceEntity
import kotlinx.android.synthetic.main.layout_news_source_single.view.*

/**
 * Created by Aaron on 01/11/17.
 */
class NewsSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(source: SourceEntity, listener: (View, SourceEntity) -> Unit) = with(itemView) {
        tv_source_name.text = source.name
        tv_source_description.text = source.description
        tv_category.text = source.category
        itemView.setOnClickListener { listener(itemView, source) }
    }
}