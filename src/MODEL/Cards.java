package MODEL;
/**
 * this interface gives the cards some function
 * @author Ilias Kapsis
 *
 */
public interface Cards {
	void get(Character r);
	void move(Character r);
	void pay(Character r, Character b);
	void getpayed(Character r, Character b);
	void payBank(Character r);
	void billCard(Character r);
	void buy(Character r);
	void sell(Character r);
	public void getCard(int temp ,Character c);
	
	
	
}
