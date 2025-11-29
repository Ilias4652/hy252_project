package MODEL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import VIEW.view;
/**
 * this class creates a PayDay card Board
 * @author Ilias Kapis
 *
 */
public class Board {
public Tile[] MyBoard = new Tile[31];
public int time;
public AgreementCards[] AT = new AgreementCards[20];
public MailCards[] MT = new MailCards[48];
String[][] mailCards = new String[48][4];
String[][] dealCards = new String[20][8];
public int jackpotammount;
public int currentcard;
public int currentmail;
/**
 * this is the constructor of the class Board which calls the initialization of  the cards of the game and initializes the Length of the game
 * @param x
 */
 public Board(int x)  {
	 try {
	if(x<0 || x>3) {
		throw new Exception();
	}else {
		time = x;
	}
	 }
	 catch (Exception e) {}
	 initTiles();
}
 
 /**
  * this initializes the cards of the game
  */
void initTiles() {
	readFile("src/resources/dealCards_greeklish.csv", "Deal");
	readFile("src/resources/mailCards_greeklish.csv", "Mail");
	for(int i=0;i<48;++i) {
		MailCards g1 = new MailCards();
		g1.findType(mailCards[i][1]);
		g1.setImage(mailCards[i][5]);
		g1.setmessage(mailCards[i][2]);
		g1.setValue(Integer.parseInt(mailCards[i][4]));
		MT[i]= g1;
		}
	for(int j=0;j<20;++j) {
		AgreementCards a1= new AgreementCards();
		a1.setValue(Integer.parseInt(dealCards[j][3]));
		a1.setImage(dealCards[j][5]);
		a1.setmessage(dealCards[j][2]);
		a1.setsellValue(Integer.parseInt(dealCards[j][4]));
		AT[j]=a1;
	}
	randomiseCards();
}
/**
 * this method saves the Tiles of the game
 */
public void initBoard() {
	int MailCount=4;
	checkEmpty(MailCount,new MailTile());
	checkEmpty(MailCount,new MailTile2());
	int DealCount=5;
	checkEmpty(DealCount,new DealTile());
	int temp=2;
	checkEmpty(temp,new SweepStakes());
	temp=3;
	checkEmpty(temp,new LotteryTile());
	temp =2;
	checkEmpty(temp,new RadioTile());
	temp=6;
	checkEmpty(temp,new BuyerTile());
	temp =2;
	checkEmpty(temp,new CasinoTile());
	checkEmpty(temp,new YardSaleTile());
	MyBoard[30] = new PayDayTile();
	
}
/**
 * this method shuffles the cards of the game
 */
void randomiseCards() {
	Random rp = new Random();
	int x;
	for(int i=0;i<48;++i) {
		x=rp.nextInt(48);
		MailCards mm = MT[x];
		MT[x]= MT[i];
		MT[i]=mm;
	}
	for(int i=0;i<20;++i) {
		x=rp.nextInt(20);
		AgreementCards mm = AT[x];
		AT[x]= AT[i];
		AT[i]=mm;
	}
}


/**
 * this method randomly saves the cards of the game in unique positions
 * @param x number of Tile
 * @param c the type of the Tile
 */
void checkEmpty(int x, Tile c) {
	Random rand = new Random();
	int i=0;
	while(x>0) {
		i = rand.nextInt(31);
		if(MyBoard[i] == null && i!=30) {
			MyBoard[i] = c;
			--x;
		}
	}
}

/**
 * this method reads the csv files
 * @param path path of file
 * @param type type of file
 */
public void readFile(String path, String type) {
	BufferedReader br=null;
	String sCurrentLine;
	try {
		
		br = new BufferedReader(new FileReader(path));
	} catch (FileNotFoundException ex) {
		
	}
	int count = 0;
	int splitCount = 0;
	HashMap<Integer, String> domainsMap = new HashMap<>();
	try {
		br.readLine();
		while ((sCurrentLine = br.readLine()) != null) {
			if (type.equals("Mail")) {
				mailCards[count++] = sCurrentLine.split(",");
			} else {
				dealCards[count++] = sCurrentLine.split(",");
			}
		}
		br.close();
	} catch (IOException ex) {
		
	}
}

}
