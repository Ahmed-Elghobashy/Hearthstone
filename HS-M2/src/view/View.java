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
  
  
  public View(Controller controller) throws CloneNotSupportedException, IOException
  {
	  chooseHeroView = new ChooseFirstHeroView(this);
	  this.getContentPane().add(chooseHeroView);
	  currentPanel=chooseHeroView;
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.setResizable(false);
	  this.setVisible(true);
	  this.setIconImage(new ImageIcon("images/Background.jpg").getImage());
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
	  JPanel firstHero=new JPanel(new BorderLayout());
	  firstHero.setBackground(Color.BLACK);
	  firstHero.setBorder(new LineBorder(Color.BLACK,3));
	  this.add(firstHero,BorderLayout.SOUTH);
	  this.firstHeroHand=new JPanel();
	  //JLabel j=new JLabel("Hand");
	  //BufferedImage myPicture = ImageIO.read(new File("images/paladin.png"));
	  JLabel firstHeroImage =new JLabel(this.chooseHeroImage(first));
	  firstHeroImage.setBackground(Color.MAGENTA);
	  //test.disable();
	  //test.setDisabledIcon(new ImageIcon(myPicture));
	  //JButton heroImage =new JButton("Hero power");
	  //heroImage.setIcon(new ImageIcon("images/paladin.png"));
	  //JPanel heroImage =new JPanel();
	  JButton deckIcon =new JButton();
	  deckIcon.setIcon(new ImageIcon("images/Deck 3.png"));
	  deckIcon.setBorder(null);
	  deckIcon.setBorderPainted(false);
	  this.firstHeroHand.setBackground(Color.BLUE);
	  firstHeroHand.setPreferredSize(new Dimension(1000,200));
	  firstHero.add(firstHeroImage,BorderLayout.CENTER);
	  firstHero.add(this.firstHeroHand,BorderLayout.WEST);
	  firstHero.add(deckIcon,BorderLayout.EAST);
	  this.add(firstHero,BorderLayout.SOUTH);
	  JPanel fields =new JPanel(new BorderLayout());
	  firstHeroField=new JPanel();
	  fields.add(this.firstHeroField,BorderLayout.SOUTH);
	  this.firstHeroField.setBackground(Color.cyan);
	  this.secondHeroField=new JPanel();
	  this.secondHeroField.setBackground(Color.YELLOW);
	  fields.add(this.secondHeroField,BorderLayout.NORTH);
	  this.add(fields,BorderLayout.CENTER);
	  JPanel secondHero=new JPanel();
	  this.secondHeroHand=new JPanel();
	  this.secondHeroHand.setBackground(Color.MAGENTA);
	  secondHeroHand.setPreferredSize(new Dimension(1000,200));
	  secondHero.add(this.secondHeroHand,BorderLayout.WEST);
	  firstHero.add(deckIcon,BorderLayout.EAST);
	  this.add(secondHero,BorderLayout.NORTH);
	 

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
}
