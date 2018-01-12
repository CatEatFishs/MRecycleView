package com.lm.mrecycleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lm.mrecycleview.commonAdapter.ItemClickListener;
import com.lm.mrecycleview.commonAdapter.MulitiTypeSupport;
import com.lm.mrecycleview.commonAdapter.RecycleCommonAdapter;
import com.lm.mrecycleview.commonAdapter.ViewHolder;
import com.lm.mrecycleview.wrapRecycleAdapter.WrapRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddHeadFootActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<AddHeadFootActivity.ChatData> datas;
    private AddHeadFootActivity.RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_head_foot);
        datas=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i%3==0){
                datas.add(new AddHeadFootActivity.ChatData("自己内容"+i,1));
            }else {
                datas.add(new AddHeadFootActivity.ChatData("朋友内容"+i,0));
            }
        }
        mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new AddHeadFootActivity.RecyclerAdapter(this,datas);
        //创建包裹adapter
        WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(mRecyclerAdapter);

        mRecycleView.setAdapter(wrapRecyclerAdapter);



        wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item,mRecycleView,false));

        wrapRecyclerAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item,mRecycleView,false));
        wrapRecyclerAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item,mRecycleView,false));




        mRecycleView.addItemDecoration(new LinearLayoutItemDecoration(this,R.drawable.linearitem));
        mRecyclerAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(AddHeadFootActivity.this, "点击了"+datas.get(position).getChatContent(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private class RecyclerAdapter extends RecycleCommonAdapter<AddHeadFootActivity.ChatData> {
        public RecyclerAdapter(Context context, List<AddHeadFootActivity.ChatData> data) {
            super(context, data, new MulitiTypeSupport<AddHeadFootActivity.ChatData>() {
                @Override
                public int getLayoutId(AddHeadFootActivity.ChatData item) {
                    if (item.isMe==1) {  //判断应该加载的那个布局
                        return R.layout.item_chat_me;
                    }
                    return  R.layout.item_chat_friend;
                }
            });
        }

        @Override
        protected void convert(ViewHolder holder, AddHeadFootActivity.ChatData item, int position) {

            if (item.isMe==1) {
                Log.d("TAG",item.isMe+"自己");
                holder.setText(R.id.chat_me,item.getChatContent());

            }else if (item.isMe==0){

                Log.d("TAG",item.isMe+"朋友"+item.getChatContent());
                holder.setText(R.id.chat_friend,item.getChatContent());

            }

            //holder.setText(R.id.chat_text,item.getChatContent());

        }
    }

    public class ChatData{
        public String chatContent;
        public int isMe; //判断是自己的内容 还是 别人的内容

        public ChatData(String chatContent, int isMe) {
            this.chatContent = chatContent;
            this.isMe = isMe;
        }

        public String getChatContent() {
            return chatContent;
        }

        public void setChatContent(String chatContent) {
            this.chatContent = chatContent;
        }

        public int getIsMe() {
            return isMe;
        }

        public void setIsMe(int isMe) {
            this.isMe = isMe;
        }
    }
}
