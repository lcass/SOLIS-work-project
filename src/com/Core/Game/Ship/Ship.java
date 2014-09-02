package com.Core.Game.Ship;

import com.Core.Gamecore;
import com.Core.Game.Entity.Entity;
import com.Core.graphics.GB;
import com.Core.world.Tile.Tile;

public class Ship{//handles out of dock activites within the ship.
	public int site;
	public float x,y = 0;
	public boolean controlable = false;
	public Entity captain = null;
	public Entity[] crew = null;
	public Tile[][] components = null;
	public FreeTile[] Tiles;
	private GB ship;
	private Gamecore core;
	public Ship(Gamecore core){
		this.core = core;
		ship = new GB(core);
		components = new Tile[256][256];//ill fix this later I know its painful.
	}
	public void tick(){
		
	}
	public void render(){
		
	}
	public Entity getcrew(int i){
		if(crew[i] != null){
			return crew[i];
		}
		else{
			core.logger.log("Invalid request at " + i);
			return null;
		}
	}
	public void move(int x, int y){
		this.x = x;
		this.y = y;
		
	}
	public void generateship(){
		
		for(int x= 0; x < 256;x ++){
			for(int y = 0; y< 256;y++){
				if(core.world.gettile(x, y)!= null){
					components[x][y] = core.world.gettile(x, y);
				}
			}
		}
	}
}
