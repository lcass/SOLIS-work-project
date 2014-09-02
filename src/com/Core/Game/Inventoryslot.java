package com.Core.Game;

import com.Core.Gamecore;
import com.Core.graphics.GB;
import com.Core.graphics.vertex4d;
import com.Core.graphics.GUI.Clickzone;
import com.Core.world.Tile.Tile;

public class Inventoryslot {
	private Tile object;
	public int number,prev;
	private int cost;
	//private vertex4d bounds;
	public GB textstorage;//again creating a new GB wastes a lot of time so lets just keep this one.
	//public Clickzone cz;
	public Inventoryslot(Tile t, int number,int cost){
		object = t;
		t.init();
		this.cost = cost;
		this.number = number;
		prev = number;
	
	}
	public void changeobject(Tile t){
		object = t;
	}
	public Tile gettile(){
		return object.returnnew();
	}
	public int getstacksize(){
		return number;
	}
	public void modifystack(int i ){
		
		number += i;
	
	}
	public int getcost(){
		return cost;
	}
	public Tile getobject(){
		return object;
	}

}
