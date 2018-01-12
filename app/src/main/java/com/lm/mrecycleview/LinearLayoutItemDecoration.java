package com.lm.mrecycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lm on 2017/12/20.
 * Email:1002464056@qq.com
 * linearLayout样式的分割线
 */

public class LinearLayoutItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;//分割
    public LinearLayoutItemDecoration(Context context,int drawableResourceId){
        mDivider= ContextCompat.getDrawable(context,drawableResourceId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //代表在item底部留出10px

        int position=parent.getChildAdapterPosition(view);
        //        parent.getChildCount()是不断变化的，不能保证最后一条
//        if (position!=parent.getChildCount()-1) {
//            outRect.bottom=10;
//        }
        //但是能保证第一条
        if (position!=0) {
            outRect.top=mDivider.getIntrinsicHeight();
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int chaildCount=parent.getChildCount();
        Rect rect=new Rect();
        rect.left=parent.getPaddingLeft();
        rect.right=parent.getWidth()-parent.getPaddingRight();
        for (int i = 1; i < chaildCount ; i++) {
            rect.bottom=parent.getChildAt(i).getTop();
            rect.top=rect.bottom-mDivider.getIntrinsicHeight();
            mDivider.setBounds(rect);
            mDivider.draw(canvas);

        }


    }
}
