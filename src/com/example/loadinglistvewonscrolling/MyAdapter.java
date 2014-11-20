package com.example.loadinglistvewonscrolling;


import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	Context mContext;
	int size;
	List<String> list;
	public MyAdapter(Context mContext, int size,List<String> list) {
       this.mContext=mContext;
       this.size=size;
       this.list=list;
	}

	@Override
	public int getCount() {
		Log.d("getCount",size+"");
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	class MyHolder {
		TextView textView;
		public MyHolder( View v) {
			textView=(TextView)v.findViewById(R.id.text_view);
		}
	}
  	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=convertView;
		MyHolder holder;
		if(view ==null){
			LayoutInflater inflater=LayoutInflater.from(mContext);
			view=inflater.inflate(R.layout.list_contents, parent, false);
			holder=new MyHolder(view);
			view.setTag(holder);
		}else{
			holder=(MyHolder) view.getTag();
		}
		holder.textView.setText(list.get(position));
		return view;
	}

	

}
