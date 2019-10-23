package com.tarena.fly;

import java.util.Random;


/**
*
* Enemy airplane: FlyingObject
*/
public class Airplane extends FlyingObject implements Enemy {
	
	private int speed = 3;  //move speed
	
	/** initial */
	public Airplane(){
		this.image = ShootGame.airplane;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;          
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);
	}
	
	/** get score */
	@Override
	public int getScore() {  
		return 5;
	}

	/** //out of Bounds */
	@Override
	public 	boolean outOfBounds() {   
		return y>ShootGame.HEIGHT;
	}

	/** move */
	@Override
	public void step() {   
		y += speed;
	}
	
	

}

