package com.Core.graphics;

import com.Core.Gamecore;
import com.Core.graphics.texture.spritecomponent;

public class shapes {
	private Gamecore core;
	private float width,height;
	public shapes(Gamecore core){
		this.core = core;
		width = core.width;
		height = core.height;
	}
	public vertex2d[] square(float pixcount,spritecomponent s){
		
		
		vertex2d[] temp = new vertex2d[]{new vertex2d(0,0),new vertex2d((pixcount/width)*2,0),new vertex2d(0,(pixcount/height)*2),new vertex2d((pixcount/width)*2,0),new vertex2d((pixcount/width)*2,(pixcount/height)*2),new vertex2d(0,(pixcount/height)*2)};
		temp[0].settexcoords(s.x, s.ey);
		temp[1].settexcoords(s.ex,s.ey);
		temp[2].settexcoords(s.x, s.y);
		temp[3].settexcoords(s.ex, s.ey);
		temp[4].settexcoords(s.ex,s.y);
		temp[5].settexcoords(s.x, s.y);
		return temp;
	}
public vertex2d[] rect(spritecomponent s,int owidth,int oheight){
		
		
		vertex2d[] temp = new vertex2d[]{new vertex2d(0,0),new vertex2d((owidth/width)*2,0),new vertex2d(0,(oheight/height)*2),new vertex2d((owidth/width)*2,0),new vertex2d((owidth/width)*2,(oheight/height)*2),new vertex2d(0,(oheight/height)*2)};
		temp[0].settexcoords(s.x, s.ey);
		temp[1].settexcoords(s.ex,s.ey);
		temp[2].settexcoords(s.x, s.y);
		temp[3].settexcoords(s.ex, s.ey);
		temp[4].settexcoords(s.ex,s.y);
		temp[5].settexcoords(s.x, s.y);
		return temp;
	}
	public vertex2d[] triangle(int pixcount){
		
		vertex2d[] temp = new vertex2d[]{new vertex2d(0,0),new vertex2d((pixcount/width)*2,0),new vertex2d(0,(pixcount/width)*2)};
		temp[0].settexcoords(0, 1);
		temp[1].settexcoords(1,1);
		temp[2].settexcoords(0, 0);
		return temp;
	}
	public vertex2d[] trianglei(int pixcount){
		vertex2d[] temp = new vertex2d[]{new vertex2d((pixcount/width)*2,0),new vertex2d((pixcount/width)*2,(pixcount/width)*2),new vertex2d(0,(pixcount/width)*2)};
		temp[0].settexcoords(1, 1);
		temp[1].settexcoords(1,0);
		temp[2].settexcoords(0, 0);
		return temp;
	}
}
