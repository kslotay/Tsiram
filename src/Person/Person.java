package Person;

import Inventory.Inventory;
import Tsiram.Attribute;

//Basic person class
public class Person {
	
	//Person name
	private String name;
	
	//Person location (location index)
	private Integer location;
	
	//Person inventory
	public Inventory inventory = new Inventory();
	
	//Person health
	public Attribute health;
	
	//Constructor
	public Person(String name, Integer location, Integer health){
		this.name = name;
		this.location = location;
		this.health = new Attribute(health);
	}
	
	//Return Person name
	public String getName(){
		return name;
	}
	
	//Return person location
	public Integer getLoc(){
		return location;
	}
	
	//Move person to new location
	public void move(Integer location){
		this.location = location;
	}
	
	//Return person inventory as string
	public String getInventory(){
		String str = new String();
		str += this.name + " has: \n" + this.inventory.toString();
		return str;
	}
}
