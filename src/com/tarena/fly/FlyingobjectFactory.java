package com.tarena.fly;

public class FlyingobjectFactory {
	
	private int index;
	private String statename;
	
	public FlyingobjectFactory(String name) {
		statename = name;
	}
	
	public FlyingObject create(int enmeyindex) {
		this.index = enmeyindex;
		
		if(statename.equals("Boss"))
			return new Boss();
		
		if (index < 4) {
			return new Bee();
		} else if(index==10){
			return new Star();
		} else {
			return new Airplane();
		}

	}

}
