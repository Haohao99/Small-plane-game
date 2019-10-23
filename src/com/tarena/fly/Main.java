package com.tarena.fly;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	
	public static void main (String[] args) throws IOException {
		
		String Playername = JOptionPane.showInputDialog("Hello, what's your name?");
		
		System.out.printf("Hello %s",Playername);
		
		GameFramework framework = new GameFramework();
		 
		framework.addGame("Shoot", new ShootGame());
		
		framework.play("Shoot");
		

	}
}
