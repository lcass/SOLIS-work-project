package com.Core.world.Tile;

import com.Core.Gamecore;
import com.Core.Util.infopack;
import com.Core.Util.internalcodes;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;
import com.Core.graphics.texture.spritecomponent;

public abstract class Tile implements Cloneable{
	private spritecomponent sp;
	private infopack info;
	private int id;
	public int Id;
	private String name;
	private vertex2d position;
	public GB graphics;
	public Tile(Gamecore core)
	{
		
	}
	public Tile(Gamecore core,String name,int id,spritecomponent sprite,vertex2d position,int pixcount){//use for creating a custom object on site.
		sp = sprite;
		this.id = id;
		this.name = name;
		this.position = position;
	}
	public void init(){//used for the returnnewtile() function
		
	}
	public void render(){
		
	}
	public void tick(){
		
	}
	public void interact(){
		
	}
	public void destroy(){
		
	}
	public void damage(){
		
	}
	public GB getgraphicsobject(){
		return null;
	}
	public infopack getinfo(){
		infopack nul = new infopack();
		nul.code = internalcodes.NOMETHODINCLASS;
		return nul;
	}
	public void setposition(int x, int y){
		
	}
	public Tile returnnew(){
		try {
			Tile temp = (Tile)this.clone();
			temp.init();
			return temp;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Tile returnactual(){
		return null;
	}
}
