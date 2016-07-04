package com.zhang.adapter;

import com.lidroid.xutils.BitmapUtils;
import com.zhang.bean.Bean;
import com.zhang.interstcoding.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter3 extends BaseAdapter {

	private ArrayList<Bean.MyRows> list;
	private Context context;
	private BitmapUtils utils;
	
	public MyAdapter3(ArrayList<Bean.MyRows> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		utils = new BitmapUtils(context);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){
			vh=new ViewHolder();
			convertView=View.inflate(context, R.layout.item,null);
			vh.iv_ima=(ImageView)convertView.findViewById(R.id.iv_ima);
			vh.tv_1=(TextView)convertView.findViewById(R.id.tv_1);
			vh.tv_2=(TextView)convertView.findViewById(R.id.tv_2);
			vh.tv_3=(TextView)convertView.findViewById(R.id.tv_3);
			vh.tv_4=(TextView)convertView.findViewById(R.id.tv_4);
			vh.tv_5=(TextView)convertView.findViewById(R.id.tv_5);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
			utils.display(vh.iv_ima, list.get(position).info.default_image);
			vh.tv_1.setText(list.get(position).info.loupan_name);
			vh.tv_2.setText(list.get(position).info.region_title+"-"+list.get(position).info.sub_region_title);
			vh.tv_3.setText(list.get(position).info.new_price_value+list.get(position).info.new_price_back);
			vh.tv_4.setText(list.get(position).info.tags);
			vh.tv_5.setText(list.get(position).info.show_activity.title);
		return convertView;
	}
	
	class ViewHolder{
		private ImageView iv_ima;
		private TextView tv_1;
		private TextView tv_2;
		private TextView tv_3;
		private TextView tv_4;
		private TextView tv_5;
	}

}
