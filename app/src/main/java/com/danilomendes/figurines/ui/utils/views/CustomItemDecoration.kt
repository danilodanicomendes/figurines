package com.danilomendes.figurines.ui.utils.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by danilo on 30-10-2017.
 */
class CustomItemDecoration(private val mHeightPx: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect.set(0, 0, 0, 0)
        if (parent != null && view != null) {
            val childPos = parent.getChildAdapterPosition(view)
            val itemCount = parent.adapter.itemCount

            if (childPos >= 0 && childPos < itemCount - 1) {
                outRect.bottom = mHeightPx
            }
        }
    }
}
