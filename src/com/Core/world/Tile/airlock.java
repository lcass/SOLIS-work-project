package com.Core.world.Tile;

import com.Core.Gamecore;
import com.Core.Util.infopack;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;

public class airlock extends Tile {
	private infopack info;
	private Gamecore core;
	private boolean opened, bolted, haspower = false;
	private GB open, close;

	public airlock(Gamecore core) {// refer to for basic tile infomation
		super(core);// required
		info = new infopack();// required
		info.a = "Airlock";// name
		info.ai = 3;// id
		info.av = new vertex2d(0, 0);// position
		this.core = core;// required
		open = new GB(core);// graphics object
		close = new GB(core);
		close.createobject(
				core.world.shape.square(32,
						core.world.tiles.getcoords(48, 0, 64, 16)),
				core.world.tiles.gettexture());// required
		open.createobject(
				core.world.shape.square(32,
						core.world.tiles.getcoords(64, 0, 80, 16)),
				core.world.tiles.gettexture());// required
	}
	public void init(){
		info = new infopack();// required
		info.a = "Airlock";// name
		info.ai = 3;// id
		info.av = new vertex2d(0, 0);// position
		this.core = core;// required
		open = new GB(core);// graphics object
		close = new GB(core);
		close.createobject(
				core.world.shape.square(32,
						core.world.tiles.getcoords(48, 0, 64, 16)),
				core.world.tiles.gettexture());// required
		open.createobject(
				core.world.shape.square(32,
						core.world.tiles.getcoords(64, 0, 80, 16)),
				core.world.tiles.gettexture());// required
	}

	public infopack getinfo() {
		return info;
	}

	public void setposition(int x, int y) {
		info.av.setcoordinates(x, y);
		open.translate(info.av.getx(),info.av.gety());
		close.translate(info.av.getx(),info.av.gety());
	}
	public void interact(){
		if(opened){
			opened = false;
			return;
		}
		else{
			opened = true;
			return;
		}
	}

	public vertex2d getcoordinates() {
		return info.av;
	}

	public void render() {
		// graphics.translate(info.av.getx(),info.av.gety());
		if (opened) {
			open.render();
		}
		else{
			close.render();
		}
	}

	public void destroy() {
		open.destroy();
		close.destroy();
	}

}