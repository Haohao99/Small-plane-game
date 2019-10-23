package com.tarena.fly;

/**
 * Bullets: FlyingObject
 */
public class Bullet extends FlyingObject  {
	private int speed = 3;  //移动的速度
	
	/** Initial data */
	public Bullet(int x,int y){
		this.x = x;
		this.y = y;
		this.image = ShootGame.bullet;
	}

	/** move */
	@Override
	public void step(){   
		y-=speed;
	}

	/** isOutofBounds */
	@Override
	public boolean outOfBounds() {
		return y<-height;
	}

}
