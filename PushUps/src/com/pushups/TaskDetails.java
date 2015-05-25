package com.pushups;

import com.example.pushups.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class TaskDetails extends Activity {

	private int tasknumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taskdetails);
		tasknumber=getIntent().getIntExtra("tasknumber", 0);
		ImageView startOrder_btn=(ImageView) findViewById(R.id.b1);  
		ImageView back=(ImageView) findViewById(R.id.b2);
		back.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(getApplicationContext(), TasksActivity.class);
				startActivity(intent);
				
			}
		});
		          
		startOrder_btn.setOnClickListener(new OnClickListener(){  
		    public void onClick(View v) {  
		        //加上你要实现的代码       
		    	//Toast.makeText(getApplicationContext(), "点了我", Toast.LENGTH_LONG).show();
		    	
		    	Intent intent2=new Intent();
		    	intent2.setClass(getApplicationContext(), BiaozhunCounter.class);
		    	startActivity(intent2);
		        }  
		    });  
	}
	

}
