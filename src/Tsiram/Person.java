package Tsiram;

public class Person {
	
	public String name;
	
	public Inventory inventory;
	
	public Integer location;
	
	public Attribute health;
	
	public Person(String name, Integer location, Integer health){
		this.name = name;
		this.location = location;
		this.health = new Attribute(health);
	}
	
	public String getName() {
		return name;
	}
}
