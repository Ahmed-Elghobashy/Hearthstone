package controller;

import javax.swing.JButton;

import model.heroes.Hero;

public class HeroButton extends JButton
{
	Hero hero;
	Controller controller;
	public HeroButton(Hero hero, Controller controller)
	{
		super();
		this.hero = hero;
		this.controller = controller;
	}
	public Hero getHero()
	{
		return hero;
	}
	public void setHero(Hero hero)
	{
		this.hero = hero;
	}
	public Controller getController()
	{
		return controller;
	}
	public void setController(Controller controller)
	{
		this.controller = controller;
	}
	
	

}
