package com.Core.graphics.GUI;

import com.Core.Gamecore;

public class Clickzone {// invisible button basically
	private int x, y, width, height;
	private Gamecore core;
	private boolean stillpressed = false;
	public Clickzone(int x, int y, int width, int height, Gamecore core) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.core = core;
	}

	public boolean checkpress() {
				
		if (core.mx >= x && core.mx <= x + width) {
			if (core.my >=  y && core.my <= y + height) {

				
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
		return false;
	}
	public void tick(){
		if(!core.button1){
			stillpressed = false;
		}
	}

	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
}
