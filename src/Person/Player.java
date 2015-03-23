package Person;

import Tsiram.Attribute;

//Player class
public class Player extends Person{

	//Player XP
	public Attribute xp = new Attribute(0);
	
	//Player mana
	public Attribute mana;
	
	//Player contructor
	public Player(String name, Integer location, Integer health, Integer mana) {
		super(name, location, health);
		this.mana = new Attribute(mana);
	}
}
