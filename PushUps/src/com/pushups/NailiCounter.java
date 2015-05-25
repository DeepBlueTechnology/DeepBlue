package com.pushups;

import com.example.pushups.R;
import com.pushups.Push_Ups_Counter.MySensorListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class NailiCounter extends Activity {

	private int TIME = 1000;
	private int number_of_layout=3;
	private TextView number_of_lack,words;
	ImageView back_of_counter;
	private SensorManager mSensorManager;
	AlertDialog ad;
	private Sensor proximity;
	private int time=0;
	MySensorListener listener;
	int count=0;
	boolean isdown=false;
	private int starttime,completetime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countdown3);
		
		handler.postDelayed(runnable, TIME);
		
		mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		proximity=mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		 listener=new MySensorListener() ;
		mSensorManager.registerListener(listener
			, proximity,SensorManager.SENSOR_DELAY_NORMAL);
		
	}

	
	Handler handler = new Handler();
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// handler自带方法实现定时器
			try {
				handler.postDelayed(this, TIME);
				switch (number_of_layout) {
				case 3:
					setContentView(R.layout.countdown2);
					number_of_layout=2;
					break;
				case 2:
					setContentView(R.layout.countdown1);
					number_of_layout=1;
					break;
				case 1:
					setContentView(R.layout.countdown_readygo);
					number_of_layout=0;
					break;
				case 0:
					setContentView(R.layout.counter);
					number_of_layout=-1;
					back_of_counter=(ImageView) findViewById(R.id.back_of_counter);
					number_of_lack=(TextView) findViewById(R.id.number_of_lack);
					words=(TextView) findViewById(R.id.words);
					back_of_counter.setOnClickListener(new OnClickListener() {
						//返回
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent intent=new Intent();
							intent.setClass(getApplicationContext(), TasksActivity.class);
							startActivity(intent);
							
						}
					});
					number_of_lack.setText("剩余个数：5");
					words.setText("耐力俯卧撑");
					break;
				case -1:
					time++;
					break;
				default:
					break;
				}
			
				
				//System.out.println("do...");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.out.println("exception...");
			}
		}
	};
	private void showDialog(Context context,String content) {  
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        //builder.setIcon(R.drawable.icon);  
        
        builder.setTitle("恭喜！");  
        builder.setMessage(content);  
        builder.setPositiveButton("确定",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                         ad.cancel();
                         Data.liliang+=10;
                         Data.naili+=10;
                         Data.tunwei+=1;
                         Data.xiongwei+=10;
                         Intent intent=new Intent();
                         intent.setClass(getApplicationContext(), TasksActivity.class);
                         startActivity(intent);
                         
                    }  
                });  

        ad=builder.show();  
    }  
	
	class MySensorListener implements SensorEventListener
	{
		@Override
		public void onSensorChanged(SensorEvent e) {
			// TODO Auto-generated method stub
			
			//proximity.
			if(count==8)
			{
				mSensorManager.unregisterListener(listener);
				showDialog(NailiCounter.this, "恭喜您，完成耐力俯卧撑的锻炼目标！");
			}
			if(e.values[0]<proximity.getMaximumRange())
			{
				isdown=true;
				starttime=time;
			}
			if(isdown&&e.values[0]==proximity.getMaximumRange())
			{
				int k=time-starttime;
				if(k<=0)
				{
					words.setText("slower!");
				}
				else if(k>=3)
				{
					words.setText("faster!");
				}
				else
				{
					if(count<=1)
						words.setText("俯卧撑1个");
					else if(count>1&&count<=3)
						words.setText("俯卧撑2个");
					else if(count>3&&count<=6)
						words.setText("俯卧撑3个");
					else if(count>6&&count<=8)
						words.setText("俯卧撑2个");
					
				}
					
				count++;
				int f=5;
				if(count<=1)
					 f=2-count;
				else if(count>1&&count<=3)
					f=4-count;
				else if(count>3&&count<=6)
					f=7-count;
				else if(count>6&&count<=8)
					f=9-count;
				number_of_lack.setText("剩余个数："+f);
				isdown=false;
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
			
		}
	}
}
