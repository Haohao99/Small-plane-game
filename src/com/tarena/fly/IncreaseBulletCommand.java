package com.tarena.fly;

public class IncreaseBulletCommand implements Command{
	Hero hero;
	
	public IncreaseBulletCommand(Hero hero) {
		this.hero = hero;
	}
	@Override
	public void execute() {
		hero.addDoubleFire();
		
	}

}
