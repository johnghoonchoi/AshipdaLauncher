package com.example.ashipdalauncher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.ashipdalauncher.util.LogUtil;

import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.*;
import android.provider.CallLog.Calls;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;


public class MainActivity extends Activity 
implements OnClickListener, OnCheckedChangeListener  {

	private SimpleDateFormat mClockFormat, mDateFormat;
	private TextView mClock, mDate, mReDial;
	private Uri uri;

	private void initResource() { //now time, date, redial information data set int here
		this.mClock = (TextView)this.findViewById(R.id.btnClock);
		this.mClockFormat = new SimpleDateFormat("HH"+":"+"mm", Locale.getDefault());
		this.mDate = (TextView)this.findViewById(R.id.btnDate);
		this.mDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		this.mReDial = (TextView)this.findViewById(R.id.btnRedial);
		updateClockTime();
	}
	// date, time , redial  data set, and show in window	

	private void updateClockTime(){
		new Thread(){
			public void run(){
				while (true){
					try{
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mClock.setText(mClockFormat.format(new Date()));
								mDate.setText(mDateFormat.format(new Date()));
								//LogUtil.v(mClockFormat.format(new Date()));
								//redial start
								//TODO:if you have time..... query modify to only set last number of my send list.not all phone call list
								try {
									Cursor c = getContentResolver().query(Calls.CONTENT_URI, null, null, null, Calls.DATE + " DESC");

									if (c != null) {
										if (c.moveToFirst()) {
											c.getString(c.getColumnIndex(Calls.CACHED_NAME));
											uri = Uri.parse("tel:" + c.getString(c.getColumnIndex(Calls.NUMBER)));
											// LogUtil.v("uri: " + uri.toString());
										} 
										// Uri is member var
									}
									LogUtil.v(c.getString(c.getColumnIndex(Calls.NUMBER)));
									mReDial.setText(c.getString(c.getColumnIndex(Calls.NUMBER))); 
									//Last num send to btn11(redial btn)

									c.close();
									//end redial

								}
								catch (Exception e) {
									LogUtil.w(e.toString());
								}
							}
						});
						Thread.sleep(400);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}

		//send wifi state 
		public static boolean isWifiEnabled(Context context) {

			WifiManager wifiMgr = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
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
			initResource();
			//create button
			ToggleButton wifi=(ToggleButton)findViewById(R.id.btnWifi);
			wifi.setOnClickListener((OnClickListener)this);	
			Button btn1=(Button)findViewById(R.id.btnDate);
			btn1.setOnClickListener((OnClickListener) this);	
			Button btn2=(Button)findViewById(R.id.btnClock);
			btn2.setOnClickListener((OnClickListener) this);
			Button btninternet=(Button)findViewById(R.id.btnInternet);
			btninternet.setOnClickListener((OnClickListener) this);
			Button btnsos=(Button)findViewById(R.id.btnSos);
			btnsos.setOnClickListener((OnClickListener) this);
			Button btn6=(Button)findViewById(R.id.btnApp);
			btn6.setOnClickListener((OnClickListener) this);
			Button btn7=(Button)findViewById(R.id.btnMms);
			btn7.setOnClickListener((OnClickListener) this);
			Button btn8=(Button)findViewById(R.id.btnCamera);
			btn8.setOnClickListener((OnClickListener) this);
			Button btn9=(Button)findViewById(R.id.btnGallery);
			btn9.setOnClickListener((OnClickListener) this);
			Button btn10=(Button)findViewById(R.id.btnPhoneCall);
			btn10.setOnClickListener((OnClickListener) this);
			Button btn11=(Button)findViewById(R.id.btnRedial);
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

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnDate:
				Intent it_date = new Intent(Intent.ACTION_MAIN);
				it_date.setComponent(new ComponentName("com.android.calendar","com.android.calendar.LaunchActivity"));
				startActivity(it_date);
				break;

			case R.id.btnClock:
				Intent it_clock= new Intent(Intent.ACTION_MAIN);
				it_clock.setComponent(new ComponentName("com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage"));
				startActivity(it_clock);
				break;

			case R.id.btnWifi:
				final WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				// button clicked wifi changed
				wifi.setWifiEnabled(!wifi.isWifiEnabled());
				break;

			case R.id.btnInternet:
				Intent it_internet = new Intent(Intent.ACTION_MAIN);
				it_internet.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
				startActivity(it_internet);
				break;

			case R.id.btnSos:
				List<PackageInfo> packageinfo = getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
				for(int i =0;i< packageinfo .size();i++){
					PackageInfo pi =  packageinfo .get(i);
					String appname = pi.packageName;        // package name
					if(pi.activities != null){
						String appclassname =pi.activities[0].name; //class name
						Log.d(appname,appclassname);
					} 
				}
				break;

			case R.id.btnApp:
				break;

			case R.id.btnMms:
				Intent it_mms = new Intent(Intent.ACTION_MAIN);
				it_mms.setComponent(new ComponentName("com.android.mms","com.android.mms.ui.ConversationComposer"));
				startActivity(it_mms);
				break;

			case R.id.btnCamera:
				Intent it_camera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
				it_camera.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(it_camera);			
				break;

			case R.id.btnGallery:
				Intent it_gallery = new Intent (Intent.ACTION_PICK);
				it_gallery.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				it_gallery.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivity(it_gallery);
				break;

			case R.id.btnPhoneCall:
				Intent itPhoneCall = new Intent(Intent.ACTION_DIAL);  
				startActivity(itPhoneCall);  
				break;

			case R.id.btnRedial: 
				Intent itPhoneReDial = new Intent(Intent.ACTION_DIAL);  
				itPhoneReDial.setData(uri);
				startActivity(itPhoneReDial);  
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
			//	Toast.makeText(this,
			//			isChecked ? wifibtn.getTextOn() : wifibtn.getTextOff(),
			//			Toast.LENGTH_SHORT).show();
		}


	}