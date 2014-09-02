package com.Core.world.Tile;

import com.Core.Gamecore;
import com.Core.Util.infopack;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;



public class floor extends Tile{
	private infopack info;
	private Gamecore core;
	private GB graphics;
	public floor(Gamecore core){//refer to for basic tile infomation
		super(core);//required
		info = new infopack();//required
		info.a = "Floor tile";//name
		info.ai = 2;//id
		info.av = new vertex2d(0,0);//position
		this.core = core;//required
		graphics = new GB(core);//graphics object
		graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(32, 0, 48,16)), core.world.tiles.gettexture());//required
	}
	public void init(){
		info = new infopack();//required
		info.a = "Floor tile";//name
		info.ai = 2;//id
		info.av = new vertex2d(0,0);//position
		this.core = core;//required
		graphics = new GB(core);//graphics object
		graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(32, 0, 48,16)), core.world.tiles.gettexture());//required
	}
	public infopack getinfo(){
		return info;
	}
	public void setposition(int x , int y){
		info.av.setcoordinates(x, y);
		//graphics.translate(info.av.getx(),info.av.gety());
	}
	public vertex2d getcoordinates(){
		return info.av;
	}
	public void render(){
		graphics.translate(info.av.getx(),info.av.gety());
		graphics.render();
	}
	public void destroy(){
		graphics.destroy();
	}
	public GB getgraphicsobject(){//used rarely when mimicing other objects graphics such as with cloaking code.
		return graphics;
	}
}