package com.tarena.fly;

import java.util.Random;

public class Star extends FlyingObject implements Award{
	private int xspeed = 2;
	private int yspeed = 2;
	private int awardType;
	private int index = 1;
	
	public Star() {
		this.image = ShootGame.star;
		width = image.getWidth();
		height = image.getHeight();
		y = -height;          
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);
		awardType = 2;
	}
	
	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		return y>ShootGame.HEIGHT;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		x += xspeed;
		y += yspeed;
		if(x > ShootGame.WIDTH-width){  
			xspeed = -1;
		}
		if(x < 0){
			xspeed = 1;
		}
	}

	@Override
	public int getType() {
		return awardType;
	}

}
