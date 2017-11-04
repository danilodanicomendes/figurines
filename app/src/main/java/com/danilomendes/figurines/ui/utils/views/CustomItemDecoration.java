package com.danilomendes.figurines.ui.utils.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by danilo on 30-10-2017.
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {
    private final int mHeightPx;

    public CustomItemDecoration(int heightPx) {
        this.mHeightPx = heightPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 0);
        if (parent != null && view != null) {
            int childPos = parent.getChildAdapterPosition(view);
            int itemCount = parent.getAdapter().getItemCount();

            if (childPos >= 0 && childPos < itemCount - 1) {
                outRect.bottom = mHeightPx;
            }
        }
    }
}
