package com.example.ashipdalauncher;


import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.ashipdalauncher.util.LogUtil;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AnotherApp extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another_apps);
		//Button returnBtn = (Button) findViewById(R.id.btnRecentApp);

		//모든어플받아오는 인텐트
		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		final List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
		MyAdapter adapter = new MyAdapter(this, R.layout.listitem, pkgAppsList);
		ListView lv = (ListView) findViewById(R.id.applistview);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				ResolveInfo temp = pkgAppsList.get(position);
				Intent i = new Intent();
				i.setClassName(temp.activityInfo.packageName, temp.activityInfo.name);
				startActivity(i);
			}
		});
		
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<RecentTaskInfo> taskInfo = am.getRecentTasks(10,  Intent.FLAG_ACTIVITY_NEW_TASK);
		MyAdapter2 adapter2 = new MyAdapter2(this, R.layout.listitem, taskInfo);
		ListView lv_recentApp = (ListView) findViewById(R.id.recentApp);
		lv_recentApp.setAdapter(adapter2);
		/*
		lv_recentApp.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				RecentTaskInfo temp = pkgAppsList.get(position);
				Intent i = new Intent();
				i.setClassName(temp.activityInfo.packageName, temp.activityInfo.name);
				startActivity(i);
			}
		});
	    컴파일하려고 넣음 ㅇㅇ*/	
		
//		PackageManager pm = getPackageManager();
//		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//		List<ActivityManager.RecentTaskInfo> taskInfo = am.getRecentTasks(10,  Intent.FLAG_ACTIVITY_NEW_TASK);
//		if(taskInfo != null){
//			for(int i=0; i<taskInfo.size(); i++)
//			{
//				RecentTaskInfo recent = taskInfo.get(i);
//				Intent intent = recent.baseIntent;
//				ComponentName cn = intent.getComponent();
//				
//				HashMap<String, Object> taskItem=new HashMap<String, Object>();
//				
//				try{
//					taskItem.put("icon", pm.getApplicationIcon(cn.getPackageName()));
//				}
//				catch(NameNotFoundException e){
//					e.printStackTrace();
//				}
//				
//				taskItem.put("LabelName", pm.getApplicationLabel(getApplicationInfo()));
				
				

//				taskItem.put("PackageName", cn.getPackageName());
//				taskItem.put("ClassName", cn.getClassName());
//				taskItem.put("shortClassName", cn.getShortClassName());
//				taskArrayL.add(taskItem);
//			}
//		}
		
	}

	
	public class MyAdapter extends ArrayAdapter<ResolveInfo> {

		List<ResolveInfo> child;

		public MyAdapter(Context context, int textViewResourceId, List<ResolveInfo> objects) {
			super(context, textViewResourceId, objects);
			child = objects;
		}




		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return child.size();
		}

		@Override
		public ResolveInfo getItem(int position) {
			// TODO Auto-generated method stub
			return child.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				convertView = li.inflate(R.layout.listitem, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.ivIcon);
			TextView name = (TextView) convertView.findViewById(R.id.tvName);

			ResolveInfo temp = child.get(position);
			icon.setImageDrawable(temp.loadIcon(getPackageManager()));
			name.setText(temp.loadLabel(getPackageManager()));


			return convertView;
		}

	}
	 
	 public class MyAdapter2 extends ArrayAdapter<RecentTaskInfo>{
		 List<RecentTaskInfo> child;


		 public MyAdapter2(Context context, int textViewResourceId, List<RecentTaskInfo> objects) {
			 super(context, textViewResourceId, objects);
			 child = objects;
		 }

		 @Override
		 public int getCount() {
			 // TODO Auto-generated method stub
			 return child.size();
		 }

		 @Override
		 public RecentTaskInfo getItem(int position) {
			 // TODO Auto-generated method stub
			 return child.get(position);
		 }

		 @Override
		 public View getView(int position, View convertView, ViewGroup parent) {
			 if(convertView == null) {
				 LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				 convertView = li.inflate(R.layout.listitem, null);
			 }
			 ImageView icon = (ImageView) convertView.findViewById(R.id.ivIcon);
			 TextView name = (TextView) convertView.findViewById(R.id.tvName);

			 RecentTaskInfo temp = child.get(position);


			 //		   icon.setImageDrawable(temp.loadIcon(getPackageManager()));
			 //		   name.setText(temp.loadLabel(getPackageManager()));


			 return convertView;
		 }
	 }
}
