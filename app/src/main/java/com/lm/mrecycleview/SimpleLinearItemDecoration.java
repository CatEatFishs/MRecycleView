package com.lm.mrecycleview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lm on 2017/12/20.
 * Email:1002464056@qq.com
 * linearLayout 简单分割线
 */

public class SimpleLinearItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    public SimpleLinearItemDecoration(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    /**
     * 在哪里留出分割线位置
     */
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
            outRect.top=10;
        }
    }

    /**
     * 绘制分割线
     */
    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int chaildCount=parent.getChildCount();
        Rect rect=new Rect();
        rect.left=parent.getPaddingLeft();
        rect.right=parent.getWidth()-parent.getPaddingRight();
        for (int i = 1; i < chaildCount ; i++) {
            rect.bottom=parent.getChildAt(i).getTop();
            rect.top=rect.bottom-10;
            canvas.drawRect(rect,mPaint);
        }


    }
}
