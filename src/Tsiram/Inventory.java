package Tsiram;
import java.util.*;
//Kulvinder Lotay and Scott Jaffe

//Inventory class extends ArrayList of Inventory items
public class Inventory extends ArrayList<InventoryItem>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8260358044702477284L;

	//Find item using item name
	private InventoryItem findItem(String itemName){
		for (int i = 0, len = this.size(); i < len; i++){
			if (this.get(i).item.name.equals(itemName)){
				return this.get(i);
			}
		}
		return null;
	}
	
	//Find item using identifier
	private InventoryItem getItem(Item item){
		return findItem(item.name);
	}
	
	//Check if inventory has particular item, identified using item name
	public Boolean hasItem(String itemName){
		Boolean hasItem = new Boolean(false);
		if (findItem(itemName) != null){
			hasItem = true;
		}
		return hasItem;
	}
	
	//Edit inventory method. Particular procedures to be used identified using type argument (add/remove etc.).
	private void editInventory(Item item, Integer num, Integer type){
		InventoryItem invItem = getItem(item);
		
		switch (type){
		case 0:
			if (invItem == null){
				this.add(new InventoryItem(item, num));
			}
			else {
				invItem.quantity += num;
			}
			break;
		case 1:
			if (invItem != null){
				invItem.quantity -= num;
				if (invItem.quantity <= 0){
					this.remove(invItem);
				}
			}
			break;
		}
	}
	
	public void addItem(Item item, Integer num){
		editInventory(item, num, 0);		
	}
	
	public void removeItem(Item item, Integer num){
		editInventory(item, num, 1);
	}
	
	//Returns an Integer containing the total usable items in inventory
	public Integer usableItems(){
		Integer count = new Integer(0);
		for (int i = 0, len = this.size(); i < len; i++){
			if (this.get(i).item.usable){
				count++;
			}
		}
		return count;
	}
	
	//Returns usable inventory items as String
	public String toString(){
		String str = new String("");
		for (int i = 0, len = this.size(); i < len; i++){
			if (this.get(i).item.usable){
				str += this.get(i).item.name + " x " +  this.get(i).quantity + "\n";
			}
		}
		return str;
	}
}
