package com.tarena.fly;


public class Name {
	private static Name uniqueName;
	private String heroname;
	
	private Name() {}
	
	public static Name createName() {
		if( uniqueName ==null) {
			uniqueName = new Name();
		}
		return uniqueName;
	}
	
	public void setName() {
		heroname = "HERO";
	}
	
	public String getName() {
		return heroname;
	}

}
