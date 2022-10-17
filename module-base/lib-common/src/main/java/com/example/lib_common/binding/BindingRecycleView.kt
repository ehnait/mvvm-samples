package com.example.lib_common.binding

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("itemTopPadding", "itemLeftPadding", "itemBottomPadding", "itemRightPadding",requireAll = false)
fun RecyclerView.addItemPadding(top: Int = 0, left: Int = 0, bottom: Int = 0, right: Int = 0) {
    addItemDecoration(SpaceItemDecoration(top, left, bottom, right))
}

@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}

class SpaceItemDecoration(private val top: Int = 0, private val left: Int = 0, private val bottom: Int = 0, private val right: Int = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (top > 0) outRect.top = top
        if (left > 0) outRect.left = left
        if (bottom > 0) outRect.bottom = bottom
        if (right > 0) outRect.right = right
    }
}