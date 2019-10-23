package com.tarena.fly;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class GameFramework {
	private Map<String, Mygame> typeToGame;
	
	public GameFramework() {
		this.typeToGame = new HashMap<String, Mygame>();
	}
	
	public void addGame(String gameType, Mygame game) {
		this.typeToGame.put(gameType, game);
	}
	
	public void play(String gameType) throws IOException {
		Mygame game = this.typeToGame.get(gameType);
		if(game != null) {
			game.run();
		}
		else {
			System.err.println("Undefined game type.");
		}
	}
	
}
