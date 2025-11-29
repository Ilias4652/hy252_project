package MODEL;
/**
 * this class functions as the players position on the board and interacts with the tiles of the board
 * @author paleokastrogreek
 *
 */
 public class Position {
public int x;


public void setPosition(int add,Character b) {
			if(this.x==30) {
				this.x = -1 + add;
				++b.Month;
	}
	else if((this.x+add)<=29) {
		this.x = this.x+add;
	}else if(this.x +add>=30){
		this.x =30;
		++b.Month;
	}
}


}
