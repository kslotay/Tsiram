package Tsiram;

public class Player extends Person{

	public Integer xp;
	
	public Attribute mana;
	
	public Player(String name, Integer location, Integer health, Integer mana) {
		super(name, location, health);
		this.mana = new Attribute(mana);
	}
	
}
