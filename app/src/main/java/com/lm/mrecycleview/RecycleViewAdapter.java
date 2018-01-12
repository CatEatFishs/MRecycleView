package com.lm.mrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lm.mrecycleview.commonAdapter.RecycleCommonAdapter;
import com.lm.mrecycleview.commonAdapter.ViewHolder;

import java.util.List;

/**
 * Created by Lm on 2017/12/18.
 * Email:1002464056@qq.com
 */



public class RecycleViewAdapter extends RecycleCommonAdapter<String> {

    public RecycleViewAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void convert(ViewHolder holder, String item, int position) {
        Log.d("TAG",item);
        //绑定数据  首先要找到view
//        TextView tv = holder.itemView.findViewById(R.id.tv);
        TextView textView=holder.itemView.findViewById(R.id.tv);
        textView.setText(item);
    }


}

//
//public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
//
//    private List<String> mData;
//    private Context mContext;
//
//    public RecycleViewAdapter(List<String> mData,Context context) {
//        this.mContext=context;
//        this.mData = mData;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
//        ViewHolder viewHolder=new ViewHolder(itemView);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.itemTv.setText(mData.get(position));
//        Log.d("TAG",mData.get(position));
//
//        if (mItemClickListener!=null) {
//            holder.itemTv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mItemClickListener.onItemClick(position);
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView itemTv;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            itemTv=itemView.findViewById(R.id.tv);
//        }
//
//    }
//
//
//    private OnItemClickListener mItemClickListener;
//    public void setOnItemClickListener(OnItemClickListener itemClickListener){
//        this.mItemClickListener=itemClickListener;
//    }
//    public interface OnItemClickListener{
//        public void onItemClick(int position);
//    }
//}
