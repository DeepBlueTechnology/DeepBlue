package com.pushups;
import com.example.pushups.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TasksActivity extends Activity {
	ImageView imageView;
	ImageView task1,t2,t3,t4,t5,t6,t7,t8,t9;
	AlertDialog ag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasks);
		task1=(ImageView) findViewById(R.id.tasks1);
		t2=(ImageView) findViewById(R.id.tasks2);
		t3=(ImageView) findViewById(R.id.tasks3);
		t4=(ImageView) findViewById(R.id.tasks4);
		//t5=(TextView) findViewById(R.id.tasks5);
//		t6=(TextView) findViewById(R.id.tasks6);
//		t7=(TextView) findViewById(R.id.tasks7);
//		t8=(TextView) findViewById(R.id.tasks8);
//		t9=(TextView) findViewById(R.id.tasks9);
		imageView=(ImageView) findViewById(R.id.imageback);
		task1.setOnClickListener(new MyOnclickListener(1));
		t2.setOnClickListener(new MyOnclickListener(2));
		t3.setOnClickListener(new MyOnclickListener(3));
		t4.setOnClickListener(new MyOnclickListener(4));
		//t5.setOnClickListener(new MyOnclickListener());
//		t6.setOnClickListener(new MyOnclickListener(6));
//		t7.setOnClickListener(new MyOnclickListener(7));
//		t8.setOnClickListener(new MyOnclickListener(8));
//		t9.setOnClickListener(new MyOnclickListener(9));
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "后退", Toast.LENGTH_LONG).show();
				Intent intent=new Intent();
				intent.setClass(TasksActivity.this, MainstatusAcitivity.class);
				startActivity(intent);
			}
		});
	}
	
	class MyOnclickListener implements OnClickListener {
		
		private Class<?> url=BiaozhunCounter.class;
		Intent intent=new Intent();
		private int flag=0;
		private int x=R.layout.taskdetails;
		public MyOnclickListener(int arg) {
			// TODO Auto-generated constructor stub
			flag=arg;
			switch (arg) {
			case 1:
				url=BiaozhunCounter.class;
				intent.putExtra("tasknumber", 1);
				//Data.pretype=1;
				System.out.println("pretype:"+1);
				break;
			case 2:
				url=BiaozhunCounter.class;
				intent.putExtra("tasknumber", 2);
				//Data.pretype=2;
				System.out.println("pretype:"+2);
				break;
			case 3:
				url=BiaozhunCounter.class;
				intent.putExtra("tasknumber", 3);
				System.out.println("pretype:"+3);
				//Data.tasktype=3;
				//Data.pretype=3;
				break;
			case 4:
				url=BiaozhunCounter.class;
				intent.putExtra("tasknumber", 4);
				//Data.tasktype=4;
				System.out.println("pretype:"+4);
				//Data.pretype=4;
				break;
			case 6:
				url=NailiCounter.class;
				x=R.layout.nailitaskdetails;
				break;
			case 7:
				url=NailiCounter.class;
				x=R.layout.nailitaskdetails;
				break;
			case 8:
				url=NailiCounter.class;
				x=R.layout.nailitaskdetails;
				break;
			case 9:
				url=NailiCounter.class;
				x=R.layout.nailitaskdetails;
				break;

			default:
				break;
			}
			
		}
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
//			LayoutInflater inflater = LayoutInflater.from(TasksActivity.this);  
//			View layout=inflater.inflate(x,null);
//			          
//			AlertDialog.Builder builder =new AlertDialog.Builder(TasksActivity.this);  
//			builder.setView(layout);  
//			builder.setCancelable(false);  
//			ag=builder.create();
//			ag.show();
//			ImageView startOrder_btn=(ImageView) layout.findViewById(R.id.b1);  
//			ImageView back=(ImageView) layout.findViewById(R.id.b2);
//			back.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View arg0) {
//					// TODO Auto-generated method stub
//					ag.cancel();
//			    	
//				}
//			});
//			          
//			startOrder_btn.setOnClickListener(new OnClickListener(){  
//			    public void onClick(View v) {  
//			        //加上你要实现的代码       
//			    	//Toast.makeText(getApplicationContext(), "点了我", Toast.LENGTH_LONG).show();
//			    	ag.cancel();
//			    	Intent intent2=new Intent();
//			    	intent2.setClass(getApplicationContext(), url);
//			    	startActivity(intent2);
//			        }  
//			    });  
//			
//		}
			intent.setClass(getApplicationContext(), TaskDetails.class);
			Data.pretype=flag;
			startActivity(intent);
		}
	}
}
