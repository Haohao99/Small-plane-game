package com.tarena.fly;

import java.util.Observable;

public class Life extends Observable{
	
	private int life;
	
	public Life() {
		life = 3;
	}
	
	public void addLife(){  
		super.setChanged();
		life++;
		super.notifyObservers();
	}
	
	public void subtractLife(){   
		life--;
	}
	
	public int getLife(){
		return life;
	}
}
