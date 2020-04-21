package controller;

import javax.swing.JButton;

import model.cards.spells.Spell;
import model.heroes.Hero;

public class SpellButton extends JButton {
	private Controller controller;
	private Spell spell;
	private Hero hero;
	public SpellButton(Controller controller,Spell spell,Hero hero) {
		super();
		this.setText(spell.getName());
		this.spell = spell;
		this.hero = hero;
		this.controller = controller;
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public Spell getSpell() {
		return spell;
	}
	public void setSpell(Spell spell) {
		this.spell = spell;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	

}
