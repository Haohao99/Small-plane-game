package com.tarena.fly;

import java.awt.image.BufferedImage;

/**
 * Flyingobjects (enemy,bees,bullet,hero)
 * 
 */
public abstract class FlyingObject {
	protected int x;    //x axis
	protected int y;    //y axis
	protected int width;    
	protected int height;   
	protected BufferedImage image;   

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * isOutofBounds
	 * @return true or false
	 */
	public abstract boolean outOfBounds();
	
	/**
	 * Flyingobjects move
	 */
	public abstract void step();
	
	/**
	 * isHit
	 * @param Bullet 
	 * @return true if is Hit
	 */
	public boolean shootBy(Bullet bullet){
		
		int x = bullet.x;  
		int y = bullet.y;  
		return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
	}

}
