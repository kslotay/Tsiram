package Inventory;

import Tsiram.Items.Item;
/*
 * Kulvinder Lotay and Scott Jaffe
 * Software Development 1
 * Project 2.5
 */

//Inventory item class used as subclass for Inventory. The Inventory is an ArrayList of type InventoryItem, which contains an Item and Quantity.
public class InventoryItem {
	
	public Item item;
	
	public Integer quantity;
	
	public InventoryItem(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
}
