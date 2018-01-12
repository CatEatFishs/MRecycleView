package com.lm.mrecycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lm.mrecycleview.commonAdapter.ItemClickListener;
import com.lm.mrecycleview.commonAdapter.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void adapter(View view) {
        //万能adapter界面
        Intent intent=new Intent(this,TYAdapterActivity.class);
        startActivity(intent);
    }

    public void mulitiType(View view) {
        //多布局界面
        Intent intent=new Intent(this,MuliteTypeActivity.class);
        startActivity(intent);
    }

    public void addHeadFoot(View view) {
        //添加头部底部
        Intent intent=new Intent(this,AddHeadFootActivity.class);
        startActivity(intent);
    }
}
