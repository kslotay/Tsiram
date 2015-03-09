package Tsiram;
import java.util.*;

public class Inventory extends ArrayList<InventoryItem>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8260358044702477284L;

	private InventoryItem findItem(String itemName){
		for (int i = 0, len = this.size(); i < len; i++){
			if (this.get(i).item.name.equals(itemName)){
				return this.get(i);
			}
		}
		return null;
	}
	
	private InventoryItem getItem(Item item){
		return findItem(item.name);
	}
	
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
	
}