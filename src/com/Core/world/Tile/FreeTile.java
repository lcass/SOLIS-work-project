package com.Core.world.Tile;

import com.Core.Gamecore;
import com.Core.graphics.vertex2d;

public class FreeTile extends Tile{//Tile with no GB instead is of multiple forms and figures out what it is on its own
	private vertex2d position;
	private Gamecore core;
	public FreeTile(Gamecore core,int x, int y ,int id){
		super(core);
		this.core = core;
		position = new vertex2d(x,y);
	}
	public void tick(){
		
	}
	public void interact(){
		
	}
	public void render(){
		
	}
	public void translate(int x, int y){
		
	}
	public Tile returnactual(){
		
	}
}
