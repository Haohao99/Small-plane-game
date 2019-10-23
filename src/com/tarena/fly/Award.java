package com.tarena.fly;
/**
 * ½±Àø
 */
public interface Award {
	int DOUBLE_FIRE = 0;  //one more bullet
	int LIFE = 1;   // one more  life
	int EXTRA = 2; //EXTRA score
	int getType();
	boolean outOfBounds();
}
