package view;

import java.io.IOException;

import javax.swing.JFrame;

public class View extends JFrame
{
  private ChooseFirstHeroView chooseHeroView ;
  private GameView gameView ;
  
  
  public View() throws IOException, CloneNotSupportedException
  {
	  chooseHeroView = new ChooseFirstHeroView(this);
	  gameView = new GameView(this);
	  this.getContentPane().add(chooseHeroView);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setExtendedState(this.MAXIMIZED_BOTH);
	  this.setResizable(false);
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
