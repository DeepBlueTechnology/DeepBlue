package com.pushups;


import com.example.pushups.R;

import db.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView newgame,resume,help;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		newgame=(ImageView) findViewById(R.id.new_game);
		resume=(ImageView) findViewById(R.id.resume);
		help=(ImageView) findViewById(R.id.intro);
		newgame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(getApplicationContext(), CreateUserAvtivity.class);
				startActivity(intent);
			}
		});
		resume.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 DBHelper helper=new DBHelper(getApplicationContext());
               String basestr="select liliang,naili,tunwei,xiongwei,type,name  from pushup order by _id desc";
               Cursor cursor = helper.query(basestr);
       		
       			cursor.moveToNext();
       			int liliang,naili,tunwei,xiongwei,type;
       			String name;
       			Data.liliang=cursor.getInt(0);
       			Data.naili=cursor.getInt(1);
       			Data.tunwei=cursor.getInt(2);
       			Data.xiongwei=cursor.getInt(3);
       			Data.tasktype=cursor.getInt(4);
       			Data.name=cursor.getString(5);
       			cursor.close();
       			Intent intent=new Intent();
				intent.setClass(getApplicationContext(), MainstatusAcitivity.class);
				startActivity(intent);
			}
		});
		
		
	}
	

}
