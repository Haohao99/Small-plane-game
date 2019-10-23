package com.tarena.fly;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends AbstractGame implements Observer{
	public static final int WIDTH = 400; // The Width of Panel
	public static final int HEIGHT = 654; // The Height of Panel
	/** Current Game Status: START RUNNING PAUSE GAME_OVER */
	protected int state;
	protected static final int START = 0;
	protected static final int RUNNING = 1;
	protected static final int PAUSE = 2;
	protected static final int GAME_OVER = 3;
	private int score = 0; 
	private Timer timer; 
	private int intervel = 1000 / 100; // time intervel(ms)
	private int time = 40;
	
	

	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage boss;
	public static BufferedImage bee;
	public static BufferedImage star;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;

	private FlyingObject[] flyings = {}; // enemy group
	private Bullet[] bullets = {}; // bullet group
	public Hero hero = new Hero();
	private Life life = new Life();

	static { // initial picture
		try {
			background = ImageIO.read(ShootGame.class
					.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			airplane = ImageIO
					.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			star = ImageIO.read(ShootGame.class.getResource("star.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			boss = ImageIO.read(ShootGame.class.getResource("Boss.png"));
			gameover = ImageIO
					.read(ShootGame.class.getResource("gameover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // draw background
		paintHero(g); // draw hero
		paintBullets(g); // drwa bullets
		paintFlyingObjects(g); 
		paintScore(g); 
		paintState(g); 
	}


	public void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}

	
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(),
					null);
		}
	}


	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.getImage(), f.getX(), f.getY(), null);
		}
	}

	
	public void paintScore(Graphics g) {
		int x = 10; // x axis
		int y = 25; // y axis
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22); // word size
		g.setColor(new Color(0xFF0000));
		g.setFont(font); // set word size
		g.drawString("SCORE:" + score, x, y); // paint score
		y=y+20; 
		g.drawString("LIFE:" + life.getLife(), x, y); // paint life
	}


	public void paintState(Graphics g) {
		switch (state) {
		case START: // start status
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE: // pause status
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER: 
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}

	
	
	public Life getLife() {
		return life;
	}

	/** run program */
	public void action() {
		// listen to mouse
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { // move mouse
				if (state == RUNNING) { // move hero with mouse position
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // click to start
				if (state == PAUSE) { // pause
					state = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { // exit
				if (state == RUNNING) { // if it is not gameover, set pause
					state = PAUSE;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { // mouse click
				switch (state) {
				case START:
					state = RUNNING; // 
					break;
				case GAME_OVER: // clean the panel
					flyings = new FlyingObject[0]; // clear the flying object
					bullets = new Bullet[0]; // clear bullets
					hero = new Hero(); // create hero
					score = 0; // clean score
					state = START; // 
					break;
				}
			}
		};
		this.addMouseListener(l); // listen to mouse action
		this.addMouseMotionListener(l); // listen to mouse movement

		timer = new Timer(); // the main timer
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) { // program state
					enterAction(); // start have a flyingobject
					stepAction(); // move
					shootAction(); // shoor bullet
					bangAction(); // isBullet hit enemy
					outOfBoundsAction(); // delete out of bounds bullets and flyingobject
					checkGameOverAction(); 
				}
				repaint(); 
			}

		}, intervel, intervel);
	}

	int flyEnteredIndex = 0; // count 

	/** flyingobject action */
	public void enterAction() {
		flyEnteredIndex++;
		
		StateMachine statemachine = new StateMachine(new NormalState("Normal"));
		if(flyEnteredIndex%100==0) {
			statemachine.setstate(new BossState("Boss"));
			statemachine.action();
		}else {
			statemachine.setstate(new NormalState("Normal"));
		}
		if (flyEnteredIndex % time == 0) { // initial 400 ms per flyingobject--10*40
			FlyingObject obj = nextOne(statemachine.returnstate()); // randomly create a flying object
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	/** move one step */
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) { // flyingobject move
			FlyingObject f = flyings[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) { // bullet move
			Bullet b = bullets[i];
			b.step();
		}
		hero.step(); // hero move 
	}

	/** flying object move one step */
	public void flyingStepAction() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			f.step();
		}
	}

	int shootIndex = 0; //shoot enemy index

	/** shoot */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // 300 ms per bullet
			Bullet[] bs = hero.shoot(); // hero shoot
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // extend bullets array
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
					bs.length); // 
		}
	}

	/** hit detected */
	public void bangAction() {
		MyIterator iterator = new MyIterator(bullets);
		while(iterator.hasNext()) {
			bang((Bullet)iterator.next());
		}
		
//		for (int i = 0; i < bullets.length; i++) { // loop all bullets
//			Bullet b = bullets[i];
//			bang(b); // isHit
//		}
	}

	/** delete outOfBounds flying objects or bullets*/
	public void outOfBoundsAction() {
		int index = 0; 
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // living flyingobjects
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index++] = f; // keep living flying objects
			}
		}

		index = 0; // resign the index
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // keep the flying objects in the bullets
	}

	/** check isGameover */
	public void checkGameOverAction() {
		if (isGameOver()==true) {
			state = GAME_OVER; // change status
		}
	}

	
	public boolean isGameOver() {
		
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) { // check if the hero hit the obj
				life.subtractLife(); // reduce one life
				hero.setDoubleFire(0); // 
				index = i; // record airplane
			}
			if (index != -1) {
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t; // change with the last flying object

				flyings = Arrays.copyOf(flyings, flyings.length - 1); // delete flying object
			}
		}
		
		return life.getLife() <= 0;
	}

	/** isHit */
	public void bang(Bullet bullet) {
		int index = -1; // hit airplane index
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) { // isHit or not
				index = i; // record hit airplane
				break;
			}
		}
		if (index != -1) { // hit airplane index
			FlyingObject one = flyings[index]; // record hit airplane index

			FlyingObject temp = flyings[index]; // swap with last flying objects
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;

			flyings = Arrays.copyOf(flyings, flyings.length - 1); // delete the last flying objects

			
			if (one instanceof Enemy) { // 
				Enemy e = (Enemy) one; // 
				score += e.getScore(); // add score
			} else { 
				Award a = (Award) one;
				int type = a.getType(); 
				switch (type) {
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire();
					break;
				case Award.LIFE:
					life.addLife(); // add extra life
					break;
				case Award.EXTRA:
					score += 20; // add extra score
					break;
				}
			}
		}
	}

	/**
	 * randomly create flying objects
	 * 
	 * @return  flying objects
	 */
	public static FlyingObject nextOne(String name) {
		Random random = new Random();
		int type = random.nextInt(20); // [0,20)
		FlyingobjectFactory flyingobjectFactory = new FlyingobjectFactory(name);
		FlyingObject obj = flyingobjectFactory.create(type);
		return obj;
	}


	@Override
	public void update(Observable o, Object arg) {
		if(this.time>5) {
			this.time -= 5;
		}
		
		
	}


	

}
