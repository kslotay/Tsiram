package Tsiram;

public class Player extends Person{

	public Attribute xp = new Attribute(0);
	
	public Attribute mana;
	
	public Player(String name, Integer location, Integer health, Integer mana) {
		super(name, location, health);
		this.mana = new Attribute(mana);
	}
}
