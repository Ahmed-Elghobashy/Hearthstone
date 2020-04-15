package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

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
 public Controller() 
 {
	 try
	{
		view = new View(this);
	} catch (IOException | CloneNotSupportedException e)
	{
		JOptionPane.showMessageDialog(view.getCurrentPanel(),"Error appened  while starting the game");
	}
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

public void setSecondPlayerHero(Hero hero) 
{
	secondPlayerHero = hero;
	startGame();
}

public void startGame() 
{
	try
	{
		model = new Game(firstPlayerHero, secondPlayerHero);
	} catch (FullHandException e)
	{
		//It is not important to deal with this exception becauses when intializing the game it is not possible to hava 
		// a fullHandException
	}
	catch (CloneNotSupportedException e)
	{
		JOptionPane.showMessageDialog(view.getCurrentPanel(),"Error appened  while starting the game");

	}
	
}
public static void main(String[] args) 
{
	new Controller();
}
 
}
