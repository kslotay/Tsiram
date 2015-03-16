package Tsiram;

public class Person {
	
	private String name;
	
	private Integer location;
	
	public Inventory inventory = new Inventory();
	
	public Attribute health;
	
	public Person(String name, Integer location, Integer health){
		this.name = name;
		this.location = location;
		this.health = new Attribute(health);
	}
	
	public String getName(){
		return name;
	}
	
	public Integer getLoc(){
		return location;
	}
	
	public void move(Integer location){
		this.location = location;
	}
	
	public String getInventory(){
		String str = new String();
		str += this.name + " has: \n" + this.inventory.toString();
		return str;
	}
}
