package com.Core.world.Tile;

import com.Core.Gamecore;
import com.Core.Util.infopack;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;



public class space extends Tile{
	private infopack info;
	private Gamecore core;
	private GB graphics;
	public int Id = 0;
	public space(Gamecore core){//refer to for basic tile infomation
		super(core);//required
		int texture = core.random.nextInt(8);
		info = new infopack();//required
		info.a = "space";//name
		info.ai = 0;//id
		info.av = new vertex2d(0,0);//position
		this.core = core;//required
		graphics = new GB(core);//graphics object
		if(texture == 0){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 0, 127,15)), core.world.tiles.gettexture());}
		if(texture == 1){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 16, 127,31)), core.world.tiles.gettexture());}
		if(texture == 2){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 32, 127,47)), core.world.tiles.gettexture());}
		if(texture == 3){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 48, 127,63)), core.world.tiles.gettexture());}
		if(texture == 4){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 64, 127,79)), core.world.tiles.gettexture());}
		if(texture == 5){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 80, 127,95)), core.world.tiles.gettexture());}
		if(texture == 6){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 96, 127,111)), core.world.tiles.gettexture());}
		if(texture == 7){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 112, 127,127)), core.world.tiles.gettexture());}
		
		
	}
	public void init(){
		int texture = core.random.nextInt(8);
		info = new infopack();//required
		info.a = "space";//name
		info.ai = 0;//id
		info.av = new vertex2d(0,0);//position
		this.core = core;//required
		graphics = new GB(core);//graphics object
		if(texture == 0){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 0, 127,15)), core.world.tiles.gettexture());}
		else if(texture == 1){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 16, 127,31)), core.world.tiles.gettexture());}
		else if(texture == 2){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 32, 127,47)), core.world.tiles.gettexture());}
		else if(texture == 3){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 48, 127,63)), core.world.tiles.gettexture());}
		else if(texture == 4){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 64, 127,79)), core.world.tiles.gettexture());}
		else if(texture == 5){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 80, 127,95)), core.world.tiles.gettexture());}
		else if(texture == 6){graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 96, 127,111)), core.world.tiles.gettexture());}
		else {graphics.createobject(core.world.shape.square(32, core.world.tiles.getcoords(112, 112, 127,127)), core.world.tiles.gettexture());}
		
	}
	public infopack getinfo(){
		return info;
	}
	public void setposition(int x , int y){
		info.av.setcoordinates(x, y);
		graphics.translate(info.av.getx(),info.av.gety());
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
