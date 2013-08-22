// Wifi Source
package com.example.wi_fi;

import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity implements OnCheckedChangeListener{
	
	
	ToggleButton toggleButton1 = null;
	// make togglebutton o,o
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		 toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
	      toggleButton1.setOnCheckedChangeListener(this); // if you toggle this button, change button 
		  toggleButton1.setChecked(false);
		  toggleButton1.setOnClickListener(new View.OnClickListener()
		  {
		    @Override
		    public void onClick(View v)
		    {
			    
			      
		      final WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		      // button clicked wifi changed
		      wifi.setWifiEnabled(!wifi.isWifiEnabled());
		  
		    }
		  });
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		//toggle button onCheckedChanged togglebutton asfas 1afjasfasf
		
		// TODO Auto-generated method stub
		Toast.makeText(this,
				isChecked ? toggleButton1.getTextOn() : toggleButton1.getTextOff(),
				Toast.LENGTH_SHORT).show();
	}

}
