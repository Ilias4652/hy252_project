package MODEL;

import java.util.Random;
/**
 * this class represents a dice and its abilities
 * @author Ilias Kapsis
 *
 */
public class Dice {
private int numb=0;
/**
 * this method rolls the dice and returns a value between 1 and 6
 * @return the value of the dice
 */
public int roll() {
	Random rand = new Random();
	int low = 1;
	int high =6;
	numb = rand.nextInt(high-low) + low;
	return numb;
}
public String setimage() {
	if(this.numb==1) {
		return "dice-1.jpg";
	}else if(this.numb==2) 
	{
		return "dice-2.jpg";
	}else if(this.numb==3) {
		return "dice-3.jpg";
	}else if(this.numb==4) {
		return "dice-4.jpg";
	}else if(this.numb==5) {
		return "dice-5.jpg";
	}else {
		return "dice-6.jpg";
	}
}
//new
public int getDice() {return this.numb;}
}
