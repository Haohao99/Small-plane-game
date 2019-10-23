package com.tarena.fly;


public class SimpleControl {
	Command cmd;
	
	public SimpleControl() {
		
	}
	
	public void setCommand(Command command) {
		cmd = command;
	}
 
	public void buttonWasPressed() {
		cmd.execute();
	}
}
