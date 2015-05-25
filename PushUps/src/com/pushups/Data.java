package com.pushups;

import android.app.Application;



public class Data  extends Application{

	public static  int   tasktype=0,pretype=0;
	public static int liliang=50;
	public static int naili=50;
	public static int xiongwei=50;
	public static int tunwei=50;
	public static String name;
	public static int getTasktype() {
		return tasktype;
	}
	public void setTasktype(int tasktype) {
		this.tasktype = tasktype;
	}
	public int getLiliang() {
		return liliang;
	}
	public void setLiliang(int liliang) {
		this.liliang = liliang;
	}
	public int getNaili() {
		return naili;
	}
	public void setNaili(int naili) {
		this.naili = naili;
	}
	public int getXiongwei() {
		return xiongwei;
	}
	public void setXiongwei(int xiongwei) {
		this.xiongwei = xiongwei;
	}
	public int getTunwei() {
		return tunwei;
	}
	public void setTunwei(int tunwei) {
		this.tunwei = tunwei;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
