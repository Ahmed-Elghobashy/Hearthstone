package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

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
  private JPanel buttons;
  private JPanel infoArea;
  private JLabel cardsLeft;
  private JLabel cardsLeft2;
  private JPanel firstHero;
  private JPanel secondHero;
  
  public View(Controller controller) 
  {
	  chooseHeroView = new ChooseFirstHeroView(this);
	  this.getContentPane().add(chooseHeroView);
	  currentPanel=chooseHeroView;
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.setResizable(false);
	  this.setVisible(true);
	  //this.setIconImage(new ImageIcon("images/Background.jpg").getImage());
	  this.controller=controller;
  }
  
  public void goToGameView(Hero first,Hero second ) throws IOException 
  {
	  //gameView = new GameView(this);
	  this.getContentPane().removeAll();
	  //this.getContentPane().add(gameView);
	  this.setTitle("Hearthstone");
	  this.setSize(1500, 800);
	  this.setLayout(new BorderLayout());
	  firstHero=new JPanel(new BorderLayout());
	  firstHero.setBackground(Color.RED);
	  firstHero.setBorder(new LineBorder(Color.BLACK,3));
	  this.add(firstHero,BorderLayout.SOUTH);
	  this.firstHeroHand=new JPanel();
	  JLabel firstHeroImage =new JLabel(this.chooseHeroImage(first));
	  firstHeroImage.setBackground(Color.MAGENTA);
	  JPanel firstDeck =new JPanel(new BorderLayout());
	  cardsLeft =new JLabel("Cards Left : 20");
	  JLabel deckIcon =new JLabel();
	  deckIcon.setIcon(new ImageIcon("images/Deck 3.png"));
	  deckIcon.setBorder(null);
	  firstDeck.add(deckIcon,BorderLayout.NORTH);
	  firstDeck.add(cardsLeft,BorderLayout.SOUTH);
	  this.firstHeroHand.setBackground(Color.BLUE);
	  firstHeroHand.setPreferredSize(new Dimension(1000,200));
	  firstHero.add(firstHeroImage,BorderLayout.CENTER);
	  firstHero.add(this.firstHeroHand,BorderLayout.WEST);
	  firstHero.add(firstDeck,BorderLayout.EAST);
	  this.add(firstHero,BorderLayout.SOUTH);
	  buttons =new JPanel(new GridLayout(0,1));
	  infoArea =new JPanel(new BorderLayout());
	  infoArea.setBackground(Color.darkGray);
	  JPanel center=new JPanel(new BorderLayout());
	  JPanel fields =new JPanel(new BorderLayout());
	  firstHeroField=new JPanel();
	  fields.add(this.firstHeroField,BorderLayout.SOUTH);
	  this.firstHeroField.setBackground(Color.cyan);
	  this.firstHeroField.setPreferredSize(new Dimension(1000,180));
	  this.secondHeroField=new JPanel();
	  this.secondHeroField.setPreferredSize(new Dimension(1000,180));
	  this.secondHeroField.setBackground(Color.YELLOW);
	  fields.add(this.secondHeroField,BorderLayout.NORTH);
	  center.add(infoArea,BorderLayout.CENTER);
	  center.add(fields,BorderLayout.WEST);
	  center.add(buttons,BorderLayout.EAST);
	  this.add(center,BorderLayout.CENTER);
	  secondHero=new JPanel();
	  this.secondHeroHand=new JPanel();
	  this.secondHeroHand.setBackground(Color.MAGENTA);
	  secondHeroHand.setPreferredSize(new Dimension(1000,200));
	  secondHero.add(this.secondHeroHand,BorderLayout.WEST);
	  JPanel secondDeck =new JPanel(new BorderLayout());
	  cardsLeft2 =new JLabel("Cards Left : 20");
	  JLabel deckIcon2 =new JLabel();
	  deckIcon2.setIcon(new ImageIcon("images/Deck 3.png"));
	  deckIcon2.setBorder(null);
	  JLabel secondHeroImage =new JLabel(this.chooseHeroImage(second));
	  secondDeck.add(deckIcon2,BorderLayout.NORTH);
	  secondDeck.add(cardsLeft2,BorderLayout.SOUTH);
	  secondHero.add(secondHeroImage,BorderLayout.CENTER);
	  secondHero.add(secondDeck,BorderLayout.EAST);
	  
	  
	  this.add(secondHero,BorderLayout.NORTH);
	 

	  this.revalidate();
	  this.repaint();
	  
  }
  
  public JPanel getFirstHero()
{
	return firstHero;
}

public void setFirstHero(JPanel firstHero)
{
	this.firstHero = firstHero;
}

public JPanel getSecondHero()
{
	return secondHero;
}

public void setSecondHero(JPanel secondHero)
{
	this.secondHero = secondHero;
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
public ImageIcon chooseHeroImage(Hero h) {
	if(h instanceof Paladin)
	{
		return(new ImageIcon("images/paladin 7.png"));
	}
	if(h instanceof Mage)
	{
		return(new ImageIcon("images/mage.png"));
	}
	if(h instanceof Warlock)
	{
		return(new ImageIcon("images/warlock.png"));
	}
	
	if(h instanceof Hunter)
	{
		return(new ImageIcon("images/hunter.png"));
	}
	if(h instanceof Priest)
	{
		return(new ImageIcon("images/priest.png"));
	}
return null;
}

public ChooseFirstHeroView getChooseHeroView() {
	return chooseHeroView;
}

public void setChooseHeroView(ChooseFirstHeroView chooseHeroView) {
	this.chooseHeroView = chooseHeroView;
}

public GameView getGameView() {
	return gameView;
}

public void setGameView(GameView gameView) {
	this.gameView = gameView;
}

public ChooseSecondHeroView getSecondHeroView() {
	return secondHeroView;
}

public void setSecondHeroView(ChooseSecondHeroView secondHeroView) {
	this.secondHeroView = secondHeroView;
}

public Controller getController() {
	return controller;
}

public void setController(Controller controller) {
	this.controller = controller;
}

public JPanel getFirstHeroField() {
	return firstHeroField;
}

public void setFirstHeroField(JPanel firstHeroField) {
	this.firstHeroField = firstHeroField;
}

public JPanel getSecondHeroField() {
	return secondHeroField;
}

public void setSecondHeroField(JPanel secondHeroField) {
	this.secondHeroField = secondHeroField;
}

public JPanel getFirstHeroHand() {
	return firstHeroHand;
}

public void setFirstHeroHand(JPanel firstHeroHand) {
	this.firstHeroHand = firstHeroHand;
}

public JPanel getSecondHeroHand() {
	return secondHeroHand;
}

public void setSecondHeroHand(JPanel secondHeroHand) {
	this.secondHeroHand = secondHeroHand;
}

public JPanel getButtons() {
	return buttons;
}

public void setButtons(JPanel buttons) {
	this.buttons = buttons;
}

public JPanel getInfoArea() {
	return infoArea;
}

public void setInfoArea(JPanel infoArea) {
	this.infoArea = infoArea;
}

public void setCurrentPanel(JPanel currentPanel) {
	this.currentPanel = currentPanel;
}

public JLabel getCardsLeft() {
	return cardsLeft;
}

public void setCardsLeft(JLabel cardsLeft) {
	this.cardsLeft = cardsLeft;
}

public JLabel getCardsLeft2() {
	return cardsLeft2;
}

public void setCardsLeft2(JLabel cardsLeft2) {
	this.cardsLeft2 = cardsLeft2;
}
}
