package MODEL;
/**
 * this class is the extension of player and is the Main class being used to controll the players of the PayDay game
 * @author paleokastrogreek
 *
 */
public class Character extends Player {
/**
 * this is the constructor of the class which intializes the name of the player
 * @param x name of player
 */
	public Character(String x) {
		super(x);
		
	}
	/**
	 * this method moves the player's position on the board
	 * @param x the number of tiles the player must move
	 */
	void move(int x) {
		this.cords.setPosition(x, this);
	}
	/**
	 * this method checks if the player has finished playing the game
	 * @param v Board 
	 * @return returns if the statement is true or false
	 */
	public boolean hasFinished(Board v) {
		if(this.Month>v.time) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * this method buys something and removes the amount of money needed for the transaction from the player.If the player doesnt have enough money the player automatically gets enough loaned cash 
	 * @param x the cost of the good that the player wants to buy 
	 */
	public void buy(int x) {
		if(super.money>=x) {
			super.money= super.money-x;
		}else {
			adjust(x);
			super.money= super.money-x;
		}
	}
	/**
	 * this method automatically lends the player enough cash to buy the desired item 
	 * @param x the cost of the item 
	 */
	void adjust(int x) {
		int temp =1000;
		while(x+super.money>temp) {
			temp = temp +1000;
		}
		super.money = super.money +temp;
		super.loanedcash = super.loanedcash+temp;
	}
	
	/**
	 * this method rolls the dice of the player
	 * @return returns what number the dice rolled on
	 */
	int rolldice() {
		int x = super.d1.roll();
		return x;
	}
	
	/**
	 * this method adds money to the players amount 
	 * @param x the amount of money 
	 */
	public void changeMoney(int x) {
		super.money= super.money +x;
	}
	/**
	 * this method adds money to the players loaned  amount 
	 * @param x the amount of money 
	 */
public	void changeLoaned(int x) {
		super.loanedcash= super.loanedcash +x;
		super.money = super.money + x;
	}
	/**
	 * this method adds money to the players bills
	 * @param x the amount of money 
	 */
	void changeBills(int x) {
		super.bills= super.bills +x;
	}
	/**
	 * this method returns the player's total money 
	 * @return player's money
	 */
	public int getMoney() {
		return super.money;
	}
	/**
	 * this method returns the player's total loaned money 
	 * @return player's loaned money
	 */
	public int getLoaned(){
		return super.loanedcash;
	}
	/**
	 * this method returns the player's total bills
	 * @return player's bills
	 */
	public int getBills() {
		return super.bills;
	}
	/**
	 * this method gives a player a certain amount of cash
	 * @param x the amount of cash
	 * @param b the player getting the cash
	 */
	public void GiveMoney(int x , Player b) {
		if(super.money>x) {
			super.money = super.money - x;
			b.money = b.money + x;
		}else {
			adjust(x);
			b.money = b.money +x;
			super.money = super.money -x;
		}
	}
/**
 * this method pays the players bills 
 */
public	void Paybills() {
		if(this.money>this.bills){
		this.money = this.money - this.bills;
		this.bills=0;
		}else {
			adjust(this.bills);
			
			if(this.money-this.bills>0) {
				this.money = this.money-this.bills;
				this.bills=0;
			}
		}
			
		}

}
