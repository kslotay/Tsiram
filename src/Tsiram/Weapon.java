package Tsiram;

public class Weapon extends Item{

	private Integer damage;
	private Attribute ammo;
	
	public Weapon(String name, String desc, Integer value, Integer damage, Integer ammo) {
		super(name, desc, value, true);
		this.damage = damage;
		this.ammo = new Attribute(ammo);
	}
	
	public Integer checkAmmo(){
		return this.ammo.get();
	}
	
	public Integer fireWeapon() {
		if(ammo.get() > 0){
			ammo.remove(1);
			return damage;
		}
		return null;
	}
	
}
