package view;

import java.io.IOException;

import javax.swing.JFrame;

public class View extends JFrame
{
  private ChooseHeroView chooseHeroView ;
  private GameView gameView ;
  
  
  public View() throws IOException, CloneNotSupportedException
  {
	  chooseHeroView = new ChooseHeroView(this);
	  gameView = new GameView(this);
	  this.getContentPane().add(chooseHeroView);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setSize(600,800);
	  this.setVisible(true);
  }
  
  public void goToGameView()
  {
	  this.getContentPane().removeAll();
	  this.getContentPane().add(gameView);
	  this.revalidate();
  }
  
  public static void main(String[] args) throws IOException, CloneNotSupportedException
{
	new View();
}
}
