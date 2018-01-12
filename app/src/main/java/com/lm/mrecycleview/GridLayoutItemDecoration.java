package com.lm.mrecycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lm on 2017/12/20.
 * Email:1002464056@qq.com
 * GridLayout样式的分割线
 */

public class GridLayoutItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;
    public GridLayoutItemDecoration(Context context, int drawableResourceId){
        mDivider= ContextCompat.getDrawable(context,drawableResourceId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //留出分割线的位置

//        outRect.bottom = mDivider.getIntrinsicHeight();
//        outRect.right = mDivider.getIntrinsicWidth();

        //留出分割线  如果是最底部 不要留 最右边 不要留
        int bottom = mDivider.getIntrinsicHeight();
        int right = mDivider.getIntrinsicWidth();
        if (isLastCloum(view,parent)){ //最后一列
            right=0;
        }
        if (isLastRow(view,parent)){ //最后一行
            bottom=0;
        }
        outRect.bottom=bottom;
        outRect.right=right;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        //绘制分割线
        drawHorizontal(canvas,parent);
        drawVertical(canvas,parent);



    }

    private boolean isLastCloum(View view, RecyclerView parent) {
        //获取当前位置
        //条目在父类中的位置
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        //获取列数

        int spanCount=getSpanCount(parent);

        return (currentPosition+1) % spanCount == 0;
    }

    /**
     * 获取recycleView的列数
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            return spanCount;
        }
        return 1;
    }

    private boolean isLastRow(View view, RecyclerView parent) {

        //当前位置+1 >(行数-1)*列数

        //列数
        int spanCount = getSpanCount(parent);

        //行数 = 总的条目 / 列数
        int rowNumber =parent.getAdapter().getItemCount() % spanCount==0 ?
                parent.getAdapter().getItemCount()/spanCount :(parent.getAdapter().getItemCount() / spanCount+1) ;

        //当前位置
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        return (currentPosition+1) > (rowNumber-1)*spanCount;
    }



    /**
     * 绘制垂直方向
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int childCount=parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View chilidView=parent.getChildAt(i);
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) chilidView.getLayoutParams();
            int left=chilidView.getRight()+ params.rightMargin;
            int right=left+mDivider.getIntrinsicWidth();
            int top=chilidView.getTop()- params.topMargin;
            int bottom=chilidView.getBottom()+ params.bottomMargin;
            //计算水平分割线的位置
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(canvas);
        }
    }

    /**
     * 绘制水平方向
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int childCount=parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View chilidView=parent.getChildAt(i);
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) chilidView.getLayoutParams();
            int left=chilidView.getLeft()- params.leftMargin;
            int right=chilidView.getRight()+mDivider.getIntrinsicWidth()+params.rightMargin;
            int top=chilidView.getBottom()+params.topMargin;
            int bottom=top+mDivider.getIntrinsicHeight();
            //计算水平分割线的位置
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(canvas);
        }

    }
}
