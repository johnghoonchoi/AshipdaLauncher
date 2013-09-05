package com.example.ashipdalauncher;

import android.net.Uri;
import android.os.*;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//버튼 등록
		Button btn1=(Button)findViewById(R.id.id_1);
		btn1.setOnClickListener((OnClickListener) this);
		
		Button btn2=(Button)findViewById(R.id.id_2);
		btn2.setOnClickListener((OnClickListener) this);
		
		Button btn3=(Button)findViewById(R.id.id_3);
		btn3.setOnClickListener((OnClickListener) this);
		
		Button btn4=(Button)findViewById(R.id.id_4);
		btn4.setOnClickListener((OnClickListener) this);
		
		Button btn5=(Button)findViewById(R.id.id_5);
		btn5.setOnClickListener((OnClickListener) this);
		
		Button btn6=(Button)findViewById(R.id.id_6);
		btn6.setOnClickListener((OnClickListener) this);
		
		Button btn7=(Button)findViewById(R.id.id_7);
		btn7.setOnClickListener((OnClickListener) this);
		
		Button btn8=(Button)findViewById(R.id.id_8);
		btn8.setOnClickListener((OnClickListener) this);
		
		Button btn9=(Button)findViewById(R.id.id_9);
		btn9.setOnClickListener((OnClickListener) this);
		
		Button btn10=(Button)findViewById(R.id.id_10);
		btn10.setOnClickListener((OnClickListener) this);
		
		Button btn11=(Button)findViewById(R.id.id_11);
		btn11.setOnClickListener((OnClickListener) this);
		
	}
	
	
	//버튼이벤트처리
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_1:
			break;
			
		case R.id.id_2:
			break;
			
		case R.id.id_3:
			break;
			
		case R.id.id_4:
			Uri uri = Uri.parse("http://www.google.com");
			Intent it  = new Intent(Intent.ACTION_VIEW,uri);
			startActivity(it);
			break;
			
		case R.id.id_5:
			break;
			
		case R.id.id_6:
			break;
			
		case R.id.id_7:
			break;
			
		case R.id.id_8:
			Intent mms = new Intent(Intent.ACTION_VIEW);   
			mms.putExtra("sms_body", "The SMS text");   
			mms.setType("vnd.android-dir/mms-sms");   
			startActivity(mms);
			break;
			
		case R.id.id_9:
			break;
			
		case R.id.id_10:
			Uri phonecall = Uri.parse("tel:");
			Intent itphonecall = new Intent(Intent.ACTION_DIAL, phonecall);  
			startActivity(itphonecall);  
			break;
			
		case R.id.id_11:
			break;

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


