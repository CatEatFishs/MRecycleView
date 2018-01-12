package com.lm.mrecycleview.commonAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lm on 2017/12/21.
 * Email:1002464056@qq.com
 * recycleView的通用viewholder
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    //view的缓存集合
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
        mViews=new SparseArray<>();
    }

    public <T extends View>T getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null) {
            view=itemView.findViewById(viewId);
            if (view==null) {
                Log.d("TAG","这里为空");
            }
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * 给TextView设置文本，链式调用
     * @param viewId 控件id
     * @param text 文本内容
     * @return
     */
    public ViewHolder setText(int viewId,CharSequence text){
        TextView textView=getView(viewId);
        if (textView==null) {
            Log.d("TAG"," 空");
        }
        textView.setText(text);
        return this;
    }

    /**
     * 给Image设置图片资源
     */
    public ViewHolder setImageResource(int viewId,int resourceId){
        ImageView imageView=getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    /**
     * 给Image设置网络资源 因为加载图片的第三方不一样 所以不能写死
     */
    public ViewHolder setImagePath(int viewId,HolderImageLoad holderImageLoad){
        ImageView imageView=getView(viewId);
        holderImageLoad.imageLoad(imageView,holderImageLoad.getPath());
        return this;
    }

    public  abstract static  class HolderImageLoad{
        private String mPath;
        public HolderImageLoad(String path){
            this.mPath=path;
        }
        public abstract void imageLoad(ImageView imageView,String path);

        public String getPath(){
            return mPath;
        }

    }



}
