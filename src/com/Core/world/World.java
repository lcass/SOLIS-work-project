package com.Core.world;

import com.Core.Gamecore;
import com.Core.Util.internalcodes;
import com.Core.graphics.GB;
import com.Core.graphics.shapes;
import com.Core.graphics.vertex2d;
import com.Core.graphics.texture.spritesheet;
import com.Core.world.Tile.Tile;
import com.Core.world.Tile.Tiles;

public class World {
	private Tile[][] Map;// called map basically just the components of a ship
	public Tiles t;
	private int width, height = 0;
	public spritesheet tiles;
	public shapes shape;
	private GB dock;
	public spritesheet items; // unsued
	private Gamecore core;

	private boolean state = false;

	public World(Gamecore core, int width, int height) {
		t = new Tiles(core);
		this.width = width;
		this.height = height;
		this.core = core;
		shape = new shapes(core);
		Map = new Tile[width][height];
		tiles = new spritesheet(
				"com/Core/graphics/texture/sprites/blocksprites.png");
		dock = new GB(core);
		spritesheet docksprite = new spritesheet(
				"com/Core/graphics/texture/sprites/Dock.png");
		dock.createobject(
				shape.square(1000 * 4, docksprite.getcoords(0, 0, 999, 999)),
				docksprite.gettexture());
		dock.translate(0, -4000);

	}

	/*
	 * public void setmap() { //depreciated for different methods state = true;
	 * for (int x = 0; x < width; x++) { for (int y = 0; y < height; y++) { Tile
	 * temp = t.getnew("space"); if (temp != null) { Map[x][y] = temp;
	 * temp.setposition(x, y); } else {
	 * core.logger.printerror("FATAL ERROR: NO TILE DEFINED"); core.kill(); } }
	 * } state = false; }
	 */
	public boolean checktile(int x, int y, Tile t){
		if(x > width/32 || x < 0 || y > height/32 || y < 0){
			return false;
		}
		if(Map[x][y] != null){
			if(Map[x][y].getinfo().a == t.getinfo().a){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	public void addtile(int x, int y,Tile t){
		if(Map[x][y] != null){
			Map[x][y].destroy();
		}
		Map[x][y] = t;
	}
	public void removetile(int x, int y){
		if(Map[x][y] == null) return;
		Map[x][y].destroy();
		Map[x][y] = null;
	}
	public int getstate() {
		if (state == true) {
			return internalcodes.WORKING;
		} else {
			return internalcodes.NORMAL;
		}
	}

	public void kill() {
		core.logger.log("Killing world");
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (Map[x][y] != null) {
					Map[x][y].destroy();
					Map[x][y] = null;
				}
			}
		}
	}

	public void tick() {

	}
	public Tile[][] getmap(){
		return Map;
	}
	public Tile gettile(int x, int y){
		if(Map[x][y] != null){
			return Map[x][y];
		}
		else{
			return null;
		}
	}

	public void render() {
		int width, height, sx, sy = 0;
		width = (int) core.width / 32;
		height = (int) core.height / 32;
		vertex2d cam = core.camera.getposition();
		sx = (int) -(cam.getx() / 32);
		sy = (int) (cam.gety() / 32);
		if (core.game.indock) {
			dock.translate(cam.getx(), -4000 + (cam.gety()));
			//System.out.println(cam.getx());
			dock.render();
		}
		for(int x = sx;x < width + sx;x++){
			for(int y = sy;y < height + sy + 3;y++){
				if(Map[x][y] != null){
					Map[x][y].setposition((int)((x*32) + cam.getx()), (int)-((y*32) - cam.gety()));
					Map[x][y].render();
				}
			}
		}

	}

}
