package agent;

import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Polymorph;
import model.cards.spells.SealOfChampions;
import model.cards.spells.Spell;
import model.heroes.Hero;

public class SpellMove extends HearthstoneMove
{
  private Spell spell;
  private Minion attackedMinion;
  private Hero attackedHero;
  private boolean attackingMinionSpell;
  private int manaCost;
  
  

public SpellMove(Spell spell)
{
	super();
	this.spell = spell;
	manaCost=spell.getManaCost();
}
public SpellMove(Spell spell, Minion attackedMinion)
{
	super();
	this.spell = spell;
	this.attackedMinion = attackedMinion;
	manaCost=spell.getManaCost();
	attackingMinionSpell=true;
}




public boolean isPositive(Spell spell)
{
	if(spell instanceof MinionTargetSpell || spell instanceof LeechingSpell)
	{
		if(spell instanceof SealOfChampions)
			return true;
	}
	return false;
}
public Spell getSpell()
{
	return spell;
}
public void setSpell(Spell spell)
{
	this.spell = spell;
}
public Minion getAttackedMinion()
{
	return attackedMinion;
}
public void setAttackedMinion(Minion attackedMinion)
{
	this.attackedMinion = attackedMinion;
}
public Hero getAttackedHero()
{
	return attackedHero;
}
public void setAttackedHero(Hero attackedHero)
{
	this.attackedHero = attackedHero;
}
public int getManaCost()
{
	return manaCost;
}
public void setManaCost(int manaCost)
{
	this.manaCost = manaCost;
}
public void setAttackingMinionSpell(boolean attackingMinionSpell)
{
	this.attackingMinionSpell = attackingMinionSpell;
}
public boolean isAttackingMinionSpell()
{
	return attackingMinionSpell;
}
  
}
