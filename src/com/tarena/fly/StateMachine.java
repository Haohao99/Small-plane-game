package com.tarena.fly;

import java.util.Timer;

public class StateMachine {
	
	private IState mystate;
	

	public StateMachine(IState s) {
		// TODO Auto-generated constructor stub
		
		mystate = s;
	}
	
	public void setstate(IState s) {
		mystate = s;
		
	}

	public void action() {
		mystate.execute();
		
	}

	public String returnstate() {
		// TODO Auto-generated method stub
		return mystate.name();
	}

	

}
