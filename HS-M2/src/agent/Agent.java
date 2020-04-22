package agent;

import java.util.ArrayList;
import java.util.Collections;

import controller.Controller;
import controller.HeroButton;
import engine.Game;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
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
 private Hero hero ;
 private ArrayList<Minion> canAttackMinions;
 private ArrayList<Minion> attackedMinions;
 private ArrayList<MinionMove> allPossibleMinonAttacks;
 private ArrayList<SpellMove> allPossibleSpellMoves;
 private ArrayList<Minion> alreadyAttackedMinions;
public Agent(Game model, Controller controller)
{
	super();
	this.model = model;
	this.controller = controller;
	hero = controller.getSecondPlayerHero();
} 

private void setCanAttackMinions()
{
	canAttackMinions = new ArrayList<Minion>();
	for (Minion minion : hero.getField())
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
		for (Minion minion : controller.getFirstPlayerHero().getField())
		{
			if(minion.isTaunt())
				attackedMinions.add(minion);
		}
	}
	else {
		for (Minion minion : controller.getFirstPlayerHero().getField())
		{
				attackedMinions.add(minion);
		}
	}
	
}


private boolean OpponenthasTaunt()
{
    	for (Minion minion : controller.getFirstPlayerHero().getField())
		{
			if(minion.isTaunt())
				return true;
		}
    	
    	return false;
}

private void generateAllPossibleMinionAttacks()
{
	allPossibleMinonAttacks = new ArrayList<MinionMove>();
	attackedMinions = controller.getFirstPlayerHero().getField();
	for (Minion minion : canAttackMinions)
	{
		for (Minion minion2 : attackedMinions)
		{
			allPossibleMinonAttacks.add(new MinionMove(minion,minion2));
		}
		allPossibleMinonAttacks.add(new MinionMove(minion, controller.getFirstPlayerHero()));
	}
	
}

private void attackWithMinion(MinionMove move)
{
	if(move.isMinionAttack())
	{
		try
		{
			hero.attackWithMinion(move.getAttackingMinion(), move.getAttackedMinion());
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
			hero.attackWithMinion(move.getAttackingMinion(), move.getAttackedHero());
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
	for (Card card :hero.getHand())
	{
		if(card instanceof Spell)
		{
			Spell spell = (Spell) card;
			if(card instanceof MinionTargetSpell || card instanceof LeechingSpell)
				if(!isPositive(spell))
					for (Minion minion : controller.getSecondPlayerHero().getField())
						allPossibleSpellMoves.add(new SpellMove((Spell) card, minion));
				else 
					for(Minion minion : hero.getField())
						 allPossibleSpellMoves.add(new SpellMove(spell, minion));
			else {
				allPossibleSpellMoves.add(new SpellMove((Spell) card));
			}
		}
	}
}

private void playSpellMove(SpellMove move)
{
	Spell spell = move.getSpell();
	if(hero.getCurrentManaCrystals()< spell.getManaCost())
		return;
	if(move.isAttackingMinionSpell())
	{
		
		if(spell instanceof MinionTargetSpell)
		{
			try
			{
				hero.castSpell((MinionTargetSpell)spell, move.getAttackedMinion());
			} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e)
			{
				e.printStackTrace();
			}
		}
		
		if(spell instanceof LeechingSpell)
		{
			try
			{
				hero.castSpell((LeechingSpell)spell, move.getAttackedMinion());
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
				hero.castSpell((AOESpell)spell, controller.getSecondPlayerHero().getField());
			} catch (NotYourTurnException | NotEnoughManaException e)
			{
				e.printStackTrace();
			}
		}
		
		if(spell instanceof FieldSpell)
		{
			try
			{
				hero.castSpell((FieldSpell)spell);
			} catch (NotYourTurnException | NotEnoughManaException e)
			{
				e.printStackTrace();
			}
			
			
		}
		if(spell instanceof HeroTargetSpell)
		{
			;
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

private void playCards()
{
	ArrayList<Object> hand = new ArrayList<Object>();
	
	for(Card card : hero.getHand())
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
		  if(hero.getCurrentManaCrystals()>= minion.getManaCost())
			try
			{
				hero.playMinion((Minion) card);
				
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
	setCanAttackMinions();
	generateAllPossibleMinionAttacks();
	generateAllPossibleSpellMoves();
	attackWithAllTheMinions();
	playCards();
	try
	{
		hero.endTurn();
		controller.updateView();
	} catch (FullHandException | CloneNotSupportedException e)
	{
		e.printStackTrace();
	}
}
  
 
 
 
}



