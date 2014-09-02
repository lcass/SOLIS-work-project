package com.Core.graphics;

public class vertex2d {
	private float x,y,r,g,b,s,t=0;
	public vertex2d(float x , float y){
		this.x = x;
		this.y = y;
	}
	public void setcolor(int r,int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	public void setcoordinates(float x, float y){
		this.x = x;
		this.y = y;
	}
	public void settexcoords(float s, float t){
		this.s = s;
		this.t = t;
	}
	public void addtovertcoords(float x, float y){
		this.x = this.x + x;
		this.y = this.y + y;
	}
	public float getx(){
		return x;
	}
	public float gety(){
		return y;
	}
	public float getr(){
		return r;
	}
	public float getg(){
		return g;
	}
	public float getb(){
		return b;
	}
	public float gets()
	{
		return s;
	}
	public float gett(){
		return t;
	}
}
