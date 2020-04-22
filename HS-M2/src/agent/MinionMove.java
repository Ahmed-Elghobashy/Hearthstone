package agent;

import controller.HeroButton;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class MinionMove extends HearthstoneMove
{
  private Minion attackingMinion;
  private Minion attackedMinion;
  private Hero attackedHero;
  private boolean minionAttack;
  
public boolean isMinionAttack()
{
	return attackedMinion!=null;
}

public MinionMove(Minion attackingMinion, Minion attackedMinion)
{
	super();
	this.attackingMinion = attackingMinion;
	this.attackedMinion = attackedMinion;
}

public MinionMove(Minion attackingMinion, Hero attackeHero)
{
	super();
	this.attackingMinion = attackingMinion;
	this.attackedHero = attackeHero;
}

public Minion getAttackingMinion()
{
	return attackingMinion;
}

public Minion getAttackedMinion()
{
	return attackedMinion;
}

public Hero getAttackedHero()
{
	return attackedHero;
}
  
  
}
