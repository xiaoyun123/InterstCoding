package com.zhang.util;

import android.app.Activity;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhang.interface_interst.OkHttpIml;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhang on 2016/5/26.
 */
public class OkHttpUtil {

    /**
     * OkHttp的Get请求
     */
    public static void getOkHttpGet(final OkHttpIml ok, String url, final Activity context) {
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                ok.getOkHttpError(e.toString() + "IOException");
            }

            @Override
            public void onResponse(Response response) throws IOException {

                final String json = response.body().string();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ok.getOkHttpSuccess(json);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * OkHttp的Post请求
     */
    public static void getOkHttpPost(final OkHttpIml ok, String url, final Activity context,HashMap<String,String> hashList) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //获得这个集合的迭代器，保存在iter里
        Iterator iter = hashList.entrySet().iterator();
        while (iter.hasNext()) {
            //能获得map中的每一个键值对了
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            builder.add(key,value);
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request arg0, final IOException arg1) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ok.getOkHttpError(arg1.toString() + "IOException");
                    }
                });
            }
		
            @Override
            public void onResponse(Response arg0) throws IOException {
                final String json = arg0.body().string();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ok.getOkHttpSuccess(json);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}