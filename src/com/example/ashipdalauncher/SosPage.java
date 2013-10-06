package com.example.ashipdalauncher;

import java.util.ArrayList;

import com.example.ashipdalauncher.util.LogUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class SosPage extends Activity {
	ArrayList<String> items;
	ArrayAdapter<String> adapter;
	ListView listView;
	Dialog mDialog;
	AlertDialog aDia;
	LayoutInflater inflater; 
	TextView text;
	Context Context;
	EditText name;
	String shit;

	final static int DIALOG_1 = 0;



	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sosmain);
		items = new ArrayList<String>();
		int i;
				

		// listView의 항목을 선택하려면 체크박스나 라디오 버튼이 있어야 하므로 아래의 레이아웃을 선택했다.
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		listView = (ListView)findViewById(R.id.sosList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(mItemClickListener);
		findViewById(R.id.btnSosAdd).setOnClickListener(mClickListener);
		findViewById(R.id.btnSosEdit).setOnClickListener(mClickListener);
		
		inflater    = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		String isthisnew;
		SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
		isthisnew = pref.getString("1", "");
		if(isthisnew != null)
		{
	        SharedPreferences.Editor editor = pref.edit();
	        editor.putString("1", "119");
	        editor.commit();
	        editor.putString("2", "112");
	        editor.commit();
	        editor.putString("3", "111");
	        editor.commit();
			
		}
		isthisnew = pref.getString("2", "");
		if(isthisnew != null)
		{
	        SharedPreferences.Editor editor = pref.edit();
	        editor.putString("1", "119");
	        editor.commit();
	        editor.putString("2", "112");
	        editor.commit();
	        editor.putString("3", "111");
	        editor.commit();
			
		}
		isthisnew = pref.getString("3", "");
		if(isthisnew != null)
		{
	        SharedPreferences.Editor editor = pref.edit();
	        editor.putString("1", "119");
	        editor.commit();
	        editor.putString("2", "112");
	        editor.commit();
	        editor.putString("3", "111");
	        editor.commit();
			
		}
		items.clear();
		
		for(i=1; i <10; i++)
		{
			items.add(pref.getString(String.valueOf(i),null));
		}

		 View layout = inflater.inflate(R.layout.sosadd, null);
		    AlertDialog.Builder aDialog = new AlertDialog.Builder(
		      SosPage.this);
		    aDialog.setTitle("추가");
		    aDialog.setView(layout);
		    aDialog.setNegativeButton("취소",
		      new DialogInterface.OnClickListener() {
		       public void onClick(DialogInterface dialog,
		         int which) {
		        dialog.dismiss();
		       }
		      });
		    aDialog.setPositiveButton("추가",
		      new DialogInterface.OnClickListener() {
		       public void onClick(DialogInterface dialog,
		         int which) {
		    	   SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
		    	   SharedPreferences.Editor editor = pref.edit();
		    	   name = (EditText)((AlertDialog)dialog).findViewById(R.id.editSosName);
		    	   shit = name.getText().toString();
		    	   String isthisfukinnew;
		    	   int j;
		    	   for(j=4;j<10;j++)
		    	   {
		    		   isthisfukinnew = pref.getString(String.valueOf(j),null);
		    		   if(isthisfukinnew == null)
		    			   break;
		    	   }
		    	   editor.putString(String.valueOf(j),shit);
                   editor.commit();
                   items.clear();
                   for(int i=1; i <10; i++)
           		{
           			items.add(pref.getString(String.valueOf(i),null));
           		}
		    	   
		       }});
		    aDia = aDialog.create();
		    
		
	}

	// 상단 버튼에서 사용하는 listener
	Button.OnClickListener mClickListener = new View.OnClickListener() {
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.btnSosAdd :
				
				aDia.show();
				break;
			case R.id.btnSosEdit:
				Intent intent = new Intent(SosPage.this, SosEdit.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};

	// ListView에서 사용하는 listener
	AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			String number = items.get(position);
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
			startActivity(intent);
			finish();
		}
	};
}
