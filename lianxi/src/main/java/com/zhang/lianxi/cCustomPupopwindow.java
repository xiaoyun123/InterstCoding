package com.zhang.lianxi;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * @author LiXufei
 * @address BeiJing
 * @function 自定义弹出框
 */
public class cCustomPupopwindow extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_custom_pup);
        /*
         * 展示底部弹出框
		 */
        findViewById(R.id.customPup_bt).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showPup();
            }
        });
    }

    protected void showPup() {
        //获得pup的view
        View view = LayoutInflater.from(cCustomPupopwindow.this).inflate(R.layout.android_custom_pup_view, null, false);
        //设置window的宽高	1 window的布局	2、window的宽	3、window的高	4、window是否获取焦点
        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        //设置window背景色
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置window动画
        window.setAnimationStyle(R.style.custom_pup_style);
        //设置window在底部显示
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //获取view中的控件
        Button bt = (Button) view.findViewById(R.id.custom_view_bt);
        bt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(cCustomPupopwindow.this, "这是一个自定义pupopwindow", Toast.LENGTH_SHORT).show();
            }
        });
    }
}