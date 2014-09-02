package com.Core.graphics.GUI;

import com.Core.Gamecore;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;

public class Button {// Mostly complete
	private vertex2d position, aposition;
	private int type;
	private GB graphicsp, graphicsd, textinf;
	private boolean pressed, stillpressed = false;
	private String text;
	private int pos;
	private text t;
	private Gamecore core;

	public Button(Gamecore core, int x, int y, String text, int type, int pos) {
		position = new vertex2d(x, (-y));
		aposition = new vertex2d(x, y);
		this.text = text;

		this.type = type;
		pressed = false;
		this.core = core;
		this.pos = pos;
		graphicsp = new GB(core);
		graphicsd = new GB(core);
		graphicsd.createobject(core.world.shape.rect(
				core.button.getcoords(0, 0, 143, 28), 143, 28), core.button
				.gettexture());
		graphicsp.createobject(core.world.shape.rect(
				core.button.getcoords(0, 30, 143, 58), 143, 28), core.button
				.gettexture());
		graphicsd.translate(position.getx(), position.gety());
		graphicsp.translate(position.getx(), position.gety());
		t = new text(core);
		textinf = t.getfontbatch(text);
		textinf.translate(position.getx() + 10, position.gety() + 8);

	}

	public vertex2d getposition() {
		return position;
	}

	public void setposition(vertex2d vert) {
		position.addtovertcoords(vert.getx(), vert.gety());
		aposition.addtovertcoords(vert.getx(), -vert.gety());
	}

	public void press() {
		if (type == 0) {
			pressed = true;
		}
		if (type == 1) {
			if (pressed) {
				pressed = false;
				return;
			} else {
				pressed = true;
				return;
			}
		}
	}

	public void unpress() {// only use on non toggle butons
		pressed = false;
	}

	public boolean checkpress() {
		
		if (core.mx >= aposition.getx() && core.mx <= aposition.getx() + 128) {
			if (core.my >= aposition.gety() && core.my <= aposition.gety() + 26) {

				press();
				if(stillpressed){
					return false;
				}
				if (core.button1) {
					stillpressed = true;
					
					return true;
				} else
					
				return false;

			}
		}
		unpress();
		return false;
	}

	public void render() {
		if (!pressed) {
			graphicsd.translate(position.getx(), position.gety());
			graphicsd.render();
		} else {
			graphicsp.translate(position.getx(),position.gety());
			graphicsp.render();

		}
		textinf.translate(position.getx() + 5,position.gety() + 7);
		textinf.render();
	}

	public int getpos() {// returns stored array position if defined
		return pos;
	}

	public void updatepress() {
		stillpressed = false;
	}

	public void destroy() {
		graphicsp.destroy();
		graphicsd.destroy();
		textinf.destroy();
	}
}
