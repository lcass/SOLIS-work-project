package com.Core.graphics;

public class vertex4d {//only stores coordinates of a vertex
	private float x,y,z,t = 0;
	public vertex4d(int x, int y, int z, int t){
		this.x = x;
		this.y = y;
		this.z = z;
		this.t = t;
	}
	public float getx(){
		return x;
	}
	public float gety(){
		return y;
	}
	public float getz(){
		return z;
	}
	public float gett(){
		return t;
	}
	public void setcoordinates(float f, float g, float h, float i){
		this.x = f;
		this.y = g;
		this.z = h;
		this.t = i;
	}
	public void addtocoordinates(float x, float y){
		this.x = this.x + x;
		this.y = this.y + y;
		z = z + x;
		t = t + y;
	}
	

}
