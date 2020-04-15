package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import engine.Game;
import engine.GameListener;
import exceptions.FullHandException;
import model.heroes.Hero;
import view.HeroButton;
import view.View;

public class Controller implements GameListener , ActionListener
{
 private Game model;
 private View view;
 private Hero firstPlayerHero;
 private Hero secondPlayerHero;
 public Controller() throws FullHandException, CloneNotSupportedException, IOException
 {
	 view = new View(this);
 }

@Override
public void onGameOver()
{
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent e)
{
	// TODO Auto-generated method stub
	
}

public void SetFirstPlayerHero(Hero hero)
{
	firstPlayerHero=hero;
}

public void setSecondPlayerHero(Hero hero) throws FullHandException, CloneNotSupportedException
{
	secondPlayerHero = hero;
	startGame();
}

public void startGame() throws FullHandException, CloneNotSupportedException
{
	model = new Game(firstPlayerHero, secondPlayerHero);
	
}
public static void main(String[] args) throws FullHandException, CloneNotSupportedException, IOException
{
	new Controller();
}
 
}
