package com.pushups;


import com.example.pushups.R;

import android.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainstatusAcitivity extends Activity {
	TextView textView,liliang,naili,xiongwei,tunwei;
	ImageView back,start_train,userPhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainstatus);
		Intent intent=getIntent();
		String username=intent.getStringExtra("username");
		boolean isboy=intent.getBooleanExtra("isboy", true);
		int height=intent.getIntExtra("height", -1);
		int weight=intent.getIntExtra("weight", -1);
		userPhoto=(ImageView) findViewById(R.id.user_photo);
		liliang=(TextView) findViewById(R.id.liliang);
		naili=(TextView) findViewById(R.id.naili);
		xiongwei=(TextView) findViewById(R.id.xiongwei);
		tunwei=(TextView) findViewById(R.id.tunwei);
		if(Data.tasktype==1||Data.tasktype==2)
			userPhoto.setImageResource(R.drawable.man2);
		if(Data.tasktype==3||Data.tasktype==4)
			userPhoto.setImageResource(R.drawable.man3);
		liliang.setText(""+Data.liliang);
		naili.setText(""+Data.naili);
		xiongwei.setText(""+Data.xiongwei);
		tunwei.setText(""+Data.tunwei);
		
		//Toast.makeText(getApplicationContext(), username+height+weight+isboy, Toast.LENGTH_LONG).show();
		textView=(TextView) findViewById(R.id.mainstatus_user_name);
		back=(ImageView) findViewById(R.id.mainstatus_back);
		start_train=(ImageView) findViewById(R.id.mainstatus_start_train);
		textView.setText(Data.name);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainstatusAcitivity.this, CreateUserAvtivity.class);
				startActivity(intent);
				
			}
		});
		start_train.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainstatusAcitivity.this, TasksActivity.class);
				startActivity(intent);
			}
		});
		
	}
	

}
