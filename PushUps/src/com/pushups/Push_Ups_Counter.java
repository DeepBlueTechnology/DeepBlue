package com.pushups;

import com.example.pushups.R;

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
import android.view.Window;
import android.widget.TextView;

public class Push_Ups_Counter extends Activity {
	
	private SensorManager mSensorManager;
	AlertDialog ad;
	private Sensor proximity;
	TextView textView;
	MySensorListener listener;
	int count=0;
	boolean isdown=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.push_up_counter);
		textView=(TextView) findViewById(R.id.counter2);
		mSensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		proximity=mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		 listener=new MySensorListener() ;
		mSensorManager.registerListener(listener
			, proximity,SensorManager.SENSOR_DELAY_NORMAL);
		
	}
	private void showDialog(Context context,String content) {  
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        //builder.setIcon(R.drawable.icon);  
        
        builder.setTitle("恭喜！");  
        builder.setMessage(content);  
        builder.setPositiveButton("确定",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                         ad.cancel();
                         
                         Intent intent=new Intent();
                         intent.setClass(getApplicationContext(), TasksActivity.class);
                         startActivity(intent);
                         
                    }  
                });  
//        builder.setNeutralButton("Button2",  
//                new DialogInterface.OnClickListener() {  
//                    public void onClick(DialogInterface dialog, int whichButton) {  
//                        setTitle("点击了对话框上的Button2");  
//                    }  
//                });  
//        builder.setNegativeButton("Button3",  
//                new DialogInterface.OnClickListener() {  
//                    public void onClick(DialogInterface dialog, int whichButton) {  
//                        setTitle("点击了对话框上的Button3");  
//                    }  
//                });  
        ad=builder.show();  
    }  

	class MySensorListener implements SensorEventListener
	{
		@Override
		public void onSensorChanged(SensorEvent e) {
			// TODO Auto-generated method stub
			
			//proximity.
			if(count==3)
			{
				mSensorManager.unregisterListener(listener);
				showDialog(Push_Ups_Counter.this, "恭喜您，完成俯卧撑3个的锻炼目标！");
			}
			if(e.values[0]<proximity.getMaximumRange())
			{
				isdown=true;
			}
			if(isdown&&e.values[0]==proximity.getMaximumRange())
			{
				count++;
				textView.setText(""+count);
				isdown=false;
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub
			
			
		}
	}

}
