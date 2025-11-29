package VIEW;

import javax.swing.*;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CONTROLLER.controller;

import java.awt.BorderLayout;
import MODEL.Board;
import MODEL.BuyerTile;
import MODEL.CasinoTile;
import MODEL.DealTile;
import MODEL.LotteryTile;
import MODEL.MailTile;
import MODEL.MailTile2;
import MODEL.PayDayTile;
import MODEL.RadioTile;
import MODEL.SweepStakes;
import MODEL.YardSaleTile;
/**
 * this method's purpose is to print the game in the window
 * @author Ilias Kapsis
 *
 */
public class view implements ActionListener{
JFrame Mp = new JFrame("PAYDAY");
JPanel panel1 = new JPanel();
JPanel panel2 = new JPanel();
JPanel panel3 = new JPanel();
public JTextArea info = new JTextArea();
public JLabel label1 = new JLabel("<html>Player 1<br>Money: 0<br>Loan: 0<br>bills: 0");
public JLabel label2 = new JLabel("<html>Player 2<br>Money: 0<br>Loan: 0<br>bills: 0");
public JLabel label3 = new JLabel("<html>JackPot Money:");
public JButton roll1 = new JButton("Roll dice");
public JButton roll2 = new JButton("Roll dice");
public JButton card1 = new JButton("My Deal Cards");
public JButton card2 = new JButton("My Deal Cards");
public JButton loan1 = new JButton("Get a Loan");
public JButton loan2 = new JButton("Get a Loan");
public JButton turn1 = new JButton("End Turn");
public JButton turn2 = new JButton("End Turn");
public JButton dice1 = new JButton();
public JButton dice2 = new JButton();
public JButton mail = new JButton();
public JButton deal= new JButton();
public view(Board r) {
	Mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Mp.setLayout(null);
	Mp.setSize(800,1080);
	panel1.setBackground(Color.green);
	panel1.setBounds(0,0,600,1080);
	panel2.setBackground(Color.gray);
	panel2.setBounds(600,0,800,540);
	panel3.setBackground(Color.red);
	panel3.setBounds(600,540,800,1080);
	label1.setBounds(610, 10, 150, 100);
	label2.setBounds(610, 550, 150, 100);
	label3.setBounds(450, 900, 100, 150);
	info.setBounds(600,400,200,150);
	info.setText("Game info \n Months left :\n Loan:\nTurn : \n ==>");
	info.setEditable(false);
	Mp.add(info);
	Mp.add(label1);
	Mp.add(label2);
	Mp.add(label3);
	roll1.setSize(100, 30);
	roll1.setLocation(610,100);
	this.roll1.addActionListener( this);
	Mp.add(roll1);
	mail.setSize(100, 60);
	mail.setLocation(10, 955);
	this.mail.addActionListener(this); 
	ImageIcon ranicon = new ImageIcon("IMAGES/images/mailCard.png");
	mail.setIcon(ranicon);
	Mp.add(mail);
	deal.setSize(100, 60);
	deal.setLocation(120, 955);
	this.deal.addActionListener(this); 
	 ranicon = new ImageIcon("IMAGES/images/dealCard.png");
	deal.setIcon(ranicon);
	Mp.add(deal);
	card1.setSize(150, 30);
	card1.setLocation(610,140);
	this.card1.addActionListener(this);
	Mp.add(card1);
	loan1.setSize(150, 30);
	loan1.setLocation(610,180);
	this.loan1.addActionListener(this);
	Mp.add(loan1);
	turn1.setSize(150, 30);
	turn1.setLocation(610,220);
	this.turn1.addActionListener(this);
	Mp.add(turn1);
	dice1.setSize(140, 140);
	dice1.setLocation(610,255);
	this.dice1.addActionListener(this);
	Mp.add(dice1);
	roll2.setSize(100, 30);
	roll2.setLocation(610,660);
	this.roll2.addActionListener(this);
	Mp.add(roll2);
	card2.setSize(150, 30);
	card2.setLocation(610,700);
	this.card2.addActionListener(this);
	Mp.add(card2);
	loan2.setSize(150, 30);
	loan2.setLocation(610,740);
	this.loan2.addActionListener(this);
	Mp.add(loan2);
	turn2.setSize(150, 30);
	turn2.setLocation(610,780);
	this.turn2.addActionListener(this);
	Mp.add(turn2);
	dice2.setSize(140, 140);
	dice2.setLocation(610,820);
	this.dice2.addActionListener(this);
	Mp.add(dice2);
	BufferedImage myPicture;
	try {
		myPicture = ImageIO.read(new File("IMAGES/images/logo.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		panel1.add(picLabel);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	r.initBoard();
	printButtons(r);
	Mp.getContentPane().add(panel1);
	Mp.getContentPane().add(panel2);
	Mp.getContentPane().add(panel3);
	Mp.setVisible(true);
	Mp.setResizable(false);
}
/**
 * this method prints all the tiles of the board to  the window
 * @param r the board of the game
 */
void printButtons(Board r) {
	
	JButton start = new JButton();
	ImageIcon neo = new ImageIcon("IMAGES/images/start.png");
	start.setPreferredSize(new Dimension(80, 80));
	start.setIcon(neo);
	panel1.add(start);
	for(int i=0;i<31;++i) {
		if(r.MyBoard[i] instanceof MailTile) {
			
				JButton example = new JButton();
				ImageIcon icon = new ImageIcon("IMAGES/images/mc1.png");
				example.setPreferredSize(new Dimension(120, 80));
				example.setText(CheckDay(i));
				example.setHorizontalTextPosition(JButton.CENTER);
				example.setVerticalTextPosition(JButton.CENTER);
				example.setIcon(icon);
				panel1.add(example);
			
		}else if(r.MyBoard[i] instanceof DealTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/deal.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof SweepStakes){
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/sweep.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof LotteryTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/lottery.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof RadioTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/radio.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof BuyerTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/buyer.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof CasinoTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/casino.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof YardSaleTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/yard.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof PayDayTile) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/pay.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}else if(r.MyBoard[i] instanceof MailTile2) {
			JButton example = new JButton();
			ImageIcon icon = new ImageIcon("IMAGES/images/mc2.png");
			example.setPreferredSize(new Dimension(120, 80));
			example.setText(CheckDay(i));
			example.setHorizontalTextPosition(JButton.CENTER);
			example.setVerticalTextPosition(JButton.CENTER);
			example.setIcon(icon);
			panel1.add(example);
		}
		
	}
	JButton jackpot = new JButton();
	neo = new ImageIcon("IMAGES/images/jackpot.png");
	jackpot.setPreferredSize(new Dimension(120, 80));
	jackpot.setIcon(neo);
	panel1.add(jackpot );
	
}
/**
 * this method finds the day of the Tile and adds it on the button
 * @param i
 * @return
 */
String CheckDay(int i) {
	String temp =String.valueOf(i+1);
	while(i>6) {
		i = i-7;
	}
	if(i==0) {
		return "Monday "+ temp;
	}else if(i==1) {
		return "Tuesday "+temp ;
	}else if(i==2){
		return "Wednesday "+ temp;
	}else if(i==3) {
		return "Thursday "+ temp;
	}else if(i==4) {
		return"Friday"+ temp;
	}else if(i==5) {
		return "Saturday "+ temp;
	}else {return "Sunday "+ temp;}
	
}
/**
 * this method gives functionality to all of the buttons
 */
void startup() {}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == roll1) {
		if(controller.Yesfroll== true) {
			controller.fakeroll();
		}else {
		controller.roll1();
		}
	}else if(e.getSource() == roll2) {
		if(controller.Yesfroll== true) {
			controller.fakeroll();
		}else {
		controller.roll2();
		}
	}else if(e.getSource()== loan1) {
		controller.loan1();
	}else if(e.getSource()== loan2) {
		controller.loan2();
	}else if(e.getSource()== turn1) {
		controller.end1();
	}else if(e.getSource()== turn2) {
		controller.end2();
	}else if(e.getSource()== card1) {
		controller.inventory1();
	}else if(e.getSource()== card2) {
		controller.inventory2();
	}else if(e.getSource()== deal) {
		if(controller.dgo==true) {
			controller.dealcard();
		}
	}else if (e.getSource()== mail) {
		if(controller.mgo==true) {
			controller.mailcard();
		}
	}
	
	
}

}
