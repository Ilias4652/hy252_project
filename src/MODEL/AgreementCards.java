package MODEL;
/**
 * this class creates a Card whose type is Agreement
 * @author Ilias Kapsis
 *
 */
public class AgreementCards implements Cards{
public int value;
public int sellvalue;
String image;
String message;

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
 * this method returns the path of the image saved in this card
 * @return returns String
 */
public String getImage() {
    return image;
}
/**
 * saves the path of the card's image
 * @param image the path of the image 
 */
public void setImage(String image) {
    this.image = image;
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
/**
 * this method returns the sell value of the card 
 * @return the sell value
 */
public int getsellValue() {
    return sellvalue;
}
/**
 * this method saves the sell value of the card
 * @param value the sell value 
 */
public void setsellValue(int value) {
    this.sellvalue = value;
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
public void pay(Character r, Character b) {
	// TODO Auto-generated method stub
	
}

@Override
public void getpayed(Character r, Character b) {
	// TODO Auto-generated method stub
	
}

@Override
public void payBank(Character r) {
	// TODO Auto-generated method stub
	
}

@Override
public void billCard(Character r) {
	// TODO Auto-generated method stub
	
}
/**
 * this method buys this card and puts it inside the inventory of the player
 */
@Override
public void buy(Character r) {
	r.buy(value);
	r.stack.add(this);
	
}
/**
 * this method sells the card , gives the player the sell value of the card and removes it from his inventory
 */
@Override
public void sell(Character r) {
	r.changeMoney(sellvalue);
	r.stack.pop();
	
}

/**
 * the player gets the card
 */
public void getCard(int temp ,Character c) {
	c.stack.add(this);
}


}
