package com.zhang.interface_interst;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhang on 2016/5/27.
 */
public interface OkHttpIml {

    /**
     * 请求成功
     * @param json
     */
    void getOkHttpSuccess(String json) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

    /**
     * 请求失败
     */
    void getOkHttpError(String message);

    /**
     * 隐藏进度条
     *
     */
    void progresssDialogHide();
    /**
     * 解析数据
     */

    Object invokeMethod(Object owner, String methodName, Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}