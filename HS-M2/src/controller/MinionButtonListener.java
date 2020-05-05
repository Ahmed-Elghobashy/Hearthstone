package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Priest;

public class MinionButtonListener extends AbstractAction
{
	Controller controller;
	Hero currentHero;
	
	 public MinionButtonListener(Controller controller)
	{
		currentHero=controller.getModel().getCurrentHero();
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof MinionButton)
		{	
		  MinionButton button = (MinionButton) e.getSource();
		  Minion minion = button.getMinion();
		  Hero minionPlayer = button.getPlayer();
		  if(controller.getAttackingMinion()==minion)
		  {
			  controller.setAttackingMinion(null);
			  controller.setAttackingWithMinonHero(null);
			  return;
		  }
		  //If the attacking minion in controller is null that means we are choosing this minion to be the attacking minion
		  if (button.isOnField())
		  {	  
		   if(controller.getAttackingMinion() == null && controller.getUsingHeroPower()==null && controller.getChosenSpell()==null)
		   {
				controller.setAttackingMinion(minion);
				sounda(minion);
				controller.setAttackingWithMinonHero(minionPlayer);
				return;
			 
				 
		    }
		   else 
		    {
			   if(controller.getUsingHeroPower()!=null)
			   {
				   if(controller.getUsingHeroPower() instanceof Priest)
				   {
					   Priest priest = (Priest) controller.getUsingHeroPower();
					   try
					 {
						priest.useHeroPower(minion);
						controller.setUsingHeroPower(null);
						controller.setAttackingWithMinonHero(null);
						controller.updateView();
						return;
					 } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullHandException | FullFieldException | CloneNotSupportedException e1)
					 {
						JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
						controller.setUsingHeroPower(null);
						controller.setAttackingWithMinonHero(null);
						return;
					 }
					   
				   }
				   if(controller.getUsingHeroPower() instanceof Mage)
				   {
					   Mage mage = (Mage) controller.getUsingHeroPower();
					   try
					   {
						   mage.useHeroPower(minion);
						   controller.setUsingHeroPower(null);
						   controller.setAttackingWithMinonHero(null);
						   controller.updateView();
						   return;
					   } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							   | FullHandException | FullFieldException | CloneNotSupportedException e1)
					   {
						   JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
						   controller.setUsingHeroPower(null);
						   controller.setAttackingWithMinonHero(null);
						   return;
					   }
					   
				   }
			   }
			 else
			 {
				 
				   
			   //this code here is for attacking the minion 
				if(controller.getAttackingMinion()!=null)
				{	
				 Minion attackingMinion = controller.getAttackingMinion();
			 
				 try
				 {
					 controller.getAttackingWithMinonHero().attackWithMinion(attackingMinion, minion);
					 controller.setAttackingMinion(null);
					 controller.setAttackingWithMinonHero(null);
					 controller.updateView();
					 return;
				 } catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
						 | NotSummonedException e1)
				 {
					 JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
					 controller.setAttackingMinion(null);
					 controller.setAttackingWithMinonHero(null);
					 return;

				 }
				}
				else 
				{
					 if(controller.getChosenSpell()!=null) {
						  SpellButton sbutton=controller.getChosenSpell();
						  Spell spell=sbutton.getSpell();
						  Hero hero=sbutton.getHero();
						  try {
							 if(spell instanceof MinionTargetSpell) 
						        hero.castSpell((MinionTargetSpell) spell, minion);
							 else {
							 if(spell instanceof LeechingSpell)
								 hero.castSpell((LeechingSpell) spell, minion);
							 else 
								 JOptionPane.showMessageDialog(controller.getView(), "Invalid target");
							 }
							 controller.setChosenSpell(null);
							 controller.updateView();
						  
					  }catch(NotYourTurnException| NotEnoughManaException| InvalidTargetException e1) {
						  controller.setChosenSpell(null);
						  JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
					  }
						  return;
					  }
					
				}
				  
			   
			 }
		    }
		  }
		  else 
		  {

			if(controller.getAttackingMinion()!=null)
			{
				JOptionPane.showMessageDialog(controller.getView(),"You can not attack a minion that your opponent has not summoned yet");
				controller.setAttackingMinion(null);
				return;
			}
			 
			controller.setUsingHeroPower(null);
			   try
			{
				minionPlayer.playMinion(minion);
				soundp(minion);
			    button.setOnField(true);
			    controller.updateView();
			} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1)
			{
				JOptionPane.showMessageDialog(controller.getView(), e1.getMessage());
			}

		  }

		}
		  

	}
	
	
	// checks if the minion is of the first player and if it is on the field
	public boolean isPlayerMinion(Minion minion)
	{
		for (Minion m : controller.getModel().getCurrentHero().getField())
		  if (m==minion)
			  return true;
		  return false;
	}

	public boolean isCurrentHero(Hero hero)
	{
		if(hero==controller.getModel().getCurrentHero())
			return true;
		return false;
	}
	
	public  void soundp (Card card) {
		
		if(card.getName().equals("Chromaggus"))
		controller.playSfx("sounds/chromp.wav");
		if(card.getName().equals("Tirion Fordring"))
			controller.playSfx("sounds/tirionp.wav");
		if(card.getName().equals("Boulderfist Ogre"))
			controller.playSfx("sounds/boulderp.wav");
	/*	if(card.getName().equals("Sunwalker"))
			controller.playSfx("sounds/suna.wav");
		*/if(card.getName().equals("Argent Commander"))
			controller.playSfx("sounds/argentp.wav");
		if(card.getName().equals("Bloodfen Raptor"))
			controller.playSfx("sounds/bloodp.wav");
		if(card.getName().equals("Chilwind Yeti"))
			controller.playSfx("sounds/chillp.wav");
		
		if(card.getName().equals("Core Hound"))
			controller.playSfx("sounds/corep.wav");
		if(card.getName().equals("Frostwolf Grunt"))
			controller.playSfx("sounds/frostp.wav");
		if(card.getName().equals("Goldshire Footman"))
			controller.playSfx("sounds/goldp.wav");
		if(card.getName().equals("The LichKing"))
			controller.playSfx("sounds/lichp.wav");
		if(card.getName().equals("Stonetusk Boar"))
			controller.playSfx("sounds/stonep.wav");
		if(card.getName().equals("Wolfrider"))
			controller.playSfx("sounds/wolfp.wav");
		/*if(card.getName().equals("Curse of Weakness"))
			 return new ImageIcon("images/curse2.png");
		if(card.getName().equals("Divine Spirit"))
			 return new ImageIcon("images/divine2.png");
		if(card.getName().equals("Flamestrike"))
			 return new ImageIcon("images/flame2.png");
		if(card.getName().equals("Holy Nova"))
			 return new ImageIcon("images/holy2.png");
		if(card.getName().equals("Kill Command"))
			 return new ImageIcon("images/kill2.png");
		if(card.getName().equals("Level Up!"))
			 return new ImageIcon("images/level2.png");
		if(card.getName().equals("Multi-Shot"))
			 return new ImageIcon("images/multi2.png");
		if(card.getName().equals("Polymorph"))
			 return new ImageIcon("images/polymorph2.png");
		if(card.getName().equals("Pyroblast"))
			 return new ImageIcon("images/pyroblast2.png");
		if(card.getName().equals("Seal of Champions"))
			 return new ImageIcon("images/seal2.png");
		if(card.getName().equals("Shadow Word: Death"))
			 return new ImageIcon("images/shadow2.png");
		if(card.getName().equals("Siphon Soul"))
			 return new ImageIcon("images/siphon2.png");
		if(card.getName().equals("Twisting Nether"))
			 return new ImageIcon("images/twisting2.png");
		*/if(card.getName().equals("Icehowl"))
			controller.playSfx("sounds/icehowlp.wav");
		if(card.getName().equals("Silver Hand Recruit"))
			controller.playSfx("sounds/silverp.wav");
		if(card.getName().equals("Prophet Velen"))
			controller.playSfx("sounds/prophetp.wav");
		if(card.getName().equals("Wilfred Fizzlebang"))
			controller.playSfx("sounds/wilfredp.wav");
		if(card.getName().equals("Kalycgos"))
			controller.playSfx("sounds/kalecgosp.wav");
		if(card.getName().equals("King Krush"))
			controller.playSfx("sounds/kingp.wav");
		/*if(card.getName().equals("Sheep"))
			 return new ImageIcon("images/sheep2.png");
		if(card.getName().equals("The LichKing"))
			 return new ImageIcon("images/lich.png");
		
		*/
		if(card.getName().equals("Colossus of the Moon"))
			controller.playSfx("sounds/colp.wav");
		if(card.getName().equals("Sunwalker"))
			controller.playSfx("sounds/sunp.wav");
		
		
	
		
	}
public  void sounda (Card card) {
		
		if(card.getName().equals("Chromaggus"))
		controller.playSfx("sounds/chroma.wav");
		if(card.getName().equals("Tirion Fordring"))
			controller.playSfx("sounds/tirionp.wav");
		if(card.getName().equals("Boulderfist Ogre"))
			controller.playSfx("sounds/bouldera.wav");
		if(card.getName().equals("Sunwalker"))
			controller.playSfx("sounds/suna.wav");
		if(card.getName().equals("Argent Commander"))
			controller.playSfx("sounds/argenta.wav");
		if(card.getName().equals("Bloodfen Raptor"))
			controller.playSfx("sounds/blooda.wav");
		if(card.getName().equals("Chilwind Yeti"))
			controller.playSfx("sounds/chillp.wav");
		
		if(card.getName().equals("Core Hound"))
			controller.playSfx("sounds/corea.wav");
		if(card.getName().equals("Frostwolf Grunt"))
			controller.playSfx("sounds/frosta.wav");
		if(card.getName().equals("Goldshire Footman"))
			controller.playSfx("sounds/golda.wav");
		if(card.getName().equals("The LichKing"))
			controller.playSfx("sounds/licha.wav");
		if(card.getName().equals("Stonetusk Boar"))
			controller.playSfx("sounds/stonea.wav");
		if(card.getName().equals("Wolfrider"))
			controller.playSfx("sounds/wolfa.wav");
		/*if(card.getName().equals("Curse of Weakness"))
			 return new ImageIcon("images/curse2.png");
		if(card.getName().equals("Divine Spirit"))
			 return new ImageIcon("images/divine2.png");
		if(card.getName().equals("Flamestrike"))
			 return new ImageIcon("images/flame2.png");
		if(card.getName().equals("Holy Nova"))
			 return new ImageIcon("images/holy2.png");
		if(card.getName().equals("Kill Command"))
			 return new ImageIcon("images/kill2.png");
		if(card.getName().equals("Level Up!"))
			 return new ImageIcon("images/level2.png");
		if(card.getName().equals("Multi-Shot"))
			 return new ImageIcon("images/multi2.png");
		if(card.getName().equals("Polymorph"))
			 return new ImageIcon("images/polymorph2.png");
		if(card.getName().equals("Pyroblast"))
			 return new ImageIcon("images/pyroblast2.png");
		if(card.getName().equals("Seal of Champions"))
			 return new ImageIcon("images/seal2.png");
		if(card.getName().equals("Shadow Word: Death"))
			 return new ImageIcon("images/shadow2.png");
		if(card.getName().equals("Siphon Soul"))
			 return new ImageIcon("images/siphon2.png");
		if(card.getName().equals("Twisting Nether"))
			 return new ImageIcon("images/twisting2.png");
		*/if(card.getName().equals("Icehowl"))
			controller.playSfx("sounds/icehowla.wav");
		if(card.getName().equals("Silver Hand Recruit"))
			controller.playSfx("sounds/silvera.wav");
		if(card.getName().equals("Prophet Velen"))
			controller.playSfx("sounds/propheta.wav");
		if(card.getName().equals("Wilfred Fizzlebang"))
			controller.playSfx("sounds/wilfreda.wav");
		if(card.getName().equals("Kalycgos"))
			controller.playSfx("sounds/kalecgosa.wav");
		if(card.getName().equals("King Krush"))
			controller.playSfx("sounds/kinga.wav");
		/*if(card.getName().equals("Sheep"))
			 return new ImageIcon("images/sheep2.png");
		if(card.getName().equals("The LichKing"))
			 return new ImageIcon("images/lich.png");
		
		*/
		if(card.getName().equals("Colossus of the Moon"))
			controller.playSfx("sounds/cola.wav");
		if(card.getName().equals("Sunwalker"))
			controller.playSfx("sounds/suna.wav");
		
		
	
		
	}

}
