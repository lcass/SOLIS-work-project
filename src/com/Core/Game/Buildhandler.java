package com.Core.Game;

import com.Core.Gamecore;
import com.Core.graphics.GB;
import com.Core.graphics.GUI.Clickzone;
import com.Core.graphics.GUI.text;
import com.Core.world.Tile.Tile;

public class Buildhandler {// just to make this slightly cleaner
	public Inventoryslot[] inventory = new Inventoryslot[200];// lets make
																// hangar
																// building be

	// massive;
	private Gamecore core;
	private int pos = 0;
	private Tile t;
	public int selected = 0;
	private text text;
	private GB name;
	private int x = 0;
	private String curname = "";
	private Clickzone up;
	private Clickzone down;
	private Clickzone[] clickzones = new Clickzone[15];
	private boolean setup = false;
	public Buildhandler(Gamecore core) {
		this.core = core;
		text = new text(core);
		name = new GB(core);
		up = new Clickzone((int) core.width -60,40,10,15,core);
		
		down= new Clickzone((int) core.width -60,60,10,15,core);
		for (int i = 0; i < clickzones.length; i++) {
			clickzones[i] = new Clickzone((int) core.width - 40,
					(6 + ((i + 1) * 38)), 36,
					36, core);
		}
		x = 162;
		
				

	}

	public void additem(Tile t, int cost, int number) {
		//t.init();
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				if (inventory[i].getobject().getinfo().a == t.getinfo().a) {
					inventory[i].modifystack(number);
				
					if (inventory[i].getstacksize() < 10000) {
						inventory[i].textstorage = text.getfontbatch(""
								+ inventory[i].getstacksize(), 8);
					} else {
						inventory[i].textstorage = text.getfontbatch("....", 8);
					}
					return;
				}
			}
			if (inventory[i] == null) {
				inventory[i] = new Inventoryslot(t, number, cost);
				if (inventory[i].getstacksize() < 10000) {
					inventory[i].textstorage = text.getfontbatch(""
							+ inventory[i].getstacksize(), 8);
				} else {
					inventory[i].textstorage = text.getfontbatch("....", 8);
				}
				return;
			}
		}
		
		core.logger.log("No slots remaining");
	}

	public void removeitem(int i) {
		if (inventory[i] != null) {
			inventory[i] = null;
		}
	}

	public Tile takeitem(int pos, int amount) {
		if (inventory[pos] != null) {
			if (inventory[pos].getstacksize() > amount) {
				inventory[pos].modifystack(-amount);
				return inventory[pos].gettile();
			} else if (inventory[pos].getstacksize() == amount) {
				inventory[pos] = null;
				return inventory[pos].gettile();
			}
		}
		return null;

	}

	public Tile gettile(int pos) {
		if (inventory[pos] != null) {
			return inventory[pos].gettile();

		} else {
			return null;
		}
	}

	public Tile getexacttile(int pos) {
		if (inventory[pos] != null) {
			return inventory[pos].getobject();
		} else {
			return null;
		}
	}

	public int getamount(int pos) {
		if (inventory[pos] != null) {
			return inventory[pos].getstacksize();
		} else {
			return 0;
		}
	}

	public void tick() {
		down.tick();
		up.tick();
		if(up.checkpress()){
			if(pos>0){
				pos--;
			}
		}
		if(down.checkpress()){
			if(pos<200){
				pos++;
			}
		}
		for (int i = 0; i < 15; i++) {
			clickzones[i].tick();
			if (clickzones[i].checkpress()) {
				selected = pos + i;
				
			}
		}
	}

	public void render() {
		for (int i = 1; i <= 15; i++) {
			if (getexacttile(pos + (i - 1)) != null) {
				if (inventory[pos + (i - 1)].prev != inventory[pos + (i - 1)].number) {
					inventory[pos + (i - 1)].prev = inventory[pos + (i - 1)].number;
					inventory[pos + (i - 1)].textstorage.destroy();
					if (inventory[pos + (i -1)].getstacksize() < 10000) {
						inventory[pos+(i - 1)].textstorage = text.getfontbatch(""
								+ inventory[pos+(i - 1)].getstacksize(), 8);
					} else {
						inventory[pos+(i - 1)].textstorage = text.getfontbatch("....", 8);
					}
				}
				
				getexacttile(pos + (i - 1)).setposition(
						(int) core.width - (100 - (x))
								+ 56, -(10 + (i * 38)));
				inventory[pos + (i - 1)].textstorage.translate((int) core.width
						- (100 - (x)) + 56,
						-(10 + (i * 38)));
				
				
				getexacttile(pos + (i - 1)).render();
				inventory[pos + (i - 1)].textstorage.render();
			}
		}
		if(inventory[selected] != null){
			getexacttile(selected).setposition((int) core.width - (100 - (x ))
					+ 6, -48);
			getexacttile(selected).render();
			if(curname != getexacttile(selected).getinfo().a){
				name = text.getfontbatch(getexacttile(selected).getinfo().a, 8);
				curname = getexacttile(selected).getinfo().a;
			}
			name.translate((int) core.width - (100 - (x))
					-60, -10);
			name.render();
		}
	}
	public Inventoryslot getselected(){
		return inventory[selected];
	}
	public void changepos(int x){
		this.x += x ;
	}
}
