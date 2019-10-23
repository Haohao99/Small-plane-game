package com.tarena.fly;

import java.util.Random;

/** Bees */
public class Bee extends FlyingObject implements Award{
	private int xSpeed = 1;   //x axis speed
	private int ySpeed = 2;   //y axis speed
	private int awardType;    
	
	/** initial data */
	public Bee(){
		this.image = ShootGame.bee;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);
		awardType = rand.nextInt(2);   //randomly choose awardtype
	}
	
	/** get award type */
	public int getType(){
		return awardType;
	}

	/** isOutofBounds */
	@Override
	public boolean outOfBounds() {
		return y>ShootGame.HEIGHT;
	}

	/** flying sideways  */
	@Override
	public void step() {      
		x += xSpeed;
		y += ySpeed;
		if(x > ShootGame.WIDTH-width){  
			xSpeed = -1;
		}
		if(x < 0){
			xSpeed = 1;
		}
	}
}