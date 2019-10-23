package com.tarena.fly;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public abstract class AbstractGame extends JPanel implements Mygame{
	public static final int WIDTH = 400; // The Width of Panel
	public static final int HEIGHT = 654; // The Height of Panel
	
	public void run() {
		JFrame frame = new JFrame("Fly");
		
		ShootGame facade = new ShootGame(); // panel
		
		Name name = Name.createName();
		name.setName();
		name.setName();//TODO add the name in the screen;
		Life lf = facade.getLife();
		lf.addObserver(facade);
		
		SimpleControl control = new SimpleControl();
		IncreaseBulletCommand increaseb = new IncreaseBulletCommand(facade.hero);
		control.setCommand(increaseb);
		control.buttonWasPressed();
		
		frame.add(facade); // add panel to frame
		frame.setSize(WIDTH, HEIGHT); // set size
		frame.setAlwaysOnTop(true); // put frame on the top
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // default close
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // set windows logo
		frame.setLocationRelativeTo(null); // set initial window size
		frame.setVisible(true); // use paint
		facade.action();
	}
}
