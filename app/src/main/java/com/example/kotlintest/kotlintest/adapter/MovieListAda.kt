package com.example.kotlintest.kotlintest.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kotlintest.kotlintest.R
import com.example.kotlintest.kotlintest.entity.MovieTop250Entity
import com.example.kotlintest.kotlintest.entity.SubjectsBean
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * 电影列表适配器
 */
class MovieListAda(layoutResId: Int, data: List<SubjectsBean>?) : BaseQuickAdapter<SubjectsBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(vh: BaseViewHolder?, item: SubjectsBean?) {
        if (item == null) return
        vh!!.setText(R.id.tv_movie_name, item.title)
                .setText(R.id.tv_movie_time, item.year)

        Glide.with(mContext).load(item.images.small).thumbnail(0.1f).into(vh.getView(R.id.iv_movie))

    }
}