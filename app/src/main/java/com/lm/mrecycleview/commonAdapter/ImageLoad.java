package com.lm.mrecycleview.commonAdapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lm.mrecycleview.R;

/**
 * Created by Lm on 2017/12/22.
 * Email:1002464056@qq.com
 */

public class ImageLoad extends ViewHolder.HolderImageLoad {
    public ImageLoad(String path) {
        super(path);
    }

    @Override
    public void imageLoad(ImageView imageView, String path) {
        //用第三方的glide加载图片，在这里更换第三方
        Glide.with(imageView.getContext()).load(path).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
