package com.Core;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.Core.Game.Game;
import com.Core.Util.Logger;
import com.Core.Util.input.Keyinput;
import com.Core.graphics.Camera;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;
import com.Core.graphics.GUI.text;
import com.Core.graphics.texture.spritesheet;
import com.Core.world.World;
import com.Core.world.Tile.Tile;
import com.Core.world.Tile.space;
//author Lucas Spencer
//This class is not to be edited
//editing may cause the game to 
//encounter issues . this is
//a class for game assets
//author Lucas Spencer
//This class is not to be edited
//editing may cause the game to 
//encounter issues . this is
//a class for game assets
public class Gamecore {// for mouse listening
	public boolean running = true;
	public int ticks = 0;
	public int frames = 0;
	public Game game;
	public Camera camera;
	public float width, height;
	public float verticalaspect;
	public World world;
	private Tile tile;
	public boolean button1, button2 = false;
	private GB icon;
	public Keyinput keys= new Keyinput();
	public int mx, my = 0;
//	public Mouselistener m; depreciated
	public spritesheet button,iconsheet;
	public boolean loading = false;
	public Random random;
	public spritesheet font ;
	private String Title = "SOLIS - spacestation builder";
	public Logger logger;
	private text load;
	private GB loadinggb;

	public Gamecore() {
		init();
		start();// start the rendering;
	}

	public static void main(String[] args) {
		new Gamecore();

	}

	private void init() {// MAIN INIT
		random = new Random();
		random.setSeed(38454838574l);
		loading = true;
		width = 800;
		height = 600;
		camera = new Camera(new vertex2d(0
				,0),(int)width,(int)height);
		try {
			Display.setDisplayMode(new DisplayMode((int) width, (int) height));
			Display.setTitle(Title);
			Display.setLocation(0, 0);
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, (int) width, 0, (int) height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		verticalaspect = (float) (height / width);
		font = new spritesheet("com/Core/graphics/texture/sprites/font1.png");
		load = new text(this);
		
		//loadinggb = load.getfontbatch("Loading...",30);
		//loadinggb.translate(240, -300);
		logger = new Logger(5000);
		renderloading();
		world = new World(this, 256, 256);
		tile = new space(this);
	
		icon = new GB(this);
		button = new spritesheet("com/Core/graphics/texture/sprites/Button.png");
		iconsheet = new spritesheet("com\\Core\\graphics\\texture\\sprites\\Icon.png");

		icon.createobject(
				world.shape.rect(iconsheet.getcoords(0, 0, 199, 149), (int) width,(int)height),
				iconsheet.gettexture());
		icon.translate(0,-(int)height);
//		m = new Mouselistener(this); depreciated
	
		logger.log("LOGGING STARTED OF SOLIS");

		
		game = new Game(this);
		// WORLD AND TILE SETUP
		world.t.init();
		
		logger.log("Tiles added successfully");
		// FINILIZATION
		//world.setmap();
		logger.log("Game initialized succesfully");
		//tile = new space(this);
	}
	

	public void start() {
		long lasttime = System.nanoTime();
		double nspertick = 1000000000D / 60.0;

		long lasttimer = System.currentTimeMillis();
		double detla = 0;

		while (running) {

			long now = System.nanoTime();
			detla += (now - lasttime) / nspertick;
			lasttime = now;
			while (detla >= 1) {

				ticks++;
				tick();
				detla -= 1;
			}

			if (System.currentTimeMillis() - lasttimer >= 1000) {
				lasttimer += 1000;
				System.out.println(frames + "," + ticks);
				frames = 0;
				ticks = 0;

			}

			render();
			frames++;

		}
		destroy();

	}

	float tickcount = 0;

	private void tick() {
		keys.tick();
		//camera.translate(1f, 0f);
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		mx = (int)( (b.getX() - Display.getX()) - 4);
		my = (int)(( b.getY() - Display.getY()) - 4);//- the width of the frame .
		//delta_x = (mx / 2) - (play.getx() - xoffset) - 16;
		//delta_y = (my / 2) - (play.gety() - yoffset) - 16;
		//angle = Math.atan2(delta_x, delta_y);
		button1 = Mouse.isButtonDown(0);//left button
		button2 = Mouse.isButtonDown(1);//right button
		if (mx < 0) {
			mx = 0;
		}
		if (my < 0) {
			my = 0;
		}
		if (mx > width) {
			mx = 0;
		}
		if (my > height) {
			my = 0;
		}
		tickcount++;
		if (Display.isCloseRequested()) {
			running = false;
		}
		game.tick();
		world.tick();

	}

	private void destroy() {
		logger.dumplogs();
		icon.destroy();
		world.kill();
		game.destroy();
		Display.destroy();
		
	}

	public void kill() {// kills the entire game
		running = false;
	}

	private void render() {
		if (running) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

			if (loading) {
				icon.render();
			}
			else{
				world.render();
			}
			 game.render();
			
			 
			Display.update();
		}
	}
	public void renderloading(){
		//loadinggb.render();
		Display.update();
	}
}
