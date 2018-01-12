# MRecycleView
万能recycleViewAdapter,支持多布局，支持添加头部

普通使用：
  recycleview = (RecyclerView) findViewById(R.id.recycleview);
  recycleview.setLayoutManager(new LinearLayoutManager(this));
  commonAdapter = new CommonAdapterTest(this,mData, R.layout.item);
  recycleview.setAdapter(commonAdapter);
多布局使用：
  mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
  mRecycleView.setLayoutManager(new LinearLayoutManager(this));
  mRecyclerAdapter = new RecyclerAdapter(this,datas);
  mRecycleView.setAdapter(mRecyclerAdapter);
  
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
 添加头尾布局：
   mRecycleView = (RecyclerView) findViewById(R.id.recycleview);
   mRecycleView.setLayoutManager(new LinearLayoutManager(this));
   mRecyclerAdapter = new AddHeadFootActivity.RecyclerAdapter(this,datas);
   //创建包裹adapter
   WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(mRecyclerAdapter);
   mRecycleView.setAdapter(wrapRecyclerAdapter);
   //添加头布局
   wrapRecyclerAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item,mRecycleView,false));
   //添加尾布局
   wrapRecyclerAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item,mRecycleView,false));
        
