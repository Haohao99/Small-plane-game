package com.tarena.fly;


public abstract class AbstractState implements IState{
	
	
	private String mygamestate;
	
	public AbstractState(String gamestate) {
		mygamestate = gamestate;
	}
	
	@Override
	public String name() {
		return mygamestate;
	}

	@Override
	public void execute() {
		
		System.out.print(mygamestate+"\n");
		
		
	}
	
	
	
	
	
	
}
