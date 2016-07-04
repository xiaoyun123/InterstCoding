package com.zhang.bean;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Bean {
	public String status;
	public MyResult result;

	public class MyResult{
		public ArrayList<MyRows> rows;
	}
	public class MyRows{
		public String fang_type;
		public MyInfo info;
	}
	public class MyInfo{
		public String default_image;//图片
		public String loupan_name;//标题
		public String new_price_back;//价钱
		public String new_price_value;//价钱
		public String region_title;//地名
		public String sub_region_title;//地名
		public String tags;//详解
		public MyShow show_activity;
	}

	public class MyShow{
		public String title;
	}

	public ArrayList<Bean.MyRows> getData(String json){
		Gson gson = new Gson();
		Bean bean = gson.fromJson(json, Bean.class);
		ArrayList<Bean.MyRows> list = bean.result.rows;
		return list;
	}
}
