package Items;

import Tsiram.Attribute;

//Weapon class extends Item class
public class Weapon extends Item{
	
	//Weapon damage and ammo info
	private Integer damage;
	private Attribute ammo;
	
	//Constructor
	public Weapon(String name, String desc, Integer value, Integer damage, Integer ammo) {
		super(name, desc, value, true);
		this.damage = damage;
		this.ammo = new Attribute(ammo);
	}
	
	//Check weapon ammo
	public Integer checkAmmo(){
		return this.ammo.get();
	}
	
	//Fire weapon, return damage
	public Integer fireWeapon() {
		if(ammo.get() > 0){
			ammo.remove(1);
			return damage;
		}
		return null;
	}
	
}
