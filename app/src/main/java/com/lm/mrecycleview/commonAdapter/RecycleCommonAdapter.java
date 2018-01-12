package com.lm.mrecycleview.commonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lm.mrecycleview.R;

import java.util.List;

/**
 * Created by Lm on 2017/12/21.
 * Email:1002464056@qq.com
 * recycleView 万能Adapter
 */

public abstract class RecycleCommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    //条目的item
    private int mLayoutId;
    //参数传递用泛型
    private List<T> mData;
    //实例化View的LayoutInflater
    private LayoutInflater mInflater;
    private Context mContext;
    private MulitiTypeSupport mTypeSupport;

    public RecycleCommonAdapter(Context context,List<T> data,int layoutId){
        this.mContext=context;
        this.mData=data;
        this.mLayoutId=layoutId;
        mInflater=mInflater.from(mContext);

    }
    public RecycleCommonAdapter(Context context,List<T> data,MulitiTypeSupport mulitiTypeSupport){
        this(context,data,-1);
        this.mTypeSupport=mulitiTypeSupport;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mTypeSupport!=null) {
            mLayoutId=viewType;
        }
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        //先执行这个方法在执行onCreateViewHolder方法
        if (mTypeSupport!=null) {
            return mTypeSupport.getLayoutId(mData.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        convert(holder,mData.get(position),position);
        if (mItemClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClickListener(position);
                }
            });

        }
        if (mItemLongClickListener!=null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   return mItemLongClickListener.onItemLongClickListener(position);
                }
            });
        }
    }

    /**
     * 把必要参数传出去
     * @param holder ViewHolder
     * @param item 当前位置的条目
     * @param position 当前位置
     */
    protected abstract void convert(ViewHolder holder, T item, int position);


    @Override
    public int getItemCount() {
        return mData.size();
    }

    private ItemClickListener mItemClickListener;
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener=itemClickListener;
    }
    private ItemLongClickListener mItemLongClickListener;
    public void setOnItemLongClickListener(ItemLongClickListener itemLongClickListener){
        this.mItemLongClickListener=itemLongClickListener;
    }
}
