package com.lm.mrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lm.mrecycleview.commonAdapter.ItemClickListener;
import com.lm.mrecycleview.commonAdapter.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class TYAdapterActivity extends AppCompatActivity {


    private RecyclerView recycleview;
    private List<String> mData;
    private RecycleViewAdapter recycleViewAdapter;
    private CommonAdapterTest commonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyadapter);
        initData();
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
//        recycleViewAdapter = new RecycleViewAdapter(mData,this);
//        recycleViewAdapter = new RecycleViewAdapter(this,mData,R.layout.item);
        commonAdapter = new CommonAdapterTest(this,mData, R.layout.item);


        /*
        * linearLayout 设计分割线

        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(recycleViewAdapter);
//        recycleview.addItemDecoration(new SimpleLinearItemDecoration());//linearlayout简单的分割线
        recycleview.addItemDecoration(new GridLayoutItemDecoration(this,R.drawable.linearitem));//linearlayout加载图片分割线
*/


        /**
         * 测试通用adapter
         */
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(commonAdapter);
        recycleview.addItemDecoration(new GridLayoutItemDecoration(this,R.drawable.linearitem));//linearlayout加载图片分割线
        commonAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(TYAdapterActivity.this, "点击条目"+mData.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        commonAdapter.setOnItemLongClickListener(new ItemLongClickListener() {
            @Override
            public boolean onItemLongClickListener(int position) {
                Toast.makeText(TYAdapterActivity.this, "长按点击条目"+mData.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        /**
         * GridLayout设计分割线

         recycleview.setLayoutManager(new GridLayoutManager(this,3));
         recycleview.setAdapter(recycleViewAdapter);
         recycleview.addItemDecoration(new GridLayoutItemDecoration(this,R.drawable.linearitem));//GridLayout加载图片分割线
         */


        //******************************************
//        recycleViewAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(MainActivity.this, "点击条目"+mData.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    private void initData(){
        mData=new ArrayList<>();
        for (int i = 'A'; i <'Z' ; i++) {
            mData.add(""+(char)i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recyclemanage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list:
                recycleview.setLayoutManager(new LinearLayoutManager(this));

                // recycleview.addItemDecoration(new LinearLayoutItemDecoration(this,R.drawable.linearitem));//linearlayout加载图片分割线

                break;
            case R.id.grid:
                recycleview.setLayoutManager(new GridLayoutManager(this,3));
                //recycleview.addItemDecoration(new GridLayoutItemDecoration(this,R.drawable.linearitem));//GridLayout加载图片分割线
                break;
        }

        return true;
    }

    public void adapter(View view) {
    }

    public void mulitiType(View view) {
    }
}
