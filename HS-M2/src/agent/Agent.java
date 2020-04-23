package agent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Handler;

import org.junit.Ignore;

import controller.Controller;
import controller.HeroButton;
import engine.Game;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.heroes.*;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Agent
{
 private Game model ;
 private Controller controller;
 private Hero agentHero ;
 private Hero opponentHero;
 private ArrayList<Minion> canAttackMinions;
 private ArrayList<Minion> attackedMinions;
 private ArrayList<MinionMove> allPossibleMinonAttacks;
 private ArrayList<SpellMove> allPossibleSpellMoves;
 private ArrayList<Minion> alreadyAttackedMinions;
 private ArrayList<ManaCostingMove> allManaCostingMoves;
public Agent(Game model, Controller controller)
{
	super();
	this.model = model;
	this.controller = controller;
	agentHero = controller.getSecondPlayerHero();
	opponentHero=controller.getFirstPlayerHero();
} 
public Agent(Game model, Controller controller,Hero hero)
{
	this.model=model;
	this.controller=controller;
	this.agentHero=hero;
	if(hero==controller.getFirstPlayerHero())
		opponentHero=controller.getSecondPlayerHero();
	else
		 opponentHero=controller.getFirstPlayerHero();
}

private void setCanAttackMinions()
{
	canAttackMinions = new ArrayList<Minion>();
	for (Minion minion : agentHero.getField())
	{
		if(!minion.isSleeping())
			canAttackMinions.add(minion);
	}
}

private void setAttackedMinions()
{
	attackedMinions = new ArrayList<Minion>();
	if(OpponenthasTaunt())
	{
		for (Minion minion : opponentHero.getField())
		{
			if(minion.isTaunt())
				attackedMinions.add(minion);
		}
	}
	else {
		for (Minion minion : opponentHero.getField())
		{
				attackedMinions.add(minion);
		}
	}
	
}


private boolean OpponenthasTaunt()
{
    	for (Minion minion : opponentHero.getField())
		{
			if(minion.isTaunt())
				return true;
		}
    	
    	return false;
}

private void generateAllPossibleMinionAttacks()
{
	setAttackedMinions();
	setCanAttackMinions();
	allPossibleMinonAttacks = new ArrayList<MinionMove>();
	for (Minion minion : canAttackMinions)
	{
		for (Minion minion2 : attackedMinions)
		{
			allPossibleMinonAttacks.add(new MinionMove(minion,minion2));
		}
		allPossibleMinonAttacks.add(new MinionMove(minion,opponentHero));
	}
	
}

private void attackWithMinion(MinionMove move)
{
	if(move.isMinionAttack())
	{
		try
		{
			agentHero.attackWithMinion(move.getAttackingMinion(), move.getAttackedMinion());
		} catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException
				| NotSummonedException e)
		{
			e.printStackTrace();
		}
		return;
	}
	else {
		try
		{
			agentHero.attackWithMinion(move.getAttackingMinion(), move.getAttackedHero());
		} catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException
				| InvalidTargetException e)
		{
			e.printStackTrace();
		}
	}
	
}

private void generateAllPossibleSpellMoves()
{
	allPossibleSpellMoves = new ArrayList<SpellMove>();
	for (Card card :agentHero.getHand())
	{
		if(card instanceof Spell)
		{
			Spell spell = (Spell) card;
			if(card instanceof MinionTargetSpell || card instanceof LeechingSpell)
				if(!isPositive(spell))
					for (Minion minion : opponentHero.getField())
						allPossibleSpellMoves.add(new SpellMove(spell, minion));
				else 
					for(Minion minion : agentHero.getField())
						 allPossibleSpellMoves.add(new SpellMove(spell, minion));
			else {
				allPossibleSpellMoves.add(new SpellMove(spell));
			}
		}
	}
}

private boolean spellIsInHand(SpellMove spellmove)
{
	Spell spell = spellmove.getSpell();
	for(Card card : agentHero.getHand())
		if(spell==card)
			return true;
	return false;
}

private void playSpellMove(SpellMove move)
{
	if(!spellIsInHand(move))
		return;
	
	Spell spell = move.getSpell();
	if(agentHero.getCurrentManaCrystals()< spell.getManaCost())
		return;
	if(move.isAttackingMinionSpell())
	{
		
		if(spell instanceof MinionTargetSpell)
		{
			
			try
			{
				if(move.getAttackedMinion()!=null)
					agentHero.castSpell((MinionTargetSpell)spell, move.getAttackedMinion());
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e)
			{
				e.printStackTrace();
			}
		}
		
		if(spell instanceof LeechingSpell)
		{
			try
			{
				agentHero.castSpell((LeechingSpell)spell, move.getAttackedMinion());
			} catch (NotYourTurnException | NotEnoughManaException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	else {
		if(spell instanceof AOESpell)
		{
			try
			{
				agentHero.castSpell((AOESpell)spell, opponentHero.getField());
			} catch (NotYourTurnException | NotEnoughManaException e)
			{
				e.printStackTrace();
			}
		}
		
		if(spell instanceof FieldSpell)
		{
			try
			{
				agentHero.castSpell((FieldSpell)spell);
			} catch (NotYourTurnException | NotEnoughManaException e)
			{
				e.printStackTrace();
			}
			
			
		}
		if(spell instanceof HeroTargetSpell)
		{
			try
			{
				if(move.getAttackedHero()!=null)
					agentHero.castSpell((HeroTargetSpell)spell,move.getAttackedHero());
			} catch (NotYourTurnException | NotEnoughManaException e)
			{
				e.printStackTrace();
			}
		}
	}
}

private boolean isPositive(Spell spell)
{
	if(spell instanceof MinionTargetSpell || spell instanceof LeechingSpell)
	{
		if(spell instanceof SealOfChampions)
			return true;
	}
	return false;
}



private ArrayList<ManaCostingMove> generateUseHeroPowerMoves()
{
	ArrayList<ManaCostingMove> retArrayList = new ArrayList<ManaCostingMove>();
	UseHeroPowerMove heroPowerMove = new UseHeroPowerMove(agentHero);
	if(heroPowerMove.isAttackingHero())
	{
		retArrayList.add(new UseHeroPowerMove(agentHero,opponentHero));
	}
	if(heroPowerMove.isAttackingMinion())
	{
		if(agentHero instanceof Mage)
			for (Minion minion :opponentHero.getField())
				retArrayList.add(new UseHeroPowerMove(agentHero,minion));
		if(agentHero instanceof Priest)
			for (Minion minion : agentHero.getField())
				retArrayList.add(new UseHeroPowerMove(agentHero,minion));
			
	}
	if(!heroPowerMove.isAttackingHero() && !heroPowerMove.isAttackingMinion())
		retArrayList.add(new UseHeroPowerMove(agentHero));
	return retArrayList;
		
}


private void generatingAllManaCostingMoves()
{
	allManaCostingMoves = new ArrayList<ManaCostingMove>();
	allManaCostingMoves.addAll(allPossibleSpellMoves);
	allManaCostingMoves.addAll(generateUseHeroPowerMoves());
	for (Card card : agentHero.getHand())
		if(card instanceof Minion)
			allManaCostingMoves.add(new PlayMinionMove((Minion) card, agentHero));
	
	
}

private void playMinionMove(PlayMinionMove move)
{
	Hero hero = move.getPlayinHero();
	Minion minion = move.getMinion();
	
	try
	{
		hero.playMinion(minion);
	} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void useHeroPowerMove(UseHeroPowerMove move)
{
	if(agentHero instanceof Mage)
	{
		agentHero = (Mage) agentHero;
		if(move.getAttackedHero()!=null)
		{
			try
			{
				((Mage)agentHero).useHeroPower(move.getAttackedHero());
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (move.getAttackedMinion()!=null) {
			try
			{
				((Mage) agentHero).useHeroPower(move.getAttackedMinion());
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	else if(agentHero instanceof Priest)
	{
		agentHero =(Priest) agentHero;
		if(move.getAttackedHero()!=null)
		{
			try
			{
				((Priest)agentHero).useHeroPower(move.getAttackedHero());
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (move.getAttackedMinion()!=null) {
			try
			{
				((Priest) agentHero).useHeroPower(move.getAttackedMinion());
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
					| FullFieldException | CloneNotSupportedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	else 
	{
		try
		{
			agentHero.useHeroPower();
		} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
				| FullFieldException | CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



private void playCards()
{
	ArrayList<Object> hand = new ArrayList<Object>();
	
	for(Card card : agentHero.getHand())
	{
		if(card instanceof Minion)
		{
			hand.add(card);
		}
	}
	hand.addAll(allPossibleSpellMoves);
	Collections.shuffle(hand);
	for (Object card : hand)
	{
		if(card instanceof Minion)
		{
			Minion minion = (Minion) card;
		  if(agentHero.getCurrentManaCrystals()>= minion.getManaCost())
			try
			{
				agentHero.playMinion((Minion) card);
				
			} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e)
			{
				e.printStackTrace();
			}
			
		}
		if(card instanceof SpellMove)
		{
			playSpellMove((SpellMove) card);
		}
	}
	
	
	
}

private void attackWithAllTheMinions()
{
	alreadyAttackedMinions = new ArrayList<Minion>();
	for(MinionMove minionMove : allPossibleMinonAttacks)
	{
		if(!alreadyAttackedMinions.contains(minionMove.getAttackingMinion()))
		{
			attackWithMinion(minionMove);
		}
	}
	
}

public void playTurn()
{
	generateAllPossibleMinionAttacks();
	generateAllPossibleSpellMoves();
	attackWithAllTheMinions();
	playCards();
	try
	{
		agentHero.endTurn();
		controller.updateView();
	} catch (FullHandException | CloneNotSupportedException e)
	{
		e.printStackTrace();
	}
}


public Game cloneGame() throws IOException, CloneNotSupportedException
{
	Hero firstHero = controller.getFirstPlayerHero();
	Hero secondHero = controller.getSecondPlayerHero();
	Hero firstHeroClone = cloneHero(firstHero);
	Hero secondHeroClone = cloneHero(secondHero);
	
	if(firstHero==model.getCurrentHero())
		return new Game(firstHeroClone,secondHeroClone,1);
	else 
		return new Game(firstHeroClone,secondHeroClone,2);
	
	
	
}
 
public Hero cloneHero(Hero hero) throws IOException, CloneNotSupportedException
{
	Hero retHero =null;
	if(hero instanceof Mage)
	{
		retHero= new Mage();
	}
	if(hero instanceof Priest)
	{
		retHero = new Priest();
	}
	if(hero instanceof Warlock)
	{
		retHero = new Warlock();
	}
	if(hero instanceof Paladin)
	{
		retHero = new Paladin();
	}
	if(hero instanceof Hunter)
	{
		retHero = new Hunter();
	}
	
	retHero.getDeck().clear();
	for(Card card : hero.getDeck())
		retHero.getDeck().add(card.clone());
	retHero.getHand().clear();
	for(Card card : hero.getHand())
		retHero.getHand().add(card.clone());
	retHero.getField().clear();
	for(Minion minion : hero.getField())
		retHero.getField().add(minion.clone());
	retHero.setCurrentHP(hero.getCurrentHP());
	retHero.setCurrentManaCrystals(hero.getCurrentManaCrystals());
	retHero.setTotalManaCrystals(hero.getTotalManaCrystals());
	
	return retHero;
}
 
 
}



