package com.example.chatui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MsgContentBean> datas = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommonAdapter<MsgContentBean> adapter;
    private EditText etInput;
    private final int RECIEVE = 1;
    private final int SEND = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        datas.add(new MsgContentBean(RECIEVE,"晚上去看电影啊"));
        datas.add(new MsgContentBean(RECIEVE,"有空吗"));
        datas.add(new MsgContentBean(SEND,"我看看有没有时间吧，我很忙的"));

        etInput = (EditText) findViewById(R.id.et_input);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new CommonAdapter<MsgContentBean>(getApplicationContext(),R.layout.item_chat,datas) {
            @Override
            protected void convert(ViewHolder holder, MsgContentBean msgContentBean, int position) {
                RelativeLayout llRecieve = holder.getView(R.id.rl_receive);
                LinearLayout llSend = holder.getView(R.id.ll_send);
                if(msgContentBean.getType() == 0){
                    llRecieve.setVisibility(View.GONE);
                    holder.setText(R.id.tv_msg_send,msgContentBean.getContent());
                }else{
                    llSend.setVisibility(View.GONE);
                    holder.setText(R.id.tv_msg_receive,msgContentBean.getContent());
                }

            }
        };
        recyclerView.setAdapter(adapter);
    }

    public void sendMsg(View view){
        String content = etInput.getText().toString().trim();
        datas.add(new MsgContentBean(SEND,content));
        adapter.notifyDataSetChanged();

        etInput.setText("");
        //滚动到底部
        recyclerView.smoothScrollToPosition(datas.size() - 1);
    }
}
