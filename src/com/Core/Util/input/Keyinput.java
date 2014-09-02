package com.Core.Util.input;
import org.lwjgl.input.Keyboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyinput{
	public boolean a = false;
	public boolean b = false;
	public boolean w = false;
	public boolean s = false;
	public boolean d = false;
	public void tick(){
		a = Keyboard.isKeyDown(Keyboard.KEY_A);
		b = Keyboard.isKeyDown(Keyboard.KEY_B);
		w = Keyboard.isKeyDown(Keyboard.KEY_W);
		s = Keyboard.isKeyDown(Keyboard.KEY_S);
		d = Keyboard.isKeyDown(Keyboard.KEY_D);
	}
}
