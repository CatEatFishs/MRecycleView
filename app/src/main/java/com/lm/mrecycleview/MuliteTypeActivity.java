package com.lm.mrecycleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.lm.mrecycleview.commonAdapter.ItemClickListener;
import com.lm.mrecycleview.commonAdapter.MulitiTypeSupport;
import com.lm.mrecycleview.commonAdapter.RecycleCommonAdapter;
import com.lm.mrecycleview.commonAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 多布局应用
 */
public class MuliteTypeActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<ChatData> datas;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulite_type);
        datas=new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i%3==0){
                datas.add(new ChatData("自己内容"+i,1));
            }else {
                datas.add(new ChatData("朋友内容"+i,0));
            }
        }
        mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter = new RecyclerAdapter(this,datas);
        mRecycleView.setAdapter(mRecyclerAdapter);
        mRecycleView.addItemDecoration(new LinearLayoutItemDecoration(this,R.drawable.linearitem));
        mRecyclerAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(MuliteTypeActivity.this, "点击了"+datas.get(position).getChatContent(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private class RecyclerAdapter extends RecycleCommonAdapter<ChatData>{
        public RecyclerAdapter(Context context, List<ChatData> data) {
            super(context, data, new MulitiTypeSupport<ChatData>() {
                @Override
                public int getLayoutId(ChatData item) {

                    if (item.isMe==1) {  //判断应该加载的那个布局
                        return R.layout.item_chat_me;
                    }
                    return  R.layout.item_chat_friend;
                }
            });
        }

        @Override
        protected void convert(ViewHolder holder, ChatData item, int position) {
            if (item.isMe==1) {
                holder.setText(R.id.chat_me,item.getChatContent());
            }else if (item.isMe==0){
                holder.setText(R.id.chat_friend,item.getChatContent());
            }
            Log.d("TAG",item.isMe+"");
//            holder.setText(R.id.chat_friend,item.getChatContent());

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
