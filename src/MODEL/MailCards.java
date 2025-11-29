package MODEL;
/**
 * this class is an extension of Cards and its abilities
 * @author Ilias Kapsis
 *
 */
 public class MailCards implements Cards {
int value;
String image;
String message;
String type;
/**
 * this methos finds the type of mailcard this is and saves its  value
 * @param s the type 
 */
public void findType(String s) {
	if(s.equals("Advertisement")) {
		type ="Advertisement";
	}else if(s.equals("Bill")) {
		type = "Bill";
	}else if(s.equals("Charity")) {
		type="Charity";
	}else if(s.equals("PayTheNeighbor")) {
		type="PayTheNeighbor";
	}else if(s.equals("MadMoney")) {
		type="MadMoney";
	}else if(s.equals("MoveToDealBuyer")) {
		type = "MoveToDealBuyer";
	}
}
/**
 * this method returns the path of the image saved in this card
 * @return returns String
 */
public String getImage() {
    return image;
}
/**
 * this method returns the type of the mail card
 * @return String
 */
public String getType() {
    return type;
}

/**
 * saves the path of the card's image
 * @param image the path of the image 
 */
public void setImage(String image) {
    this.image = image;
}

/**
 * this method returns the message of the card 
 * @return return the saved String of the card
 */
public String getmessage() {
    return message;
}

/**
 * this method saves a message to tge card 
 * @param i a string 
 */

public void setmessage(String i) {
    this.message = i;
}

/**
 * returns the cost of the card
 * @return the cost 
 */
public int getValue() {
    return value;
}


/**
 * this method sets the cost of the cards
 * @param value the value of the card
 */
public void setValue(int value) {
    this.value = value;
}
@Override
public void get(Character r) {
	// TODO Auto-generated method stub
	
}
@Override
public void move(Character r) {
	// TODO Auto-generated method stub
	
}
@Override

/**
 * this card basically makes the player pay the other player
 */
public void pay(Character r, Character b) {
	r.GiveMoney(value, b);
	
}
/**
 * this card basically makes the other player pay him
 */
@Override
public void getpayed(Character r, Character b) {
	b.GiveMoney(value, r);
	
}
/**
 * this method makes the player pay the bank a certain amount
 */
@Override
public void payBank(Character r) {
	r.buy(value);
	
}
/**
 * this card bills the player a certain amount
 */
@Override
public void billCard(Character r) {
	r.changeBills(value);
	
}
@Override
public void buy(Character r) {
	// TODO Auto-generated method stub
	
}
@Override
public void sell(Character r) {
	// TODO Auto-generated method stub
	
}
@Override
public void getCard(int temp, Character c) {
	// TODO Auto-generated method stub
	
}


}
