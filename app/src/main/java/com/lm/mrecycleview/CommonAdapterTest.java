package com.lm.mrecycleview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lm.mrecycleview.commonAdapter.ImageLoad;
import com.lm.mrecycleview.commonAdapter.RecycleCommonAdapter;
import com.lm.mrecycleview.commonAdapter.ViewHolder;

import java.util.List;

/**
 * Created by Lm on 2017/12/21.
 * Email:1002464056@qq.com
 */

public class CommonAdapterTest extends RecycleCommonAdapter<String> {


    public CommonAdapterTest(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void convert(ViewHolder holder, String item, int position) {
        Log.d("TAG",item);
        //绑定数据  首先要找到view
        //方法一：最普通的方法
//        TextView tv = (TextView) holder.itemView.findViewById(R.id.tv);
//        tv.setText(item);
        //方法二：（一些特殊的文本设置还是得用这种方式设置 如有html设置颜色）
//        TextView tv=holder.getView(R.id.tv);
//        tv.setText(item);
        //方法三：
        holder.setText(R.id.tv,item);
        holder.setImageResource(R.id.iv,R.mipmap.ic_launcher);


        //加载图片
//        holder.setImagePath(R.id.iv,new ImageLoad("imagePath"));

    }
}
