package CONTROLLER;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
/**
 * THIS CLASS IS THE CONTROLLER AND THE BRAIN OF THE GAME AND IT HANDLES EVERY POSSIBILITY 
 * @Aauthor Ilias Kapsis
 */
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.text.View;

import MODEL.AgreementCards;
import MODEL.Board;
import MODEL.BuyerTile;
import MODEL.Cards;
import MODEL.CasinoTile;
import VIEW.view;
import MODEL.Character;
import MODEL.DealTile;
import MODEL.LotteryTile;
import MODEL.MailCards;
import MODEL.MailTile;
import MODEL.MailTile2;
import MODEL.PayDayTile;
import MODEL.RadioTile;
import MODEL.SweepStakes;
import MODEL.YardSaleTile;
public class controller  {
static Random rand = new Random();
 static int x =0;
 static int fr1=0;
 static int fr2=0;
 static view MyView;
 static int turn;
 static int decision=-1;
 static int AmmountMail=0;
 public static  boolean Yesfroll= false; 
 static boolean p1hasplayed =false;
 static boolean p2hasplayed =false;
public static Character player1 = new Character("ILIAS");
public static Character player2 = new Character("Nikos");
static String in; 
public static Board b1;
static boolean p1=false;
static boolean p2=false;
public static boolean mgo;
public static boolean dgo;
 static boolean Sunday=false;
 static boolean Thursday=false;
	private static void controller() {
		 b1 =new Board(selectTime());
		 x = rand.nextInt(10-5) + 5;
		 MyView = new view(b1);
		 turn =calculateTurn();
		 MyView.info.setText("Game info \n Months left :\n Loan:\nTurn :"+turn+" \n ==>");
			in ="Game info \n Months left :\n Loan:\nTurn :"+turn+" \n ==>";
			MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
			MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
			
	}

public static void main(String[] args) {
	controller();
	
}
/**
 * this method selects the length of the game
 * @return returns the length of the game
 */
static int selectTime() {
	Scanner scan = new Scanner(System.in);
	System.out.println("Please enter a number between 1 and 3, Which will be  the months of the game (Time Period) : ");
	int x = scan.nextInt();
	if(x>=4) {selectTime();}
	return x;
}
/*
 * this calculates the players score
 */
static int calculate(Character p) {
	int x = p.getMoney() -p.getLoaned() -p.getBills();
	return x;
}
/**
 * this checks if the game has finished
 * @param a Player a
 * @param b Player b
 *  @param c the board of the game
 * @return returns if the game has finished
 */
static boolean hasGameFineshed(Character a, Character b,Board c) {
	if(a.hasFinished(c) && b.hasFinished(c)) {
		return true;
		}else {return false;}
}
/**
 * this method randomly decides the turn of each player uniquely every game.
 * @return returns whose turn is to play
 */
static int calculateTurn() {
		++x;
		if(x % 2 ==0) {
			return 1;
		}else {return 2;}
	}
/**
 * this method updates the text of the game
 */
static void updateText() {
	if(turn==1) {
		in ="Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1) + "==> ";
	}else {
		in ="Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> ";
	}
	MyView.info.setText(in);
}

/**
 * this method updates the text of the geme
 * @param s a string which will describe the player's current condition
 */
static void updateText(String s) {
	
	MyView.info.setText(s);
}

/**
 * this method finds the tile that the player has landed on and does the appropriate action
 * @param c the player
 * @param x the player's position on the board
 */
static void play(Character c, int x) {
	if(c.hasFinished(b1)  ) {
	if(turn==1) {
		turn =2;
		p1hasplayed=true;
	}else {
		turn=1;
		p2hasplayed=true;
	}
	}else if(Sunday==true) {
		Yesfroll=true;
		updateText("Game info \n Months left :"+b1.time+"\n Turn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Roll the dice again");
	}else if(Thursday==true) {
		Yesfroll=true;
		updateText("Game info \n Months left :"+b1.time+"\n Turn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Roll the dice again");
	}else if(b1.MyBoard[x] instanceof DealTile) {
		dgo =true;
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Pick up a Deal Card");
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
	}else if(b1.MyBoard[x] instanceof MailTile || b1.MyBoard[x] instanceof MailTile2) {
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		mgo =true;
		if(b1.MyBoard[x] instanceof MailTile) {
			
			AmmountMail=1;
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Pick up a Mail Card");
		}else {
			AmmountMail=2;
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Pick up 2 Mail Card");
		}
	}else if(b1.MyBoard[x] instanceof SweepStakes) {
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Roll the dice");
		if(turn==1) {
			p1hasplayed=false;
			Yesfroll=true;
		}else {
			p2hasplayed=false;
			Yesfroll=true;
		}
	}else if(b1.MyBoard[x] instanceof LotteryTile) {
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Select a number");
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		lottery();
	}else if(b1.MyBoard[x] instanceof RadioTile) {
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Roll the dice");
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
	Radio();
		
	}else if(b1.MyBoard[x] instanceof BuyerTile) {
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==>if you have one, sell a card");
		Buyer(c);
	}else if(b1.MyBoard[x] instanceof CasinoTile) {
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		casino(c);
	}else if(b1.MyBoard[x] instanceof YardSaleTile) {
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1)+ "==> Roll the dice");
		if(turn==1) {
			p1hasplayed=false;
			Yesfroll=true;
		}else {
			p2hasplayed=false;
			Yesfroll=true;
		}
	}else if(b1.MyBoard[x] instanceof PayDayTile) {
		PayD(c );
	}
}
/**
 * this method rolls the player 1's dice and moves him
 */
public static  void roll1() {
	if(turn ==1 && p1hasplayed == false) {
		p1hasplayed=true;
		player1.cords.setPosition(player1.d1.roll(), player1); 
		if(player1.d1.getDice()==6) {
			player1.changeMoney(b1.jackpotammount);
			b1.jackpotammount=0;
        	MyView.label3.setText("<html>JackPot Money:"+b1.jackpotammount);
		}
		String str = "IMAGES/images/" + player1.d1.setimage();
		ImageIcon icon = new ImageIcon(str);
		MyView.dice1.setIcon(icon);
		updateText();
if(hasGameFineshed(player1,player2,b1)) {
			endGame();
		}
		
		play(player1, player1.cords.x);
		if(player1.cords.x==6 || player1.cords.x==13 || player1.cords.x==20 || player1.cords.x==27) {
			sun();
		}
		if(player1.cords.x==3 || player1.cords.x==10 || player1.cords.x==17 || player1.cords.x==24) {
			thur(); 
		}
	}
}
/**
 * this method is used when a player needs to roll his dice again and doesnt want to move from his position on the board
 */
public static  void fakeroll() {
	if(turn==1 && p1hasplayed==false ) {
		player1.d1.roll();
		String str = "IMAGES/images/" + player1.d1.setimage();
		ImageIcon icon = new ImageIcon(str);
		MyView.dice1.setIcon(icon);
		Yesfroll= false; 
	if(Sunday==true) {
		Sunday=false;
		p1hasplayed=true;
		if(decision>-1) {
		if(decision==0 && (player1.d1.getDice() ==1 || player1.d1.getDice() ==2)) {
			player1.changeMoney(1000);
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "\n==> You won 1000$ ");
		}else if(decision==1 && (player1.d1.getDice() ==3 || player1.d1.getDice() ==4)) {
			player1.changeMoney(1000);
		}else if(decision==2 && (player1.d1.getDice() ==5 || player1.d1.getDice() ==6)){
			player1.changeMoney(1000);
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "\n==> You won 1000$ ");
		}else {
			player1.changeMoney(-500);
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "\n==> You lost 500$ ");
		}
		}else{
			
		}
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
	}else if (Thursday==true) {
		Thursday=false;
		p1hasplayed=true;
		if(decision==1) {
			if(decision==1 && (player1.d1.getDice() ==1 || player1.d1.getDice() ==2)){
				player1.changeMoney(-300);
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "\n==> The price went down\n You lost 300$ ");

			}else if(decision==1 && (player1.d1.getDice() ==3 || player1.d1.getDice() ==4)) {
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "\n==> The pice stayed the same ");
			}else if(decision==1 && (player1.d1.getDice() ==5 || player1.d1.getDice() ==6)) {
				player1.changeMoney(600);
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "\n==>The price went up\nYou won 600$ ");

			}
			}else {
				}
		MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
		MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		}else if(b1.MyBoard[player1.cords.x] instanceof SweepStakes) {
		p1hasplayed=true;
			Sweep();
		
		}else if(b1.MyBoard[player1.cords.x] instanceof YardSaleTile) {
			p1hasplayed=true;
			yard();
		}
	}else if(turn ==2 && p2hasplayed==false){
		player2.d1.roll();
		String str = "IMAGES/images/" + player2.d1.setimage();
		ImageIcon icon = new ImageIcon(str);
		MyView.dice2.setIcon(icon);
		Yesfroll= false; 
		if(Sunday==true) {
			Sunday=false;
			p2hasplayed=true;
			if(decision>-1) {
			if(decision==0 && (player2.d1.getDice() ==1 || player2.d1.getDice() ==2)) {
				player2.changeMoney(1000);
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==> You won 1000$ ");
			}else if(decision==1 && (player2.d1.getDice() ==3 || player2.d1.getDice() ==4)) {
				player2.changeMoney(1000);
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==> You won 1000$ ");
			}else if(decision==2 && (player2.d1.getDice() ==5 || player2.d1.getDice() ==6)){
				player2.changeMoney(1000);
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==> You won 1000$ ");
			}else {
				player2.changeMoney(-500);
				updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==> You lost the 500$ ");
			}
			}else{
				
			}
			MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
			MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
		}else if (Thursday==true) {
			Thursday=false;
			p2hasplayed=true;
			if(decision==1) {
				if(decision==1 && (player2.d1.getDice() ==1 || player2.d1.getDice() ==2)){
					player2.changeMoney(-300);
					updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==> The price went down\n You lost 300$ ");
				}else if(decision==1 && (player2.d1.getDice() ==3 || player2.d1.getDice() ==4)) {
					updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==> The pice stayed the same ");
				}else if(decision==1 && (player2.d1.getDice() ==5 || player2.d1.getDice() ==6)) {
					player2.changeMoney(600);
					updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "\n==>The price went up\nYou won 600$ ");
				}
				}else {
					}
			MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
			MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
			}else if(b1.MyBoard[player2.cords.x] instanceof SweepStakes) {
			p2hasplayed=true;
			Sweep();
		
		}else if(b1.MyBoard[player2.cords.x] instanceof YardSaleTile) {
			p2hasplayed=true;
			yard();
		}
	}
}

/**
 * this method moves the second player on the board
 */
public static  void roll2() {
	if(turn ==2 && p2hasplayed == false) {
		p2hasplayed=true;
		player2.cords.setPosition(player2.d1.roll(), player2); 
		String str = "IMAGES/images/" + player2.d1.setimage();
		if(player2.d1.getDice()==6) {
			player2.changeMoney(b1.jackpotammount);
			b1.jackpotammount=0;
        	MyView.label3.setText("<html>JackPot Money:"+b1.jackpotammount);
		}
		ImageIcon icon = new ImageIcon(str);
		MyView.dice2.setIcon(icon);
		updateText();
		if(hasGameFineshed(player1,player2,b1)) {
			endGame();
		}
		play(player2, player2.cords.x);
		if(player2.cords.x==6 || player2.cords.x==13 || player2.cords.x==20 || player2.cords.x==27) {
			sun();
		}
		if(player2.cords.x==3 || player2.cords.x==10 || player2.cords.x==17 || player2.cords.x==24) {
			thur();
		}
		
	}
}
/**
 * this method allows the player to loan money 
 */
public static  void loan1() {
	int x=0;
	if(turn ==1) {
	JPanel panel = new JPanel();
    panel.add(new JLabel("Please make a selection:"));
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    model.addElement("1000");
    model.addElement("2000");
    model.addElement("3000");
    model.addElement("4000");
    model.addElement("5000");
    model.addElement("6000");
    model.addElement("7000");
    model.addElement("8000");
    model.addElement("9000");
    model.addElement("10000");
    JComboBox comboBox = new JComboBox(model);
    panel.add(comboBox);

    int result = JOptionPane.showConfirmDialog(null, panel, "Get a Loan from the Bank", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    switch (result) {
        case JOptionPane.OK_OPTION:
        	Object da = comboBox.getSelectedItem();
        	 x= Integer.valueOf((String)comboBox.getSelectedItem());
           player1.changeLoaned(x);
           in="Game info \n Months left :\n Loan:"+player1.loanedcash+"\nTurn :"+turn+" \n ==> Got " + x +" euros in cash";
            break;
    }
    	MyView.label1.setText("<html>Player 1<br>Money: 0<br>Loan:"+x+"<br>bills: 0");
    	updateText("Game info \n Months left :"+b1.time+"\n Turn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Got a Loan of \n"+x+" dollars");
}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}
/**
 * this method allows the player to loan money 
 */
public static  void loan2() {
	int x=0;
	if(turn ==2) {
	JPanel panel = new JPanel();
    panel.add(new JLabel("Please make a selection:"));
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    model.addElement("1000");
    model.addElement("2000");
    model.addElement("3000");
    model.addElement("4000");
    model.addElement("5000");
    model.addElement("6000");
    model.addElement("7000");
    model.addElement("8000");
    model.addElement("9000");
    model.addElement("10000");
    JComboBox comboBox = new JComboBox(model);
    panel.add(comboBox);

    int result = JOptionPane.showConfirmDialog(null, panel, "Get a Loan from the Bank", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    switch (result) {
        case JOptionPane.OK_OPTION:
        	Object da = comboBox.getSelectedItem();
        	 x= Integer.valueOf((String)comboBox.getSelectedItem());
           player2.changeLoaned(x);
           in="Game info \n Months left :\n Loan:"+player2.loanedcash+"\nTurn :"+turn+" \n ==> Got " + x +" euros in cash";
            break;
    }
    	
    	updateText("Game info \n Months left :"+b1.time+"\n Turn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Got a Loan of \n"+x+" dollars");
}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}

/**
 * this method stop player 1's turn
 */
public static  void end1() {
	if(hasGameFineshed(player1, player2, b1)) {
		endGame();
	}
	if(!player2.hasFinished(b1)) {
	if(turn ==1 && p1hasplayed == true && dgo == false && mgo==false) {
	turn =calculateTurn();
updateText();
	}
	if(p1hasplayed == true && p2hasplayed == true) {
		p1hasplayed= false;
		p2hasplayed=false;
	}
	}else {
		p1hasplayed=false;
		turn=1;
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}

/**
 * this method ends player 2's turn
 */
public static  void end2() {
	if(hasGameFineshed(player1, player2, b1)) {
		endGame();
	}
	if(!player1.hasFinished(b1)) {
	if(turn==2 && p2hasplayed == true && dgo == false && mgo == false) {
	turn =calculateTurn();
	updateText();
	}
	if(p1hasplayed == true && p2hasplayed == true) {
		p1hasplayed= false;
		p2hasplayed=false;
	}
	}else {
		p2hasplayed=false;
		turn=2;
	}
	updateText();
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}

/**
 * thismethod opens a window and shows the player the deal cards he has purchused
 */
public static  void inventory1() {
	if(p1==true) {
	if(turn==1  && !player1.stack.isEmpty()) {
	JPanel panel = new JPanel();
    panel.add(new JLabel("Please make a selection:"));
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    for(int i =0; i<player1.stack.size();++i) {
    model.addElement(player1.stack.get(i).getmessage() + player1.stack.get(i).sellvalue);
    }
    JComboBox comboBox = new JComboBox(model);
    panel.add(comboBox);
    int result = JOptionPane.showConfirmDialog(null, panel, "Select a card", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    AgreementCards s=null;
    switch (result) {
        case JOptionPane.OK_OPTION:
        	 s= (AgreementCards) comboBox.getSelectedItem();
            break;
    }
    if(s!=null) {
    JFrame show = new JFrame();
    show.setSize(400, 400);
    JLabel title = new JLabel(s.getmessage());
    JButton sell = new JButton("Sell " +s.getsellValue());
    String str = "IMAGES/images/" + s.getImage();
    JLabel imgLabel = new JLabel(new ImageIcon(str));
    show.add(title);
    show.add(imgLabel);
    show.add(sell);
    show.setVisible(true);
	updateText("Game info \n Months left :"+b1.time+"\n Turn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Sold a Card for  \n"+s.getsellValue()+" dollars");
    final AgreementCards s1=s;
    sell.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()== sell) {
            	sellcard(s1,  player1);
            	show.dispose();
            }
        }
    });
	}
	}
	p1=false;
	p1hasplayed=true;
	}
}
/**
 * this method opens a window and show the cards that the player has purchased
 */
public static  void inventory2() {
	if(p2==true) {
	if(turn==2  && !player2.stack.isEmpty()) {
	JPanel panel = new JPanel();
    panel.add(new JLabel("Please make a selection:"));
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    for(int i =0; i<player2.stack.size();++i) {
    model.addElement(player2.stack.get(i));
    }
    JComboBox comboBox = new JComboBox(model);
    panel.add(comboBox);
    int result = JOptionPane.showConfirmDialog(null, panel, "Select a card", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    AgreementCards s=null;
    switch (result) {
        case JOptionPane.OK_OPTION:
        	 s= (AgreementCards) comboBox.getSelectedItem();
            break;
    }
    if(s!=null) {
    JFrame show = new JFrame();
    show.setSize(400, 400);
    show.setLayout(new FlowLayout());
    JLabel title = new JLabel(s.getmessage());
    JButton sell = new JButton("Sell " +s.getsellValue());
    String str = "IMAGES/images/" + s.getImage();
    JLabel imgLabel = new JLabel(new ImageIcon(str));
    show.add(title);
    show.add(imgLabel);
    show.add(sell);
    show.setVisible(true);
    updateText("Game info \n Months left :"+b1.time+"\n Turn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Sold a Card for  \n"+s.getsellValue()+" dollars");
    final AgreementCards s1=s;
    sell.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent e)
        {
        	
            if(e.getSource()== sell) {
            	
            	sellcard(s1,  player2);
            	show.dispose();
            }
        }
    });
    }
	}
	p2=false;
	p2hasplayed=true;
	}
}
public static  void sellcard(AgreementCards s , Character b) {
	s.sell(b);
}

/**
 * method that show a deal card
 */
public static  void dealcard() {
	if(turn ==1 && dgo == true ) {
		AgreementCards lol = b1.AT[b1.currentcard];
		if(b1.currentcard==19) {
			b1.currentcard=0;
		}else {
			++b1.currentcard;
		}
		JFrame pp = new JFrame(lol.getmessage());
		JPanel ww = new JPanel(new FlowLayout());
		JLabel ll = new JLabel();
		ll.setText("Price "+lol.getValue());
		ww.add(ll);
		pp.setSize(900, 600);
		ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setLayout(new FlowLayout());
		  JButton ignore = new JButton("Ignore" );
		  ignore.setSize(100, 50);
		  JButton buy= new JButton("Buy" );
		  ignore.setSize(100, 50);
		  ww.add(ignore);
		  ww.add(buy);
		JLabel po = new JLabel();
		po.setText("Sell Value "+lol.getsellValue());
		pp.add(po);
		  pp.add(ww);
		  pp.setVisible(true);
		  ignore.addActionListener(new ActionListener()
		    
		    {
		        public void actionPerformed(ActionEvent e)
		        {
		        	
		           pp.dispose();
		           
		        }
		    });
		  buy.addActionListener(new ActionListener()
		    
		    {
		        public void actionPerformed(ActionEvent e)
		        {
		        	pp.dispose();
		        	lol.buy(player1);
		        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Bought a Card for  \n"+lol.getValue()+" dollars");
		        }
		    });
		  dgo =false;
	}else if(turn ==2 && dgo == true) {
		Random rp = new Random();
		int temp = rp.nextInt(20);
		AgreementCards lol = b1.AT[temp];
		JFrame pp = new JFrame(lol.getmessage());
		JPanel ww = new JPanel(new FlowLayout());
		JLabel ll = new JLabel();
		ll.setText("Price "+lol.getValue());
		ww.add(ll);
		pp.setSize(900, 600);
		ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setLayout(new FlowLayout());
		  JButton ignore = new JButton("Ignore" );
		  ignore.setSize(100, 50);
		  JButton buy= new JButton("Buy" );
		  ignore.setSize(100, 50);
		  ww.add(ignore);
		  ww.add(buy);
		JLabel po = new JLabel();
		po.setText("Sell Value "+lol.getsellValue());
		pp.add(po);
		  pp.add(ww);
		  pp.setVisible(true);
		  ignore.addActionListener(new ActionListener()
		    
		    {
		        public void actionPerformed(ActionEvent e)
		        {
		        	
		           pp.dispose();
		        }
		    });
		  buy.addActionListener(new ActionListener()
		    
		    {
		        public void actionPerformed(ActionEvent e)
		        {
		        	pp.dispose();
		        	lol.buy(player2);
		        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Bought a Card for  \n"+lol.getValue()+" dollars");
		        }
		    });
		  dgo =false;
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}
/**
 * method that shows mail cards
 */
public static  void mailcard() {
	while(AmmountMail>=1) {
	if(turn ==1 && mgo == true ) {
		MailCards  lol = (MailCards) b1.MT[b1.currentmail];
		if(b1.currentmail==47) {
			b1.currentmail=0;
		}else {
			++b1.currentmail;
		}
		JFrame pp = new JFrame();
		pp.setSize(800, 800);
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setLayout(new GridLayout());
		  if(lol.getType() == "Advertisement") {
			  pp.setTitle("Advertisement");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JLabel ll = new JLabel();
			  ll.setText("Price "+lol.getValue());
			  ww.add(ll);
			  JLabel q = new JLabel();
			  q.setText(lol.getmessage());
			  ww.add(q);
			  JButton buy= new JButton("Sell" +lol.getValue());
			  buy.setSize(100, 50);
			  ww.add(buy);
			  pp.add(ww);
			  pp.setVisible(true);
			  buy.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player1.changeMoney(lol.getValue());
			           pp.dispose();
			           updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Sold an AdCard for  \n"+lol.getValue()+" dollars");
			        }
			    });
		  }else if(lol.getType() == "Bill") {
			  pp.setTitle("Get charged with a Bill");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton charge= new JButton("Charge" + lol.getValue());
			  charge.setSize(100, 50);
			  ww.add(charge);
			  pp.add(ww);
			  pp.setVisible(true);
			  charge.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	lol.billCard(player1);
			           pp.dispose();
			           updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Added a Bill of   \n"+lol.getValue()+" dollars");
			        }
			    });
		  }else if(lol.getType() == "Charity") {
			  pp.setTitle("Help Charity");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton charity= new JButton("Give Charity "+lol.getValue() +"$");
			  charity.setSize(100, 50);
			  ww.add(charity);
			  pp.add(ww);
			  pp.setVisible(true);
			  charity.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player1.buy(lol.getValue());
			        	b1.jackpotammount = b1.jackpotammount + lol.getValue();
			        	MyView.label3.setText("<html>JackPot Money:"+b1.jackpotammount);
			        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Gave   \n"+lol.getValue()+" dollars to JackPot pile");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "PayTheNeighbor") {
			  pp.setTitle("Pay the Neighbor");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images//"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton pay= new JButton("Pay the neighbor "+ lol.getValue() );
			  pay.setSize(100, 50);
			  ww.add(pay);
			  pp.add(ww);
			  pp.setVisible(true);
			  pay.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player1.GiveMoney(lol.getValue(), player2);
			        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==>  Player 1 Gave   \n"+lol.getValue()+" dollars to Player 2");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "MadMoney") {
			  pp.setTitle("Get payed by the Neighbor");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton get= new JButton("Pay the neighbor "+lol.getValue() +"$");
			  get.setSize(100, 50);
			  ww.add(get);
			  pp.add(ww);
			  pp.setVisible(true);
			  get.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player2.GiveMoney(lol.getValue(), player1);
			        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> Player 1 got   \n"+lol.getValue()+" dollars From Player 2");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "MoveToDealBuyer") {
			  pp.setTitle("Move to closest deal Buyer");
			  JPanel ww=new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton move= new JButton("Move " );
			  move.setSize(100, 50);
			  ww.add(move);
			  pp.add(ww);
			  pp.setVisible(true);
			  move.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	 boolean now = true;
			        	 int temp=player1.cords.x;
						  int l=player1.cords.x;
						  while(now==true) {
							  if(b1.MyBoard[l]instanceof DealTile) {
								  now =false;
								  player1.cords.x=l;
									updateText();
								  play(player1 , player1.cords.x);
							  }else {
								  if(l==30) {
									  now =false;
									  player1.cords.x=temp;
								  }else {++l;}
								  }
						  }
			           pp.dispose();
			           updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> Player 1  Moved to the closest Buyer Tile  ");
			        }
			    });
			 
		  }
		  --AmmountMail;
		  if(AmmountMail>=1) {mailcard();}
		  mgo =false;
		
	}else if(turn ==2 && mgo == true){
		MailCards  lol = (MailCards) b1.MT[b1.currentmail];
		if(b1.currentmail==47) {
			b1.currentmail=0;
		}else {
			++b1.currentmail;
		}
		JFrame pp = new JFrame();
		pp.setSize(800, 800);
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pp.setLayout(new GridLayout());
		  if(lol.getType() == "Advertisement") {
			  pp.setTitle("Advertisement");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JLabel ll = new JLabel();
			  ll.setText("Price "+lol.getValue());
			  ww.add(ll);
			  JLabel q = new JLabel();
			  q.setText(lol.getmessage());
			  ww.add(q);
			  JButton buy= new JButton("Sell" +lol.getValue());
			  buy.setSize(100, 50);
			  ww.add(buy);
			  pp.add(ww);
			  pp.setVisible(true);
			  buy.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player2.changeMoney(lol.getValue());
			        	 updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Sold an AdCard for  \n"+lol.getValue()+" dollars");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "Bill") {
			  pp.setTitle("Get charged with a Bill");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton charge= new JButton("Charge" + lol.getValue());
			  charge.setSize(100, 50);
			  ww.add(charge);
			  pp.add(ww);
			  pp.setVisible(true);
			  charge.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	lol.billCard(player2);
			        	 updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Added a Bill of   \n"+lol.getValue()+" dollars");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "Charity") {
			  pp.setTitle("Help Charity");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton charity= new JButton("Give Charity "+lol.getValue() +"$");
			  charity.setSize(100, 50);
			  ww.add(charity);
			  pp.add(ww);
			  pp.setVisible(true);
			  charity.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player2.buy(lol.getValue());
			        	b1.jackpotammount = b1.jackpotammount + lol.getValue();
			        	MyView.label3.setText("<html>JackPot Money:"+b1.jackpotammount);
			        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Gave   \n"+lol.getValue()+" dollars to JackPot pile");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "PayTheNeighbor") {
			  pp.setTitle("Pay the Neighbor");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images//"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton pay= new JButton("Pay the neighbor "+ lol.getValue() );
			  pay.setSize(100, 50);
			  ww.add(pay);
			  pp.add(ww);
			  pp.setVisible(true);
			  pay.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player2.GiveMoney(lol.getValue(), player1);
			        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==>  Player 2 Gave   \n"+lol.getValue()+" dollars to Player 1");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "MadMoney") {
			  pp.setTitle("Get payed by the Neighbor");
			  JPanel ww = new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton get= new JButton("Get payed "+lol.getValue() +"$");
			  get.setSize(100, 50);
			  ww.add(get);
			  pp.add(ww);
			  pp.setVisible(true);
			  get.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	player1.GiveMoney(lol.getValue(), player2);
			        	updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> Player 2 got   \n"+lol.getValue()+" dollars From Player 1");
			           pp.dispose();
			        }
			    });
		  }else if(lol.getType() == "MoveToDealBuyer") {
			  pp.setTitle("Move to closest deal Buyer");
			  JPanel ww=new JPanel(new FlowLayout());
			  ww.add(new JLabel(new ImageIcon("cardicon/images/"+ lol.getImage())));
			  ww.add(new JLabel(lol.getmessage()));
			  JButton move= new JButton("Move " );
			  move.setSize(100, 50);
			  ww.add(move);
			  pp.add(ww);
			  pp.setVisible(true);
			  move.addActionListener(new ActionListener()
			    
			    {
			        public void actionPerformed(ActionEvent e)
			        {
			        	 boolean now = true;
			        	 int temp=player2.cords.x;
						  int l=player2.cords.x;
						  while(now==true) {
							  if(b1.MyBoard[l]instanceof DealTile) {
								  now =false;
								  player2.cords.x=l;
									updateText();
								  play(player2 , player2.cords.x);
							  }else {
								  if(l==30) {
									  now =false;
									  player2.cords.x=temp;
								  }else {++l;}
								  }
						  }
			           pp.dispose();
			           updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> Player 2  Moved to the closest Buyer Tile  ");
			        }
			    });
			 
		  }
		  --AmmountMail;
		  if(AmmountMail>=1) {mailcard();}
		  mgo = false; 
		  
	}
	
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}

/**
 * method that performs the actions needed if a player landed on a SweepStakes Tile 
 */
public static  void Sweep() {
	if(turn==1) {
		player1.changeMoney(player1.d1.getDice()*1000);
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> You won" +player1.d1.getDice()*1000+" dollars");
	}else {
		player2.changeMoney(player2.d1.getDice()*1000);
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> You won" +player2.d1.getDice()*1000+" dollars");
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}

/**
 * method that performs the actions needed if a player landed on a Lottery Tile 
 */
public static void lottery() {
	int x=0;
	int z=0;
	int y=0;
	for(int i=0; i<=1;++i) {
		JPanel panel = new JPanel();
	    panel.add(new JLabel("Player" +turn+" Select a number"));
	    DefaultComboBoxModel model = new DefaultComboBoxModel();
	    model.addElement("1");
	    model.addElement("2");
	    model.addElement("3");
	    model.addElement("4");
	    model.addElement("5");
	    model.addElement("6");
	    JComboBox comboBox = new JComboBox(model);
	    panel.add(comboBox);

	    int result = JOptionPane.showConfirmDialog(null, panel, "Select a number ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    switch (result) {
	        case JOptionPane.OK_OPTION:
	        	Object da = comboBox.getSelectedItem();
	        	 x= Integer.valueOf((String)comboBox.getSelectedItem());
	        	 if(turn==1) {
	        		z=x; 
	        	 }else {
	        		 y=x;
	        	 }
	           
	            break;
	    }
	    turn = calculateTurn();
	}
	Random r = new Random();
	if(z==y) {
		lottery();
	}
	int res = r.nextInt(2);
	if(turn==1) {
	if(res==0) {
		player1.changeMoney(1000);
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> You won 1000 dollars via random giveaway");
		
	}else {
		player2.changeMoney(1000);
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> Player2 won 1000 dollars via random giveaway");
	}
	}else {
		if(res==0) {
			player1.changeMoney(1000);
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> Player1 won 1000 dollars via random giveaway");
		}else {
			player2.changeMoney(1000);
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> You won 1000 dollars via random giveaway");
		}
	}
	
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}
/**
 * method that performs the actions needed if a player landed on a Radio Tile 
 */
public static void Radio( ){
	JFrame frame = new JFrame("Roll dice");
    JPanel panel = new JPanel();
    panel.setLayout(null);
    JButton ro1 = new JButton("Player 1 roll");
    ro1.setBounds(100, 400, 150, 80);
    panel.add(ro1);
    JButton ro2 = new JButton("Payer2 roll");
    ro2.setBounds(400, 400, 150, 80);
    panel.add(ro2);
    JButton di1 = new JButton();
    di1.setBounds(100, 200, 140, 140);
    panel.add(di1);
    JButton di2 = new JButton();
    di2.setBounds(400, 200, 140, 140);
    panel.add(di2);
    JButton o = new JButton("OK");
    o.setBounds(500, 500, 80, 20);
    panel.add(o);
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setContentPane(panel);
    frame.setSize(900, 600);
    frame.setVisible(true);
    
	
	boolean ok =true;
	
	ro1.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent e)
        {
        	 player1.d1.roll();
        	 ImageIcon icon = new ImageIcon("IMAGES/images/" + player1.d1.setimage());
        	 di1.setIcon(icon);
        	 fr1= player1.d1.getDice();
           
        }
    });
ro2.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent e)
        {
        	 player2.d1.roll();
        	 ImageIcon icon = new ImageIcon("IMAGES/images/" + player2.d1.setimage());
        	 di2.setIcon(icon);
        	 fr2= player2.d1.getDice();
           
        }
    });
	
	
	o.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent e)
        {
        	if(fr1>fr2) {
        		player1.changeMoney(1000);
        		fr1=0;
        		fr2=0;
        		frame.dispose();
        		if(turn == 1) {
        			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> You won 1000 dollars");
        		}else {
        			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1)+ "==> Player2 won 1000 dollars");
        		}
        	}else if(fr2>fr1) {
        		player2.changeMoney(1000);
        		fr1=0;
        		fr2=0;
        		frame.dispose();
        		if(turn == 1) {
        			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> Player1 1000 dollars");
        		}else {
        			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1)+ "==> You won 1000 dollars");
        		}
        	}else {
        		fr1=0;
        		fr2=0;
        		frame.dispose();
        		Radio();
        	}
        	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
        	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
           
        }
    });
	
	
	
}

/**
 * method that performs the actions needed if a player landed on a Buyer Tile 
 */
public static void Buyer(Character c) {
	if(c.stack.isEmpty()) {
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1) + "==> You have no cards bought");
	}else {
		if(turn==1) {
			p1=true;
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1) + "==> Please Press Inventory");
		}else {
			p2=true;
			updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1) + "==> Please Press Inventory");
		}
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}
/**
 * method that performs the actions needed if a player landed on a casino Tile 
 */
public static void casino(Character c) {
	if(c.d1.getDice()%2 ==0) {
		c.changeMoney(500);
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1) + "==> You won 500 bucks");
	}else {
		c.changeMoney(-500);
		b1.jackpotammount= b1.jackpotammount+500;
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(c.cords.x+1) + "==> You lost 500 bucks which were added to the JackPot ammmount");
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}
/**
 * method that performs the actions needed if a player landed on a yardsale Tile 
 */
public static void yard() {
	if(turn==1) {	
		player1.changeMoney(player1.d1.getDice()*-100);
		b1.AT[b1.currentcard].getCard(b1.currentcard,player1);
		if(b1.currentcard==20) {
			b1.currentcard=0;
		}else {
			++b1.currentcard;
		}
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player1.cords.x+1) + "==> You lost" +player1.d1.getDice()*-100 +"dollars but won a free card");
	}else {
		player2.changeMoney(player2.d1.getDice()*-100);
		b1.AT[b1.currentcard].getCard(b1.currentcard,player2);
		if(b1.currentcard==20) {
			b1.currentcard=0;
		}else {
			++b1.currentcard;
		}
		updateText("Game info \n Months left :"+b1.time+"\nTurn :"+turn +"\n Position : "+(player2.cords.x+1) + "==> You lost" +player2.d1.getDice()*-100 +"dollars but won a free card");
	}
	MyView.label1.setText("<html>Player 1<br>Money:"+player1.getMoney() +" <br>Loan:"+player1.getLoaned()+"<br>bills:"+player1.getBills());
	MyView.label2.setText("<html>Player 2<br>Money:"+player2.getMoney() +" <br>Loan:"+player2.getLoaned()+"<br>bills:"+player2.getBills());
}
/**
 * method that performs the actions needed if a player landed on a PayDay Tile 
 */
public static void PayD(Character C) {
	C.changeMoney(3500);
	C.Paybills();
	int temp;
	temp = (10* C.getLoaned())/100;
	C.buy(temp);
	JPanel panel = new JPanel();
	if(C.getLoaned()>0) {
    panel.add(new JLabel("Please make a selection:"));
    DefaultComboBoxModel model = new DefaultComboBoxModel();
    model.addElement("1000");
    model.addElement("2000");
    model.addElement("3000");
    model.addElement("4000");
    model.addElement("5000");
    model.addElement("6000");
    model.addElement("7000");
    model.addElement("8000");
    model.addElement("9000");
    model.addElement("10000");
    JComboBox comboBox = new JComboBox(model);
    panel.add(comboBox);

    int result = JOptionPane.showConfirmDialog(null, panel, "Ammount of Loan you Want to Pay", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    switch (result) {
        case JOptionPane.OK_OPTION:
        	Object da = comboBox.getSelectedItem();
        	 x= Integer.valueOf((String)comboBox.getSelectedItem());
           C.changeLoaned(-x);
           C.changeMoney(-x);
    }
	}
    	
}
/**
 * method that performs the actions needed if a player landed on a Sunday
 */
public static void sun() {
	Sunday=true;
	 JFrame frame = new JFrame("Sunday Football");
     JPanel panel = new JPanel();
     frame.getContentPane();
     JLabel my = new JLabel(new ImageIcon("IMAGES/images/Barcelona_Real.jpg"));
     JLabel ww = new JLabel("Bet on the result of the match");
     ww.setBounds(700, 100, 200, 60);
     my.setBounds(0, 0, 650, 400);
     panel.add(my);
     panel.add(ww);
     JButton win = new JButton("Win");
     win.setBounds(10, 400, 150, 80);
     panel.setLayout(null);
     panel.add(win);
     JButton Draw = new JButton("Draw");
     Draw.setBounds(160, 400, 150, 80);
     panel.add(Draw);
     JButton Awin = new JButton("Away win");
     Awin.setBounds(310, 400, 150, 80);
     panel.add(Awin);
     panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
     frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     frame.add(panel);
     frame.setSize(900, 600);
     frame.setVisible(true);
     win.addActionListener(new ActionListener()
	    
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	decision =0;
	        	frame.dispose();
	        }
	    });
     Draw.addActionListener(new ActionListener()
	    
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	decision =1;
	        	frame.dispose();
	        }
	    });
     Awin.addActionListener(new ActionListener()
	    
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	decision =0;
	        	frame.dispose();
	        	if(turn==1) {
	        		p1hasplayed=false;
	           	 play(player1,player1.cords.x);
	            }else {
	            	p2hasplayed=false;
	           	 play(player2,player2.cords.x);
	            }
	        }
	    });
     
}

/**
 * method that performs the actions needed if a player landed on a Thursday
 */
public static void thur() {
	Thursday=true;
	JFrame frame = new JFrame("Thursday Rise of Crypto");
    JPanel panel = new JPanel();
    frame.getContentPane();
    JLabel my = new JLabel(new ImageIcon("IMAGES/images/crypto.jpg"));
    JLabel ww = new JLabel("Invest in Crypto");
    ww.setBounds(700, 100, 200, 60);
    my.setBounds(10, 10, 650, 400);
    panel.add(my);
    panel.add(ww);
    JButton win = new JButton("Win");
    win.setBounds(10, 400, 150, 80);
    panel.setLayout(null);
    panel.add(win);
    
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(900, 600);
    frame.setVisible(true);
    win.addActionListener(new ActionListener()
	    
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	decision =1;
	        	frame.dispose();
	        	if(turn==1) {
	        		p1hasplayed=false;
	        	   	 play(player1,player1.cords.x);
	        	    }else {
	        	    	p2hasplayed=false;
	        	   	 play(player2,player2.cords.x);
	        	    }
	        }
	    });
 
   
}
/**
 * method that ends the programm and shows who won
 */
public static void endGame() {
	int total1=0;
	int total2=0;
	total1= calculate(player1);
	total2= calculate(player2);
	if(total1>total2) {
		System.out.println("Player 1 won,He is inevitable ");
	}else if(total1<total2) {
		System.out.println("Player 2 won, He is inevitable");
	}else {
		System.out.println("Draw");
	}
	System.exit(0);
	
}
}
