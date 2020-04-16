package view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
  
  public View(Controller controller) throws CloneNotSupportedException, IOException
  {
	  chooseHeroView = new ChooseFirstHeroView(this);
	  gameView = new GameView(this);
	  secondHeroView  = new  ChooseSecondHeroView(this);
	  this.getContentPane().add(chooseHeroView);
	  currentPanel=chooseHeroView;
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.setResizable(false);
	  this.setVisible(true);
	  this.controller=controller;
  }
  
  public void goToGameView() 
  {
	  this.getContentPane().removeAll();
	  this.getContentPane().add(gameView);
	  this.revalidate();
  }
  
  public static void main(String[] args) throws IOException, CloneNotSupportedException
{
//	new View();
}

public void goToChooseSecondHeroView()
{
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
