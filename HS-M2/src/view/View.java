package view;

import java.io.IOException;

import javax.swing.JFrame;

import controller.Controller;
import exceptions.FullHandException;
import model.heroes.Hero;

public class View extends JFrame
{
  private ChooseFirstHeroView chooseHeroView ;
  private GameView gameView ;
  private ChooseSecondHeroView secondHeroView;
  private Controller controller;
  
  public View(Controller controller) throws IOException, CloneNotSupportedException
  {
	  chooseHeroView = new ChooseFirstHeroView(this,controller);
	  gameView = new GameView(this);
	  secondHeroView  = new  ChooseSecondHeroView(this,controller);
	  this.getContentPane().add(chooseHeroView);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.setResizable(false);
	  this.setVisible(true);
	  this.controller=controller;
  }
  
  public void goToGameView(Hero hero) throws FullHandException, CloneNotSupportedException
  {
	  this.getContentPane().removeAll();
	  this.getContentPane().add(gameView);
	  controller.setSecondPlayerHero(hero);
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
  this.revalidate();
}
}
