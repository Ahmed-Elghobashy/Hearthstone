package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;
import exceptions.FullHandException;
import model.heroes.Hero;

public class View extends JFrame
{
  private ChooseFirstHeroView chooseHeroView ;
  private GameView gameView ;
  private ChooseSecondHeroView secondHeroView;
  private Controller controller;
  private JPanel currentPanel;
  private JPanel firstHeroField;
  private JPanel secondHeroField;
  private JPanel firstHeroHand;
  private JPanel secondHeroHand;
  
  
  public View(Controller controller) throws CloneNotSupportedException, IOException
  {
	  chooseHeroView = new ChooseFirstHeroView(this);
	  this.getContentPane().add(chooseHeroView);
	  currentPanel=chooseHeroView;
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.setResizable(false);
	  this.setVisible(true);
	  this.controller=controller;
  }
  
  public void goToGameView() throws IOException 
  {
	  //gameView = new GameView(this);
	  this.getContentPane().removeAll();
	  //this.getContentPane().add(gameView);
	  this.setTitle("Hearthstone");
	  this.setSize(1500, 800);
	  this.setLayout(new BorderLayout());
	  JPanel firstHero=new JPanel(new BorderLayout());
	  firstHero.setBackground(Color.RED);
	  firstHero.setBorder(new LineBorder(Color.BLACK,3));
	  this.add(firstHero,BorderLayout.SOUTH);
	  this.firstHeroHand=new JPanel();
	  //JLabel j=new JLabel("Hand");
	  //BufferedImage myPicture = ImageIO.read(new File("images/paladin.png"));
	  JLabel test =new JLabel(new ImageIcon("images/paladin5.png"));
	  //test.disable();
	  //test.setDisabledIcon(new ImageIcon(myPicture));
	  //JButton heroImage =new JButton("Hero power");
	  //heroImage.setIcon(new ImageIcon("images/paladin.png"));
	  //JPanel heroImage =new JPanel();
	  JButton deckIcon =new JButton();
	  deckIcon.setIcon(new ImageIcon("images/Deck.png"));
	  this.firstHeroHand.setBackground(Color.BLUE);
	  firstHeroHand.setPreferredSize(new Dimension(600,200));
	  firstHero.add(test,BorderLayout.CENTER);
	  firstHero.add(this.firstHeroHand,BorderLayout.WEST);
	  firstHero.add(deckIcon,BorderLayout.EAST);
	  this.add(firstHero,BorderLayout.SOUTH);
	  this.revalidate();
	  this.repaint();
	  
  }
  
  public static void main(String[] args) throws IOException, CloneNotSupportedException
{
//	new View();
}

public void goToChooseSecondHeroView()
{
  secondHeroView  = new  ChooseSecondHeroView(this);
  this.getContentPane().removeAll();
  this.getContentPane().add(secondHeroView);
  currentPanel=secondHeroView;
  this.revalidate();
}

public JPanel getCurrentPanel()
{
	return currentPanel;
}
}
