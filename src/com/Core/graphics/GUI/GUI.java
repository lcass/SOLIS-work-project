package com.Core.graphics.GUI;

import com.Core.Gamecore;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;
import com.Core.graphics.texture.spritecomponent;
import com.Core.graphics.texture.spritesheet;

public class GUI {// to be setup
	private Button[] buttons = new Button[200];
	private GB[] text = new GB[200];// one gui wont use 200 text hopefully
	private Clickzone[] zones = new Clickzone[200];
	private int width, height = 0;
	private int x, y, ex, ey = 0;
	private Gamecore core;
	private text textcreator;
	private GB background;

	public GUI(int width, int height, Gamecore core) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.ex = x + width;
		this.ey = y + height;
		this.core = core;
	}

	public GUI(int x, int y, int width, int height, Gamecore core,
			spritesheet background, int scaling) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.ex = x + width;
		this.ey = y + height;
		this.core = core;
		setupbackground(background, scaling);
	}

	private void setupbackground(spritesheet b, int scaling) {
		spritecomponent s = b.getcoords(0, 0, b.gettexture().width,
				b.gettexture().height);
		vertex2d[] back = core.world.shape.rect(s, b.gettexture().width
				* scaling, b.gettexture().height * scaling);
		background = new GB(core);// look at

		background.createobject(back, b.gettexture());// 0 tex for default
		background.translate(x, y);
	}

	public int addButton(int x, int y, String text) {
		core.logger.log("Button " + text + " Added");
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == null) {
				buttons[i] = new Button(core, x, y, text, 0, i);
				return i;
			}
		}
		core.logger.log("No free button slots to add button to");
		return -1;

	}

	public int addClickzone(int x, int y, int width, int height) {
		core.logger.log("Clickzone Added");
		for (int i = 0; i < zones.length; i++) {
			if (zones[i] == null) {
				zones[i] = new Clickzone(x, y, width, height, core);
				return i;
			}
		}
		core.logger.log("No free clickzones slots left in GUI");
		core.logger.printerror("NO CLICKZONES");
		return -1;
	}

	public void moveclickzone(int x, int y, int pos) {
		if (zones[pos] != null) {
			zones[pos].translate(x, y);
		} else {
			core.logger.log("Invalid position " + pos);
		}
	}

	public void removebutton(int i) {
		buttons[i] = null;
	}

	public void removetext(int i) {
		text[i] = null;
	}

	public void edittext(int i, String text) {
		System.out.println("Text edited" + " " + text);
		this.text[i] = null;
		this.text[i] = textcreator.getfontbatch(text);
	}

	public int settext(String text,int x , int y) {// generally you wont be drawing new data
										// to a gui in use.
		core.logger.log("New text added to GUI " + text);
		for (int i = 0; i < text.length(); i++) {
			if (this.text[i] == null) {
				this.text[i] = textcreator.getfontbatch(text);
				this.text[i].translate(x, y);
				return i;
			}
		}//really buggy do not use
		return -1;
	}

	public void displaydialogue(String text, int x, int y, int width, int height) {// depreciated

	}

	public void translate(vertex2d v) {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] != null) {
				buttons[i].setposition(v);
				
			} else {
				break;
			}

		}
		for (int i = 0; i < text.length; i++) {
			if (text[i] != null) {
				text[i].translate(v.getx(), v.gety());
			} else {
				break;
			}
		}
		for (int i = 0; i < zones.length; i++) {
			if (zones[i] != null) {
				zones[i].translate((int) v.getx(), (int) v.gety());
			} else {
				break;
			}
		}
	
		background.translate(this.x + v.getx(), this.y + v.gety());
		this.x+=v.getx();
		this.y+=v.gety();
	}

	public void render() {
		if (background != null) {
			background.render();
		}
		for (int i = 0; i < 200; i++) {
			if (buttons[i] != null) {
				buttons[i].render();
			}
			if (text[i] != null) {
				text[i].render();
			}

		}

	}

	public void tick() {
		if (!core.button1) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i] != null) {
					buttons[i].updatepress();
				} else {
					break;
				}

			}
		}

	}

	public Button getbutton(int i) {
		if (i >= 200) {
			return null;
		}
		if (buttons[i] != null) {
			return buttons[i];
		} else {
			return null;
		}
	}

	public void destroy() {
		if (background != null) {
			background.destroy();
		}
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == null) {
				break;
			} else {
				buttons[i].destroy();
			}
		}
		for (int i = 0; i < text.length; i++) {
			if (text[i] == null) {
				break;
			} else {
				buttons[i].destroy();
			}

		}
	}
	public GB gettextGB(String text, int size, int x , int y){
		textcreator = new text(core);//reset the texture;
		GB temp = textcreator.getfontbatch(text, size);
		temp.translate(x, y);
		return temp;
	}

}
