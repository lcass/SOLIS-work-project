package com.Core.Game;

import com.Core.Gamecore;
import com.Core.Util.input.Keyinput;
import com.Core.graphics.vertex2d;
import com.Core.graphics.GUI.GUI;
import com.Core.graphics.texture.spritesheet;
import com.Core.world.World;
import com.Core.world.Tile.Tile;
import com.Core.world.Tile.airlock;
import com.Core.world.Tile.floor;
import com.Core.world.Tile.hazard;
import com.Core.world.Tile.solar;
import com.Core.world.Tile.space;
import com.Core.world.Tile.wall;

public class Game {// handles the entire game loading of major events , Gamecore
					// will be a render function and tick updater does not
					// include objects
	private Gamecore core;
	private World world;
	private GUI screen;
	private GUI loading;
	private boolean toggleinvent = false;
	public GUI inventory;
	private Tile t;
	private int camspeed = 4;
	private Keyinput keys;
	private spritesheet playerGUI;
	public boolean indock = true;
	private int[] buttons = new int[200];
	private GUI options;// unused
	private int posa = 200;
	private boolean shown = false;
	private boolean moving = false;
	private boolean inventopen = false;
	private boolean renderinvent = false;
	public Buildhandler bh;
	public int positioninvent = 160;

	public Game(Gamecore core) {
		this.core = core;
		this.world = core.world;
		core.logger.log("Game event handler loaded");
		playerGUI = new spritesheet(
				"com/Core/graphics/texture/sprites/playerGUI.png");
		init();
		setuploading();
		setupscreen();
		setupinventory();
		keys = core.keys;
		bh = new Buildhandler(core);
		bh.additem(new space(core), 25, 9000);
		bh.additem(new airlock(core), 25, 9000);
		bh.additem(new floor(core), 25, 9000);
		bh.additem(new wall(core), 25, 9000);
		bh.additem(new solar(core), 25, 9000);
		bh.additem(new hazard(core), 25, 9000);
		bh.additem(new space(core), 25, 9000);

	}

	private void init() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = -2;
		}
		loading = new GUI((int) core.width, (int) core.height, core);
		screen = new GUI(0, -238, 400, 238, core, playerGUI, 2);

	}

	private void setuploading() {
		buttons[0] = loading.addButton(250, 260, "Play");// returns an integer
															// value to relative
															// array position.
															// All other info is
															// handled
															// automatically
		buttons[1] = loading.addButton(250, 290, "Options");
		buttons[2] = loading.addButton(250, 320, "Exit");

	}

	private void setupscreen() {
		buttons[3] = screen.addButton(3, 30, "Info");
		buttons[4] = screen.addButton(3, 57, "Options");
		buttons[5] = screen.addButton(3, 84, "Inventory");
		buttons[6] = screen.addButton(3, 230, "Show/Hide");
	}

	public void play() {
		core.loading = false;
	}

	public void options() {

	}

	public void exit() {
		core.kill();
	}

	public void render() {

		if (core.loading) {
			loading.render();
		} else {
			screen.render();
		}
		if (renderinvent) {
			
			inventory.render();
			bh.render();
		}

	}

	private boolean sp1 = false;
	private boolean sp2 = false;

	public void tick() {
		loading.tick();
		screen.tick();

		inventory.tick();
		if (!core.button1) {
			sp1 = false;
		}
		if (!core.button2) {
			sp2 = false;
		}
		if (indock && !core.loading) {
			vertex2d cam = core.camera.getposition();
			
			if (bh.inventory[bh.selected] != null) {
				if (!inventopen) {

					if (core.button1
							&& !sp1
							&& !core.world.checktile((int) (core.mx - cam
									.getx()) / 32,
									(int) (core.my + cam.gety() + 3) / 32, bh
											.getselected().getobject())) {
						core.world.addtile((int) (core.mx - cam.getx()) / 32,
								(int) (core.my + cam.gety() + 3) / 32,
								bh.takeitem(bh.selected, 1));

						sp1 = true;
					}
					if (core.button2 && !sp2) {
						core.world.removetile(
								(int) (core.mx - cam.getx()) / 32,
								(int) (core.my + cam.gety() + 3) / 32);
						sp2 = true;
					}
				} else {
					if (core.mx < core.width - 60) {
						if (core.button1
								&& !sp1
								&& !core.world.checktile(
										(int) (core.mx - cam.getx()) / 32,
										(int) (core.my + cam.gety() + 3) / 32,
										bh.getselected().getobject())) {
							core.world.addtile(
									(int) (core.mx - cam.getx()) / 32,
									(int) (core.my + cam.gety() + 3) / 32,
									bh.takeitem(bh.selected, 1));
							// System.out.println("test");
							sp1 = true;
						}
						if (core.button2 && !sp2) {
							core.world.removetile(
									(int) (core.mx - cam.getx()) / 32,
									(int) (core.my + cam.gety() + 3) / 32);
							sp2 = true;
						}
					}
				}
			}
		}
		if (keys.a) {
			if (-core.camera.getposition().getx() >= camspeed) {
				core.camera.translate(-camspeed, 0);
			}
		}
		if (keys.w) {
			if (core.camera.getposition().gety() >= camspeed) {
				core.camera.translate(0, camspeed);
			}
		}
		if (keys.d) {

			if (-core.camera.getposition().getx() <= (4000 - (core.width
					+ camspeed - 1))) {
				core.camera.translate(camspeed, 0);

			}
		}
		if (keys.s) {
			if (core.camera.getposition().gety() <= 4000 - camspeed
					- core.height) {

				core.camera.translate(0, -camspeed);
			}
		}
		if (core.loading) {
			if (loading.getbutton(buttons[0]).checkpress()) {

				play();
			}
			if (loading.getbutton(buttons[1]).checkpress()) {
				options();

			}
			if (loading.getbutton(buttons[2]).checkpress()) {
				exit();
			}
		} else {
			if (screen.getbutton(buttons[3]).checkpress()) {

			}
			if (screen.getbutton(buttons[4]).checkpress()) {

			}
			if (screen.getbutton(buttons[5]).checkpress()) {
				openinvent();
				toggleinvent = true;

			}
			if (screen.getbutton(buttons[6]).checkpress()) {
				shown = !shown;
				moving = true;
			}
			if (moving) {
				
				if (shown) {
					if (posa > 3) {
						posa -= 3;
						screen.translate(new vertex2d(0,3));
						
					}
					else{
						moving = false;
					}

				} else {
					if (posa <197) {
						posa +=3;
						
						screen.translate(new vertex2d(0,-3));
						
					}
					else{
						moving = false;
					}
				}
			}
			if (toggleinvent) {
				if (inventopen) {
					renderinvent = true;
					if (positioninvent > 2) {

						positioninvent -= 2;
						bh.changepos(-2);
						inventory.translate(new vertex2d(-2, 0));
					} else {
						toggleinvent = false;
					}
				} else {
					if (positioninvent < 158) {
						positioninvent += 2;
						bh.changepos(2);
						inventory.translate(new vertex2d(2, 0));
					} else {
						toggleinvent = false;
						renderinvent = false;
					}
				}
			}
		}
		if(!core.loading && indock){
			bh.tick();
		}
	}

	public void openinvent() {
		inventopen = !inventopen;
	}

	public void destroy() {// used for cancelling events only. Object deletion
							// occurs in map.
		loading.destroy();
		screen.destroy();
		inventory.destroy();
	}

	public void setupinventory() {
		inventory = new GUI((int) core.width - 40, -600, 200, 300, core,
				new spritesheet(
						"com/Core/graphics/texture/sprites/inventory.png"), 2);
	}

}
