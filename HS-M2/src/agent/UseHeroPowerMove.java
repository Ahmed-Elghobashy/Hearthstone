package agent;

import model.cards.minions.Minion;
import model.cards.spells.HeroTargetSpell;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Priest;

public class UseHeroPowerMove extends HearthstoneMove implements ManaCostingMove
{
	private Hero usingHero;
	private final int manaCost = 2;
	private Hero attackedHero;
	private Minion attackedMinion;
	private boolean isAttackingHero;
	private boolean isAttackingMinion;
	
	
	
	
	public UseHeroPowerMove(Hero usingHero,Hero attackedHero)
	{
		this.usingHero=usingHero;
		this.attackedHero=attackedHero;
		setHeroPowerType(usingHero);
	}
	
	
	public UseHeroPowerMove(Hero usingHero)
	{
		super();
		this.usingHero = usingHero;
		setHeroPowerType(usingHero);
	}
	
	
	public UseHeroPowerMove(Hero usingHero, Minion attackedMinion)
	{
		super();
		this.usingHero = usingHero;
		this.attackedMinion = attackedMinion;
	}


	private void setHeroPowerType(Hero hero)
	{
		if(hero instanceof Mage || hero instanceof Priest)
		{
			isAttackingHero=true;
			isAttackingMinion=true;
		}
		
	}
	
	public Hero getAttackedHero()
	{
		return attackedHero;
	}

	public void setAttackedHero(Hero attackedHero)
	{
		this.attackedHero = attackedHero;
	}

	public Minion getAttackedMinion()
	{
		return attackedMinion;
	}

	public void setAttackedMinion(Minion attackedMinion)
	{
		this.attackedMinion = attackedMinion;
	}


	public boolean isAttackingHero()
	{
		return isAttackingHero;
	}

	public boolean isAttackingMinion()
	{
		return isAttackingMinion;
	}

	public int getManaCost()
	{
		return 2;
	}
	
}
