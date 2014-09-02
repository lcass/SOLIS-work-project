package com.Core.world.Tile;

import com.Core.Gamecore;
import com.Core.Util.internalcodes;

public class Tiles {//Used for the static objcts 
	Tile[] tiles = new Tile[256];
	private Gamecore core;

	public Tiles(Gamecore core) {
		this.core = core;
	}

	public Tile getnew(String name) {
		Tile t = null;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null) {
				if (tiles[i].getinfo().a == name) {
					t = returntile(i);
					t.init();
				}
			}
		}
		return t;
	}

	public int addtile(Tile t, int id) {// adds a static tile for reference
		if (tiles[id] == null) {
			tiles[id] = t;
		} else {
			return internalcodes.SLOTFULL;
		}
		return internalcodes.NORMAL;
	}

	public Tile returntile(int i) { // returns the relative object to an id ,

		// getnew is simply a convenient cast
		Tile tmp;
		if(tiles[i] !=null){
			return tiles[i].returnnew();
		}
		else{
			return new space(core);
		}
	}

	public void newtile(Tile t) {
		if (t == null) {
			return;
		}
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null) {

				if (tiles[i].getinfo().a == t.getinfo().a) {
					return;
				}
			}
			else{
				tiles[i] = t;
				return;
			}
		}
	}
	public  void init(){
		newtile(new space(core));
		newtile(new wall(core));
		newtile(new floor(core));
		newtile(new airlock(core));
		newtile(new hazard(core));
		newtile(new solar(core));
	}
}

(Name = "Example")...//Declare new functions and classes through brackets
	Vbo object.
	(init_<>)...//parameters are stored within <> and indentation is done through ...
		print<"This is an example">.//end the function call with a .
		load<>.
	...
	(load<>)...
		Display.starttick<60>.
		?<!object>...
				object = Vbo.create<v2d.list<0,0,1,1,1,0,0,1>,Texture.load<"Test.png">.//no creating objects simply function calls that return them
		...	
		Vbo.assign<object>.
		+
		+
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		