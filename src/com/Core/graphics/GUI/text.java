package com.Core.graphics.GUI;

import com.Core.Gamecore;
import com.Core.graphics.Flowbuffer;
import com.Core.graphics.GB;
import com.Core.graphics.vertex2d;
import com.Core.graphics.texture.spritecomponent;
import com.Core.graphics.texture.spritesheet;

public class text {
	
	private String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789. ";
	private Flowbuffer fb;
	private Gamecore core;
	public text(Gamecore core){
		this.core = core;

	}
	public GB getfontbatch(String text){
		fb = new Flowbuffer(core);
		GB graphics = new GB(core);
		int[] index = new int[text.length()];
		
		spritecomponent[] coordinates = new spritecomponent[text.length()];
		for(int i = 0; i < text.length();i++){
			index[i] = alphabet.indexOf(text.charAt(i));
			coordinates[i]  = core.font.getcoords(index[i] * 6, 0,(index[i] + 1)*6, 8);
			
		}
		
		vertex2d[] data = fb.getflowbuffer(text.length(), 10, 10, coordinates,8);
		graphics.createobject(data, core.font.gettexture());
		return graphics;
		
	}
	public GB getfontbatch(String text,int size){
		fb = new Flowbuffer(core);
		GB graphics = new GB(core);
		int[] index = new int[text.length()];
		
		spritecomponent[] coordinates = new spritecomponent[text.length()];
		for(int i = 0; i < text.length();i++){
			index[i] = alphabet.indexOf(text.charAt(i));
			coordinates[i]  = core.font.getcoords(index[i] * 6, 0,(index[i] + 1)*6, 8);
			
		}
		
		vertex2d[] data = fb.getflowbuffer(text.length(), size, size, coordinates,size);
		graphics.createobject(data, core.font.gettexture());
		return graphics;
		
	}
}
