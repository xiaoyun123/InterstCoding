package com.zhang.lianxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhang.bean.MessageEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);
        tv_name = (TextView) findViewById(R.id.tv_name);
        btn.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(String message){
        tv_name.setText(message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                EventBus.getDefault().post("事儿");
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //反注册EventBus
        EventBus.getDefault().unregister(this);
    }
}