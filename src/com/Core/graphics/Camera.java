package com.Core.graphics;

public class Camera {
	private vertex2d position;
	private vertex4d viewpoint;
	public Camera(vertex2d position,int width, int height){
		this.position = position;
		viewpoint = new vertex4d(0,0,0,0);
		viewpoint.setcoordinates((float)position.getx(),(float)position.gety(),(float)(position.getx() + width),(float)(position.gety()+height));
	}
	public vertex4d returnviewpoint(){
		return viewpoint;
	}
	public void translate(float x, float y){
		viewpoint.addtocoordinates(-x, -y);//invert to compensate
		position.addtovertcoords(-x, -y);
	}
	public vertex2d getposition(){
		return position;
	}
}
