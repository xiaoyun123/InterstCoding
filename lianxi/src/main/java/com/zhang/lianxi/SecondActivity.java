package com.zhang.lianxi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhang.bean.MessageEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class SecondActivity extends Activity implements View.OnClickListener{

    private Button btn_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_accept=(Button)findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_accept:
                EventBus.getDefault().post("只有这样才能继续");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(String message){
        btn_accept.setText(message);
    }
}
