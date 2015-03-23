package Tsiram.Items;

import Inventory.Inventory;

//Safe item, extends item class. Contains specific methods and variables needed for a 'Safe'
public class Safe extends Item{

	//Safe unlock code
	private String code;
	
	//Contains info about safe's current lock status
	private Boolean locked;
	
	//Safe can contain items such as coins etc. therefore needs an inventory
	private Inventory contents = new Inventory();
	
	//Safe constructor
	public Safe(String name, String desc, Integer value, String code, Boolean locked) {
		super(name, desc, value, false);
		this.code = code;
		this.locked = locked;
	}
	
	//Is the safe locked
	public Boolean isLocked(){
		return locked;
	}
	
	//Unlock the safe
	public void unlock(String current_code){
		if(current_code.equals(code)){
			locked = false;
		}
	}
	
	//Add items to safe, e.g. cash
	public void addContents(Item item, Integer amount){
		this.contents.addItem(item, amount);
	}
	
	//Remove items from safe
	public void removeContents(Item item, Integer amount){
		this.contents.removeItem(item, amount);
	}
}
