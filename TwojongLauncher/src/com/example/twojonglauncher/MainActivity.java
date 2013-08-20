package com.example.twojonglauncher;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
        MyAdapter adapter = new MyAdapter(this, R.layout.listitem, pkgAppsList);
        GridView lv = (GridView) findViewById(R.id.launcherList);
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
}