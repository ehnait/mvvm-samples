package com.galaxy.graduationproject2011.ui.adapter

import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.galaxy.graduationproject2011.R
import com.galaxy.graduationproject2011.entity.NewsList


/**
 * Created by Liam.Zheng on 3/24/21
 *
 * Des:
 */
class NewsAdapter : BaseQuickAdapter<NewsList.NewsItem, BaseViewHolder>(R.layout.item_news) {
    override fun convert(holder: BaseViewHolder, item: NewsList.NewsItem) {
        holder.setText(R.id.item_news_title, item.title.toString())
        holder.setText(R.id.item_news_digest, item.digest.toString())
        val newsImg = holder.getView<ImageView>(R.id.item_news_img)
        newsImg.load(item.imgsrc)
    }
}