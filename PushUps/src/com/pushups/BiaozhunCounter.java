package com.pushups;

import java.io.IOException;

import com.example.pushups.R;
import com.pushups.Push_Ups_Counter.MySensorListener;

import db.DBHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class BiaozhunCounter extends Activity {

	private int TIME = 1000;
	private int number_of_layout=3;
	
	private TextView number_of_lack,words;
	ImageView back_of_counter,fuwocheng;
	private SensorManager mSensorManager;
	AlertDialog ag;
	AlertDialog ad;
	private Sensor proximity;
	private int time=0;
	MySensorListener listener;
	int count=-1;
	boolean isdown=false;
	private int starttime,completetime;
	private MediaPlayer mMediaPlayer,mMediaplayer2;
	SoundPlayer player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countdown3);
		player=new SoundPlayer();
		player.init(getApplicationContext());
		
		handler.postDelayed(runnable, TIME);
		
		mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		proximity=mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		 listener=new MySensorListener() ;
		mSensorManager.registerListener(listener
			, proximity,SensorManager.SENSOR_DELAY_NORMAL);
		
		mMediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        //播放工程res目录下的raw目录中的音乐文件in_call_alarm
		mMediaplayer2=MediaPlayer.create(this, R.raw.no1);
 
        try {
            mMediaPlayer.prepare();
            mMediaplayer2.prepare();
        } catch (IllegalStateException e) {
            
        } catch (IOException e) {
           
        }
 
        mMediaPlayer.start();
        //headsetplay.setEnabled(false);
 
        mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    //播完了接着播或者关闭mMediaPlayer
            }
        });
    
		
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
					fuwocheng=(ImageView) findViewById(R.id.fuwocheng);
					back_of_counter.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							
							showpause();
							
//							Intent intent=new Intent();
//							intent.setClass(getApplicationContext(), TasksActivity.class);
//							startActivity(intent);
							
							
							
							
							
						}
					});
				
					number_of_lack.setText("Left To do: 5");
					words.setText("Standard Push-ups*5");
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
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
//        //builder.setIcon(R.drawable.icon);  
//        
//        builder.setTitle("恭喜！");  
//        builder.setMessage(content);  
//        builder.setPositiveButton("确定",  
//                new DialogInterface.OnClickListener() {  
//                    public void onClick(DialogInterface dialog, int whichButton) {  
//                         ad.cancel();
//                         Data.liliang+=10;
//                         Data.naili+=10;
//                         Data.xiongwei+=1;
//                         Intent intent=new Intent();
//                         intent.setClass(getApplicationContext(), TasksActivity.class);
//                         startActivity(intent);
//                         
//                    }  
//                });  
//
//        ad=builder.show();  
		
		
		
		LayoutInflater inflater = LayoutInflater.from(BiaozhunCounter.this);  
		View layout=inflater.inflate(R.layout.done,null);
		          
		AlertDialog.Builder builder =new AlertDialog.Builder(BiaozhunCounter.this);  
		builder.setView(layout);  
		builder.setCancelable(false);  
		ag=builder.create();
		ag.show();

		
		
		TextView done;
		done=(TextView) layout.findViewById(R.id.done);
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ag.cancel();
				Data.liliang+=5;
               Data.naili+=2;
               Data.xiongwei+=0.2;
               Data.tunwei+=0.1;
               DBHelper helper=new DBHelper(getApplicationContext());
//               String basestr="select liliang,naili,tunwei,xiongwei,type,name  from pushup order by _id desc";
//               Cursor cursor = helper.query(basestr);
//       		
//       			cursor.moveToNext();
       			
       			String s="insert into pushup(liliang,naili,tunwei,xiongwei,type,name)  VALUES ("
       					+ Data.liliang+","
       					+Data.naili+","
       					+Data.tunwei+","
       					+Data.xiongwei+","
       					+Data.tasktype+","
       					+"\'"
       					+Data.name
       					+"\')";
       			
               helper.getWritableDatabase().execSQL(s);
               mMediaPlayer.pause();
               Intent intent=new Intent();
               intent.setClass(getApplicationContext(), MainstatusAcitivity.class);
               startActivity(intent);
			}
		});
		
		
		
    }  
	
	class MySensorListener implements SensorEventListener
	{
		@Override
		public void onSensorChanged(SensorEvent e) {
			// TODO Auto-generated method stub
			
			//proximity.
			if(count==20)
			{
				mSensorManager.unregisterListener(listener);
				if(Data.pretype==1)
					Data.tasktype=1;
				
				else if(Data.pretype==2)
					Data.tasktype=2;
				else if(Data.pretype==3)
					Data.tasktype=3;
				else if(Data.pretype==4)
					Data.tasktype=4;
				System.out.println(Data.tasktype);
				showDialog(BiaozhunCounter.this, "恭喜您，完成力量训练的锻炼目标！");
				
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
					
					if(count<5)
					{
						//words.setText("Standard Push-ups*5");
						
					}
					else if(count>=5&&count<10)
					{	
						fuwocheng.setImageResource(R.drawable.kuanju);
						//words.setText("Wide Push-ups*5");
					
					
					}
					else if(count>=10&&count<15)
					{	
					fuwocheng.setImageResource(R.drawable.waixiangkuanju);
					//words.setText("Wide Outward Push-ups*5");
					
					}
					else if(count>=15&&count<20)
					{	
						fuwocheng.setImageResource(R.drawable.zhaiju);
						//words.setText("Close Push-ups*5");
					}
				}
				else if(k>=3)
				{
					words.setText("faster!");
					
					
					if(count<5)
					{
						//words.setText("Standard Push-ups*5");
						
					}
					else if(count>=5&&count<10)
					{	
						fuwocheng.setImageResource(R.drawable.kuanju);
						//words.setText("Wide Push-ups*5");
					
					
					}
					else if(count>=10&&count<15)
					{	
					fuwocheng.setImageResource(R.drawable.waixiangkuanju);
					//words.setText("Wide Outward Push-ups*5");
					
					}
					else if(count>=15&&count<20)
					{	
						fuwocheng.setImageResource(R.drawable.zhaiju);
						//words.setText("Close Push-ups*5");
					}
				}
				else
				{
					if(count<5)
					{
						words.setText("Standard Push-ups*5");
						
					}
					else if(count>=5&&count<10)
					{	
						fuwocheng.setImageResource(R.drawable.kuanju);
						words.setText("Wide Push-ups*5");
					
					
					}
					else if(count>=10&&count<15)
					{	
					fuwocheng.setImageResource(R.drawable.waixiangkuanju);
					words.setText("Wide Outward Push-ups*5");
					
					}
					else if(count>=15&&count<20)
					{	
						fuwocheng.setImageResource(R.drawable.zhaiju);
						words.setText("Close Push-ups*5");
					}
					
				}
				count++;
				int f=5;
				if(count<=5)
					 f=6-count;
				else if(count>5&&count<=10)
					f=11-count;
				else if(count>10&&count<=15)
					f=16-count;
				else if(count>15&&count<=20)
					f=21-count;
				
				
				
				
				switch (f) {
				case 5:
					//player.playSound(R.raw.no1);
					mMediaplayer2.start();
			        //headsetplay.setEnabled(false);
			 
			        mMediaplayer2.setOnCompletionListener(new OnCompletionListener() {
			                public void onCompletion(MediaPlayer mp) {
			                    //播完了接着播或者关闭mMediaPlayer
			                	mMediaplayer2.pause();
			            }
			        });
					break;
				case 4:
					player.playSound(R.raw.no2);
					break;
				case 3:
					player.playSound(R.raw.no3);
					break;
				case 2:
					player.playSound(R.raw.onemore);
					break;
				case 1:
					player.playSound(R.raw.goodjob);
					break;
				

				default:
					break;
				}
				if(f==6)
					f=5;
				number_of_lack.setText("Left To do:"+f);
				isdown=false;
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
			
		}
	}
	
	public void showpause()
	{
		LayoutInflater inflater = LayoutInflater.from(BiaozhunCounter.this);  
		View layout=inflater.inflate(R.layout.onpause,null);
		          
		AlertDialog.Builder builder =new AlertDialog.Builder(BiaozhunCounter.this);  
		builder.setView(layout);  
		builder.setCancelable(false);  
		ag=builder.create();
		ag.show();
//		ImageView startOrder_btn=(ImageView) layout.findViewById(R.id.b1);  
//		ImageView back=(ImageView) layout.findViewById(R.id.b2);
//		back.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				ag.cancel();
//		    	
//			}
//		});
//		          
//		startOrder_btn.setOnClickListener(new OnClickListener(){  
//		    public void onClick(View v) {  
//		        //加上你要实现的代码       
//		    	//Toast.makeText(getApplicationContext(), "点了我", Toast.LENGTH_LONG).show();
//		    	ag.cancel();
//		    	Intent intent2=new Intent();
//		    	intent2.setClass(getApplicationContext(), url);
//		    	startActivity(intent2);
//		        }  
//		    });  
		
		
		TextView cancle,continue_,giveup;
		//cancle=(TextView) layout.findViewById(R.id.cancle);
		continue_=(TextView) layout.findViewById(R.id.continue2);
		giveup=(TextView) layout.findViewById(R.id.giveup);
//		cancle.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				ag.cancel();
//				
//			}
//		});
		
		giveup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				mMediaPlayer.pause();
				intent.setClass(getApplicationContext(), TasksActivity.class);
				startActivity(intent);
			}
		});
		continue_.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ag.cancel();
			}
		});
	}
		
		
	
}
