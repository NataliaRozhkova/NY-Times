package com.example.nytimes;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class NewsItemDecoration extends DividerItemDecoration {
    private int offset;

    public NewsItemDecoration(Context context, int orientation, int offset) {
        super(context, orientation);
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {


        outRect.bottom = offset;
        outRect.left = offset;
        outRect.right = offset;
        outRect.top = offset;

    }

}
