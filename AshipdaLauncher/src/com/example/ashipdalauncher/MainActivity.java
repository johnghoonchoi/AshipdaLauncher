package com.example.ashipdalauncher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity implements OnClickListener, OnCheckedChangeListener  {
	
	private SimpleDateFormat mClockFormat;
	private TextView mClock;

	
	//send wifi state 
	public static boolean isWifiEnabled(Context context) {

		WifiManager wifiMgr = (WifiManager)context
		.getSystemService(Context.WIFI_SERVICE);
		if(wifiMgr.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
		return true;
		} else {
		return false;
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initResource();
	

		//create button
		ToggleButton wifi=(ToggleButton)findViewById(R.id.btnwifi);
		wifi.setOnClickListener((OnClickListener)this);	
		
		Button btn1=(Button)findViewById(R.id.btndate);
		btn1.setOnClickListener((OnClickListener) this);	
		
		Button btn2=(Button)findViewById(R.id.btnclock);
		btn2.setOnClickListener((OnClickListener) this);
		
		Button btninternet=(Button)findViewById(R.id.btninternet);
		btninternet.setOnClickListener((OnClickListener) this);
		
		Button btnsos=(Button)findViewById(R.id.btnsos);
		btnsos.setOnClickListener((OnClickListener) this);
		
		Button btn6=(Button)findViewById(R.id.btnapp);
		btn6.setOnClickListener((OnClickListener) this);
		
		Button btn7=(Button)findViewById(R.id.btnmms);
		btn7.setOnClickListener((OnClickListener) this);
		
		Button btn8=(Button)findViewById(R.id.btncamera);
		btn8.setOnClickListener((OnClickListener) this);
		
		Button btn9=(Button)findViewById(R.id.btngallery);
		btn9.setOnClickListener((OnClickListener) this);
		
		Button btn10=(Button)findViewById(R.id.btnphonecall);
		btn10.setOnClickListener((OnClickListener) this);
		
		Button btn11=(Button)findViewById(R.id.btnrecall);
		btn11.setOnClickListener((OnClickListener) this);
		
		//wifi state changed
		//if wifion -> changed ON! wifibtn icon 
		if(isWifiEnabled(this)){
    		  wifi.setChecked(true);}
		else{
			Log.v("test", "2");
    	    wifi.setChecked(false);
		}
	}
	
	private void initResource() {
		// TODO Auto-generated method stub
		this.mClock = (TextView)this.findViewById(R.id.btnclock);
		this.mClockFormat = new SimpleDateFormat("HH : mm", Locale.getDefault());
		this.updateClockTime();
	}

	private void updateClockTime() {
		// TODO Auto-generated method stub
		this.mClock.setText(this.mClockFormat.format(new Date()));
	}
	
	
	//버튼이벤트처리
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btndate:
			Intent it_date = new Intent(Intent.ACTION_MAIN);
			it_date.setComponent(new ComponentName("com.android.calendar","com.android.calendar.LaunchActivity"));
			startActivity(it_date);
			
			break;
			
		case R.id.btnclock:
			Intent it_clock= new Intent(Intent.ACTION_MAIN);
			it_clock.setComponent(new ComponentName("com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage"));
			startActivity(it_clock);
			break;
			
		case R.id.btnwifi:
			final WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		      // button clicked wifi changed
		      wifi.setWifiEnabled(!wifi.isWifiEnabled());
			break;
			
		case R.id.btninternet:
			Intent it_internet = new Intent(Intent.ACTION_MAIN);
			it_internet.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
			startActivity(it_internet);
			break;
			
		case R.id.btnsos:
			List<PackageInfo> packageinfo = getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
		      
	        for(int i =0;i< packageinfo .size();i++){
	         PackageInfo pi =  packageinfo .get(i);
	         String appname = pi.packageName;        // 패키지명
	                if(pi.activities != null){
	                  String appclassname =pi.activities[0].name;        // 클래스명
	                Log.d(appname,appclassname);
	               } 
	        }
			break;
			
		case R.id.btnapp:
			break;
			
		case R.id.btnmms:
			Intent it_mms = new Intent(Intent.ACTION_MAIN);
			it_mms.setComponent(new ComponentName("com.android.mms","com.android.mms.ui.ConversationComposer"));
			startActivity(it_mms);
			
			//Intent it_mms = new Intent(Intent.ACTION_);
			//startActivity(it_mms);  
			
			break;
			
		case R.id.btncamera:
			Intent it_camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
			it_camera.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(it_camera);			
			break;
			
		case R.id.btngallery:
			Intent it_gallery = new Intent (Intent.ACTION_PICK);
			it_gallery.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
			it_gallery.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivity(it_gallery);
			break;
			
		case R.id.btnphonecall:
			Intent itphonecall = new Intent(Intent.ACTION_DIAL);  
			startActivity(itphonecall);  
			break;
			
		case R.id.btnrecall:
			break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
	//	Toast.makeText(this,
	//			isChecked ? wifibtn.getTextOn() : wifibtn.getTextOff(),
	//			Toast.LENGTH_SHORT).show();
	}


}


