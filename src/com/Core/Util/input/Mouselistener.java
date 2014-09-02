package com.Core.Util.input;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import com.Core.Gamecore;

public class Mouselistener implements MouseInputListener{
	public int x,y = 0;
	public boolean a,b,c = false;
	
	public Mouselistener(Gamecore core){
	}
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == e.BUTTON1){
			a = true;
		}
		if(e.getButton() == e.BUTTON2){
			b = true;
		}
		if(e.getButton() == e.BUTTON3){
			c = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == e.BUTTON1){
			a = false;
		}
		if(e.getButton() == e.BUTTON2){
			b = false;
		}
		if(e.getButton() == e.BUTTON3){
			c = false;
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	

}
