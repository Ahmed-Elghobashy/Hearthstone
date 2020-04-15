package view;

import javax.swing.JButton;

import model.heroes.Hero;

public class HeroButton extends JButton 
{
  private Hero hero;	
  private int playernumber;
  
  public HeroButton(Hero hero,int playernumber)
  {
	  super();
	  this.hero=hero;
	  this.playernumber=playernumber;
  }

public Hero getHero()
{
	return hero;
}

public void setHero(Hero hero)
{
	this.hero = hero;
}

public int getPlayernumber()
{
	return playernumber;
}

public void setPlayernumber(int playernumber)
{
	this.playernumber = playernumber;
}

}
