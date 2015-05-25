package com.pushups;

import com.example.pushups.R;

import db.DBHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateUserAvtivity extends Activity {
	CheckBox boy;
	CheckBox girl;
	EditText username;
	EditText height,weight;
	ImageView startgame,backgame;
	boolean isboy=true;
	String s_username,s_height,s_weight;
	int i_height,i_weight;
	AlertDialog ad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_user);
		boy=(CheckBox) findViewById(R.id.boy);
		girl=(CheckBox) findViewById(R.id.girl);
		username=(EditText) findViewById(R.id.user_name);
		height=(EditText) findViewById(R.id.height);
		weight=(EditText) findViewById(R.id.weight);
		startgame=(ImageView) findViewById(R.id.start_game);
		backgame=(ImageView) findViewById(R.id.back_game);
		backgame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		boy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
				// TODO Auto-generated method stub
				if(ischecked)
				{
					isboy=true;
					girl.setChecked(false);
					//Toast.makeText(getApplicationContext(),""+ isboy, Toast.LENGTH_LONG).show();
				}
				else 
				{
					isboy=false;
					girl.setChecked(true);
					//Toast.makeText(getApplicationContext(),""+ isboy, Toast.LENGTH_LONG).show();
				}
			}
		});
		girl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
				// TODO Auto-generated method stub
				if (ischecked) {
					isboy=false;
					boy.setChecked(false);
					//Toast.makeText(getApplicationContext(),""+ isboy, Toast.LENGTH_LONG).show();
				} else {
					isboy=true;
					boy.setChecked(true);
					//Toast.makeText(getApplicationContext(),""+ isboy, Toast.LENGTH_LONG).show();
				}
			}
		});
		startgame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				s_username=username.getText().toString();
				if(s_username.compareTo("角色名称")==0)
				{
					showDialog(CreateUserAvtivity.this, "请输入正确的名称");
					return;
				}
				s_height=height.getText().toString();
				try 
				{
					i_height=Integer.parseInt(s_height);
				} catch (NumberFormatException e) 
				{
					showDialog(CreateUserAvtivity.this, "请输入正确的身高数字");
					return;
				}
				s_weight=weight.getText().toString();
				try 
				{
					i_weight=Integer.parseInt(s_weight);
				} catch (NumberFormatException e) 
				{
					showDialog(CreateUserAvtivity.this, "请输入正确的体重数字");
					return;
				}
				Intent intent=new Intent();
				intent.putExtra("isboy", isboy);
				intent.putExtra("height", i_height);
				intent.putExtra("weight", i_weight);
				intent.putExtra("username", s_username);
				Data.name=s_username+"    ";
				Data.tasktype=0;
				Data.liliang=50;
				Data.naili=40;
				Data.xiongwei=90;
				Data.tunwei=40;
						
				
				DBHelper helper=new DBHelper(getApplicationContext());
				String s="insert into pushup(liliang,naili,tunwei,xiongwei,type,name)  VALUES (50,40,40,90,0,"
       					
       					+"\'"
       					+Data.name
       					+"\')";
       			
              try{ helper.getWritableDatabase().execSQL(s);}
              catch(SQLException e)
              {
            	  e.printStackTrace();
              }
				
				
				intent.setClass(CreateUserAvtivity.this, MainstatusAcitivity.class);
				startActivity(intent);
				
				
			}
		});
	}
	private void showDialog(Context context,String content) {  
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        //builder.setIcon(R.drawable.icon);  
        
        builder.setTitle("警告");  
        builder.setMessage(content);  
        builder.setPositiveButton("确定",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                         ad.cancel();
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


}
