package com.zhang.interstcoding;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.zhang.adapter.MyAdapter3;
import com.zhang.bean.Bean;
import com.zhang.bean.Bean2;
import com.zhang.interface_interst.OkHttpIml;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BaseActivity{

    private ListView lv_view;
    private String url = "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?city_id=14&lat=40.04652&lng=116.306033&api_key=androidkey&sig=9317e9634b5fbc16078ab07abb6661c5&macid=45cd2478331b184ff0e15f29aaa89e3e&app=a-ajk&_pid=11738&o=PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys&from=mobile&m=Android-PE-TL10&cv=9.5.1&cid=14&i=864601026706713&v=4.4.2&qtime=20160411091603&pm=b61&uuid=1848c59c-185d-48d9-b0e9-782016041109&_chat_id=0";
//  private String url="http://www.oschina.net/action/api/news_list";
    private Button btn;
    private MyAdapter3 adapter3;
    private ArrayList<Bean.MyRows> list;
    private HashMap<String,String> hashList=new HashMap<String,String>();
    private ArrayList<Bean2.MyNew> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              getOkHttpGet(MainActivity.this, url,MainActivity.this);//Get请求
                hashList.put("pageIndex","0");
                hashList.put("catalog","1");
                hashList.put("pageSize","20");
//                getOkHttpPost(MainActivity.this, url, MainActivity.this, hashList);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initView() {
        lv_view = (ListView) findViewById(R.id.lv_view);
        btn = (Button) this.findViewById(R.id.btn);
    }

    @Override
    public void getOkHttpSuccess(String json) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        list= (ArrayList<Bean.MyRows>)invokeMethod(new Bean(),"getData",new Object[]{json});
        adapter3=new MyAdapter3(list,this);
        lv_view.setAdapter(adapter3);
//          list2=(ArrayList<Bean2.MyNew>)invokeMethod(new Bean2(),"getData2",new Object[]{json});
//        for (Bean2.MyNew myNews:list2){
//            Log.e("Main___",myNews.body);
//        }
    }

    @Override
    public void getOkHttpError(String message) {
        super.getOkHttpError(message);
    }
}