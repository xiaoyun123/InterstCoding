package com.zhang.interstcoding;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zhang.interface_interst.OkHttpIml;
import com.zhang.util.Alert;
import com.zhang.util.OkHttpUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BaseActivity extends FragmentActivity implements OkHttpIml{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void getOkHttpGet(final OkHttpIml ok, String url,Activity context) {
        Alert.customDialog(this, "正在获取信息");
        OkHttpUtil.getOkHttpGet(this, url, context);
    }

    public void getOkHttpPost(final OkHttpIml ok, String url,Activity context,HashMap<String,String> hashList) {
        Alert.customDialog(this,"正在获取信息");
        OkHttpUtil.getOkHttpPost(this, url, context, hashList);
    }

    @Override
    public void getOkHttpSuccess(String json) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

    }

    @Override
    public void getOkHttpError(String message) {
        progresssDialogHide();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.e("Main",message);
    }

    /**
     * 对话框消失
     */
    @Override
    public void progresssDialogHide() {
        if (Alert.dialog != null) {
            Alert.dialog.dismiss();
        }
    }

    public Object invokeMethod(Object owner, String methodName, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        progresssDialogHide();

        Class ownerClass = owner.getClass();

        Class[] argsClass = new Class[args.length];

        for(int i=0, j=args.length; i<j; i++){
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);
        //Method method = ownerClass.getMethod(methodName, argsClass)
        // 通过methodName和参数的argsClass（方法中的参数类型集合）
        // 数组得到要执行的Method
        return method.invoke(owner, args);
    }
}