package com.tarena.fly;
import java.awt.image.BufferedImage;


/**
 * Hero, Flyingobject
 */
public class Hero extends FlyingObject {
	
	private BufferedImage[] images = {};  //hero picture
	private int index = 0;                //hero picture index
	
	private int doubleFire ;  
	  
	
	/** initial data */
	public Hero(){
		
		doubleFire = 0;   //
		images = new BufferedImage[]{ShootGame.hero0, ShootGame.hero1}; //hero picture group
		image = ShootGame.hero0;  
		width = image.getWidth();
		height = image.getHeight();
		x = 150;
		y = 400;
	}
	
	public int isDoubleFire() {
		return doubleFire;
	}

	public void setDoubleFire(int doubleFire) {
		this.doubleFire = doubleFire;
	}
	
	public void addDoubleFire(){
		doubleFire = 40;
	}
	
	
	
	public void moveTo(int x,int y){   
		this.x = x - width/2;
		this.y = y - height/2;
	}

	@Override
	public boolean outOfBounds() {
		return false;  
	}

	public Bullet[] shoot(){   
		int xStep = width/4;      //four and half
		int yStep = 20;  
		if(doubleFire>0){ 
			Bullet[] bullets = new Bullet[2];
			bullets[0] = new Bullet(x+xStep,y-yStep);  //y-yStep the distance between bullet and airplane
			bullets[1] = new Bullet(x+3*xStep,y-yStep);
			return bullets;
		}else{  
			Bullet[] bullets = new Bullet[1];
			bullets[0] = new Bullet(x+2*xStep,y-yStep);  
			return bullets;
		}
	}

	@Override
	public void step() {
		if(images.length>0){
			image = images[index++/10%images.length];  //switch picture hero0£¬hero1
		}
	}
	
	/** isHited */
	public boolean hit(FlyingObject other){
		
		int x1 = other.x - this.width/2;                 //x axis least distance
		int x2 = other.x + this.width/2 + other.width;   //x axis largest distance
		int y1 = other.y - this.height/2;                //y axis least distance
		int y2 = other.y + this.height/2 + other.height; //y axis largest distance
	
		int herox = this.x + this.width/2;               //hero x axis to center
		int heroy = this.y + this.height/2;              //hero y axis to center
		
		return herox>x1 && herox<x2 && heroy>y1 && heroy<y2;   
	}
	
}










