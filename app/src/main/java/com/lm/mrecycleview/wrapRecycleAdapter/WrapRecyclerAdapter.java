package com.lm.mrecycleview.wrapRecycleAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lm on 2017/12/22.
 * Email:1002464056@qq.com
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private RecyclerView.Adapter mAdapter;
//
//    //头部和底部的集合
//    private SparseArray<View> mHeaders,mFooters;
//
//    private static int BASE_HEADER_KEY=1000000;//随便赋值
//    private static int BASE_FOOTER_KEY=2000000;//随便赋值
//
//    public WrapRecyclerAdapter(RecyclerView.Adapter adapter){
//        this.mAdapter=adapter;
//        mHeaders=new SparseArray<>();
//        mFooters=new SparseArray<>();
//    }
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //区分头部还是底部 还是列表，只能根据viewType来
//        if (mHeaders.indexOfKey(viewType)>=0) {
//            //头部
//            return creatHeaderFooterViewHolder(mHeaders.get(viewType));
//
//        }else if (mFooters.indexOfKey(viewType)>=0){
//
//            //底部
//            return creatHeaderFooterViewHolder(mFooters.get(viewType));
//        }
//
//        return mAdapter.onCreateViewHolder(parent,viewType);
//    }
//
//    private RecyclerView.ViewHolder creatHeaderFooterViewHolder(View view) {
//        return new RecyclerView.ViewHolder(view){
//
//        };
//    }
//
//
//
//    @Override
//    public int getItemViewType(int position) {
//        //根据当前位置判断type
//
//        // Header (negative positions will throw an IndexOutOfBoundsException)
//        int numHeaders = mHeaders.size();
//        if (position < numHeaders) {
//            return mHeaders.keyAt(position);
//        }
//
//        // Adapter
//        final int adjPosition = position - numHeaders;
//        int adapterCount = 0;
//        if (mAdapter != null) {
//            adapterCount = mAdapter.getItemCount();
//            if (adjPosition < adapterCount) {
//                return mAdapter.getItemViewType(adjPosition);
//            }
//        }
//
//        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
//        return mFooters.keyAt(adjPosition - adapterCount);
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        //头部和底部不需要绑定数据
//        int numHeaders = mHeaders.size();
//        if (position < numHeaders) {
//            return;
//        }
//
//        // Adapter
//        final int adjPosition = position - numHeaders;
//        int adapterCount = 0;
//        if (mAdapter != null) {
//            adapterCount = mAdapter.getItemCount();
//            if (adjPosition < adapterCount) {
//                mAdapter.onBindViewHolder(holder,position);
//            }
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mHeaders.size()+mAdapter.getItemCount()+mFooters.size();
//    }
//
//    //添加头部
//    public void addHeaderView(View view){
//        if (mHeaders.indexOfValue(view) == -1) {
//            //没有就添加到集合
//            mHeaders.put(BASE_HEADER_KEY++,view);
//        }
//    }
//    //添加底部
//    public void addFooterView(View view){
//        if (mFooters.indexOfValue(view) == -1) {
//            //没有就添加到集合
//            mFooters.put(BASE_FOOTER_KEY++,view);
//        }
//    }
//
//    //移除头部
//    public void removeHeaderView(View view){
//        if (mHeaders.indexOfValue(view)>=0) {
//            mHeaders.removeAt(mHeaders.indexOfValue(view));
//        }
//    }
//    //移除底部
//    public void removeFooterView(View view){
//        if (mFooters.indexOfValue(view)>=0) {
//            mFooters.removeAt(mFooters.indexOfValue(view));
//        }
//    }
//
//}

private final static String TAG = "WrapRecyclerAdapter";
    // 用来存放底部和头部View的集合  比Map要高效一些
    // 可以点击进入看一下官方的解释
    /**
     * SparseArrays map integers to Objects.  Unlike a normal array of Objects,
     * there can be gaps in the indices.  It is intended to be more memory efficient
     * than using a HashMap to map Integers to Objects, both because it avoids
     * auto-boxing keys and its data structure doesn't rely on an extra entry object
     * for each mapping.
     */
    private SparseArray<View> mHeaderViews;
    private SparseArray<View> mFooterViews;

    // 基本的头部类型开始位置  用于viewType
    private static int BASE_ITEM_TYPE_HEADER = 10000000;
    // 基本的底部类型开始位置  用于viewType
    private static int BASE_ITEM_TYPE_FOOTER = 20000000;

    // 列表的Adapter
    private RecyclerView.Adapter mAdapter;

    public WrapRecyclerAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mHeaderViews = new SparseArray<>();
        mFooterViews = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // viewType 可能就是 SparseArray 的key
        if (isHeaderViewType(viewType)) {
            View headerView = mHeaderViews.get(viewType);
            return createHeaderFooterViewHolder(headerView);
        }

        if (isFooterViewType(viewType)) {
            View footerView = mFooterViews.get(viewType);
            return createHeaderFooterViewHolder(footerView);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * 是不是底部类型
     */
    private boolean isFooterViewType(int viewType) {
        int position = mFooterViews.indexOfKey(viewType);
        return position >= 0;
    }

    /**
     * 创建头部或者底部的ViewHolder
     */
    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {

        };
    }

    /**
     * 是不是头部类型
     */
    private boolean isHeaderViewType(int viewType) {
        int position = mHeaderViews.indexOfKey(viewType);
        return position >= 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderPosition(position) || isFooterPosition(position)) {
            return;
        }
        // 计算一下位置
        position = position - mHeaderViews.size();
        mAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            // 直接返回position位置的key
            return mHeaderViews.keyAt(position);
        }
        if (isFooterPosition(position)) {
            // 直接返回position位置的key
            position = position - mHeaderViews.size() - mAdapter.getItemCount();
            return mFooterViews.keyAt(position);
        }
        // 返回列表Adapter的getItemViewType
        position = position - mHeaderViews.size();
        return mAdapter.getItemViewType(position);
    }

    /**
     * 是不是底部位置
     */
    private boolean isFooterPosition(int position) {
        return position >= (mHeaderViews.size() + mAdapter.getItemCount());
    }

    /**
     * 是不是头部位置
     */
    private boolean isHeaderPosition(int position) {
        return position < mHeaderViews.size();
    }

    @Override
    public int getItemCount() {
        // 条数三者相加 = 底部条数 + 头部条数 + Adapter的条数
        return mAdapter.getItemCount() + mHeaderViews.size() + mFooterViews.size();
    }

    /**
     * 获取列表的Adapter
     */
    private RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    // 添加头部
    public void addHeaderView(View view) {
        int position = mHeaderViews.indexOfValue(view);
        if (position < 0) {
            mHeaderViews.put(BASE_ITEM_TYPE_HEADER++, view);
        }
        notifyDataSetChanged();
    }

    // 添加底部
    public void addFooterView(View view) {
        int position = mFooterViews.indexOfValue(view);
        if (position < 0) {
            mFooterViews.put(BASE_ITEM_TYPE_FOOTER++, view);
        }
        notifyDataSetChanged();
    }

    // 移除头部
    public void removeHeaderView(View view) {
        int index = mHeaderViews.indexOfValue(view);
        if (index < 0) return;
        mHeaderViews.removeAt(index);
        notifyDataSetChanged();
    }

    // 移除底部
    public void removeFooterView(View view) {
        int index = mFooterViews.indexOfValue(view);
        if (index < 0) return;
        mFooterViews.removeAt(index);
        notifyDataSetChanged();
    }

    /**
     * 解决GridLayoutManager添加头部和底部不占用一行的问题
     *
     * @param recycler
     * @version 1.0
     */
//    public void adjustSpanSize(RecyclerView recycler) {
//        if (recycler.getLayoutManager() instanceof GridLayoutManager) {
//            final GridLayoutManager layoutManager = (GridLayoutManager) recycler.getLayoutManager();
//            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    boolean isHeaderOrFooter =
//                            isHeaderPosition(position) || isFooterPosition(position);
//                    return isHeaderOrFooter ? layoutManager.getSpanCount() : 1;
//                }
//            });
//        }
//    }
}
