package MODEL;

import java.util.Stack;

import javax.swing.JFrame;
/**
 * this abstart class represent a player and has some basic functions
 * @author Ilias Kapsis
 *
 */
abstract class Player {
public int money;
public int loanedcash;
public int bills;
private String  Name;
public int Month;
public Dice d1 = new Dice();
public Position cords = new Position(); 
public Stack<AgreementCards> stack = new Stack<AgreementCards>();
Player(String x){
	this.Name = x;
	Set();
}
void Set() {
	this.bills=0;
	this.loanedcash=0;
	this.money=3500;
	this.Month=0;
	this.cords.x=-1;
}

	
}
