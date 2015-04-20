/**
 * 
 */
package Tsiram;
import java.util.*;
import java.io.*;

import Inventory.InventoryItem;
import Items.Item;
import Items.Safe;
import Items.Weapon;
import Items.MagicItem;
import Person.Player;

/*
 * Kulvinder Lotay and Scott Jaffe
 * Software Development 1
 * Project 2.5
 */

public class Tsiram {
	
	//ArrayList that holds all instantiated Location objects
	public static ArrayList<Location> locationArray = new ArrayList<Location>();
	
	public static LinkedList<MagicItem> magicitems = new LinkedList<MagicItem>();
	
	//Creates player object
	public static Player player1;
	
	//User input scanner
	public static Scanner scan = new Scanner(System.in);
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Scanner delimScan = new Scanner(System.in);
		delimScan.useDelimiter("'");
		
		//Holds player response
		String response, response2, search_str;
		
		//Initialize game parameters
		init_locations();
		init_items();
		init_game();
		
		//Handle player responses, running applicable methods for the different commands
		do{
			
			//Displays the current location
			update_display("\n\n\nYour current Location is: " + locationArray.get(player1.getLoc()).getName() + "\n\n");
			
			//Displays current location description, along with any items that may be in that location
			update_display(locationArray.get(player1.getLoc()).currentDesc() + "\n");
			
			//Request player input
			if(player1.getLoc() == 8){
				update_display("Would you like to buy an item from the Magic Shop? (Y/X) ");
			}
			else {
				update_display("Enter your next move: ");
			}
			
			//Store player response in response string variable
			response = scan.next();
			
			if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("north")){
				navigate(0);
			}
			else if (response.equalsIgnoreCase("e") || response.equalsIgnoreCase("east")){
				navigate(1);
			}
			else if (response.equalsIgnoreCase("s") || response.equalsIgnoreCase("south")){
				navigate(2);
			}
			else if (response.equalsIgnoreCase("w") || response.equalsIgnoreCase("west")){
				navigate(3);
			}
			
			//Take command
			else if (response.equalsIgnoreCase("take") || response.equalsIgnoreCase("t")){
				//If location has usable items call take function, else display error message
				if (locationArray.get(player1.getLoc()).inventory.usableItems() > 0){
					cmdTake();
				}
				else {
					update_display("\nThere are no items to take.");
				}
			}
			
			//Display player inventory
			else if (response.equalsIgnoreCase("inventory") || response.equalsIgnoreCase("i")){
				if (!player1.inventory.isEmpty()){
					update_display(player1.getInventory());
				}
				else {
					update_display("\nYou have no items in inventory.");
				}
			}
			
			//Show map if player has map in inventory
			else if (response.equalsIgnoreCase("m") || response.equalsIgnoreCase("map")){
				if (player1.inventory.hasItem("Map")){
					drawMap();
				}
				else {
					update_display("\nYou do not have the map in your inventory!");
				}
			}
			
			//Help command
			else if (response.equalsIgnoreCase("help")){
				update_display("The help command is still being implemented. Contact kulvinder.lotay1@marist.edu for any inquiries.");
			}
			
			//If player responds yes while in magic shop
			else if (response.equalsIgnoreCase("y")){
				search_str = "";
				int min, max;
				min = magicitems.indexOf(magicitems.getFirst());
				max = magicitems.indexOf(magicitems.getLast());
				int index = min;
				Boolean found = false;
				
				update_display("Please enter the name of the item you would like to search for (within quotations e.g. 'rags'): ");
				search_str = delimScan.next();
								
				while((!found) && (index <= max)){
					found = magicitems.get(index).name.equalsIgnoreCase(search_str);
					index++;
				}
				
				if(found){
					update_display("Would you like to buy this item? " + magicitems.get(index - 1).name + " - " + magicitems.get(index - 1).value + " Gold  (Y/N) ");
					response2 = scan.next();
					if (response2.equalsIgnoreCase("y")){
						if (!(player1.inventory.findItem("Gold Coin(s)") == null)){
							if (player1.inventory.findItem("Gold Coin(s)").quantity >= magicitems.get(index).value){
								player1.inventory.findItem("Gold Coin(s)").quantity -= magicitems.get(index).value;
								player1.inventory.addItem(magicitems.get(index), 1);
								update_display("You have bought 1 x " + magicitems.get(index).name);
								magicitems.remove(index);
							}
						}
						else {
							update_display("You do not have enough gold to purchase this item!");
						}
					}
				}
				else{
					update_display("Item not found!");
				}
			}
			
			else if (response.equalsIgnoreCase("x")){
				update_display("Bye!");
			}
			
			//Quit game
			else if (response.equalsIgnoreCase("q")){
				update_display("\nThanks for playing!\n");
			}
			
			//Display error message for invalid commands
			else {
				update_display("\nPlease enter a valid command. Type help for more information.");
			}
		} while (!response.equalsIgnoreCase("q"));
		
		scan.close();
		delimScan.close();
	}
	
	//Initialize Locations
	private static void init_locations() throws IOException {
	
		//Locations.txt file template: Location_name/Message/Desc1/Desc2/Desc3/N/E/S/W/
		
		//Temporary variables for location data
		String name, msg;
		String[] desc = new String[3];
		Integer[] nav = new Integer[4];
		Location temp_loc;
		
		//Filescanner using appropriate delimiter for Locations.txt file
		Scanner fileScan = new Scanner(new File("Locations.txt"));
		fileScan.useDelimiter("/");
		
		while(fileScan.hasNext()){
			//Assign values to related temp variables
			name = fileScan.next();
			msg = fileScan.next();
			desc[0] = fileScan.next();
			desc[1] = fileScan.next();
			desc[2] = fileScan.next();
			nav[0] = Integer.parseInt(fileScan.next());
			nav[1] = Integer.parseInt(fileScan.next());
			nav[2] = Integer.parseInt(fileScan.next());
			nav[3] = Integer.parseInt(fileScan.next());
			temp_loc = new Location(name);
			temp_loc.setMessage(msg);
			temp_loc.setLocDesc(desc[0], desc[1], desc[2]);
			temp_loc.setNav(nav[0], nav[1], nav[2], nav[3]);
			//Add location to locationArray
			locationArray.add(temp_loc);
			fileScan.nextLine();
		}
		
		fileScan.close();
	}
	
	//Initialize game items
	private static void init_items() throws IOException {
		
		//Items.txt file template: Item_type/Location_id/Name/Description/Value/Usable/Quantity/
		//File Info: Usable items MUST all come before non-usable items or take command display message is broken
		
		//Temporary variables for items data
		int loc_id, value, damage, ammo, quantity, cash_amount, coins_amount;
		String name, desc, code;
		Boolean usable, locked;
		Weapon wp;
		Item money = new Item("Cash", "US Dollars", 10, true);
		Item coins = new Item("Gold Coin(s)", "24 karat gold coins. Looks like you just struck... gold.", 40, true);
		Safe safe;
		Item item;
		MagicItem magicitem;
		
		Scanner fileScan = new Scanner (new File("Items.txt"));
		fileScan.useDelimiter("/");
		
		while (fileScan.hasNext()) {
			String item_type = fileScan.next();
			
			//Instantiate items and add to appropriate location inventories
			if (item_type.equals("Item")){
				loc_id = Integer.parseInt(fileScan.next());
				name = fileScan.next();
				desc = fileScan.next();
				value = Integer.parseInt(fileScan.next());
				usable = Boolean.parseBoolean(fileScan.next());
				quantity = Integer.parseInt(fileScan.next());
				item = new Item(name, desc, value, usable);
				locationArray.get(loc_id).inventory.addItem(item, quantity);
			}
			else if (item_type.equals("Weapon")){
				loc_id = Integer.parseInt(fileScan.next());
				name = fileScan.next();
				desc = fileScan.next();
				value = Integer.parseInt(fileScan.next());
				damage = Integer.parseInt(fileScan.next());
				ammo = Integer.parseInt(fileScan.next());
				quantity = Integer.parseInt(fileScan.next());
				wp = new Weapon(name, desc, value, damage, ammo);
				locationArray.get(loc_id).inventory.addItem(wp, quantity);
			}
			else if (item_type.equals("Safe")){
				loc_id = Integer.parseInt(fileScan.next());
				name = fileScan.next();
				desc = fileScan.next();
				value = Integer.parseInt(fileScan.next());
				code = fileScan.next();
				locked = Boolean.parseBoolean(fileScan.next());
				cash_amount = Integer.parseInt(fileScan.next());
				coins_amount =  Integer.parseInt(fileScan.next());
				quantity =  Integer.parseInt(fileScan.next());
				safe = new Safe(name, desc, value, code, locked);
				safe.addContents(money, cash_amount);
				safe.addContents(coins, coins_amount);
				locationArray.get(loc_id).inventory.addItem(safe, 1);
			}
			else if (item_type.equals("Coins")){
				loc_id = Integer.parseInt(fileScan.next());
				quantity = Integer.parseInt(fileScan.next());
				locationArray.get(loc_id).inventory.addItem(coins, quantity);
			}
			
			fileScan.nextLine();
		}
		
		fileScan = new Scanner(new File("magicitems.txt"));
		
		while (fileScan.hasNext()) {
			name = fileScan.nextLine();
			value = (int)((Math.random() * 20) + 1);
			magicitem = new MagicItem(name, value);
			magicitems.add(magicitem);
		}
		
		fileScan.close();
	}

	//Initialize game
	private static void init_game() {	
				
		//Initialize player
		player1 = new Player("Player1", 0, 1000, 100);
		player1.xp.new_max(100);
		locationArray.get(player1.getLoc()).visited();
		
		//Intro display messages
		update_display("Welcome to Tsiram!\n\n");
		update_display("Move using n, e, s, w. Use t or take to take items from a location. To quit the game, type q.");
	}
	
	//Draw ASCII Map
	private static void drawMap(){
		update_display(" ____________________ ____________________ ____________________ \n");
		String name;
		Integer name_len;
		Integer space_len;
		
		for (int i = 0, len = locationArray.size(); i < len; i++){
			name_len = locationArray.get(i).getName().length();
			space_len = 20 - name_len;
			
			//Current player location marked with (X)
			if (i == player1.getLoc()){
				if (space_len >= 3){
					name = locationArray.get(i).getName() + "(X)";
					space_len -= 3;
				}
				else {
					//Location names that are longer than available spaces are shortened. "--" appended at end.
					name = locationArray.get(i).getName().substring(0, 12) + "-- (X)";
				}
			}
			else {			
				if (space_len >= 0){
					name = locationArray.get(i).getName();
				}
				else {
					name = locationArray.get(i).getName().substring(0, 18) + "--";
				}
			}
			
			//Display location name in caps
			update_display("|" + name.toUpperCase());
			for (int x = 0; x < space_len; x++){
				update_display(" ");
			}
			
			//Final columns have specific draw instructions
			switch (i){
			case 2:
			case 5:
			case 8:
				update_display("|\n");
				for (int x = 0; x < 4; x++){
					//Final row contains bottom border
					if (x == 3){
						update_display("|____________________|____________________|____________________|\n");
					}
					else {
						update_display("|                    |                    |                    |\n");
					}
				}
				break;
			}
		}
	}
	
	//Take command function
	private static void cmdTake(){
		//Holder variables
		InventoryItem inv_item;
		String response;
		
		//Check if location has usable items (comparing usable boolean for items)
		update_display("\nWhich item would you like to take?\n");
		for (int i = 0, n = 1, len = locationArray.get(player1.getLoc()).inventory.size(); i < len; i++, n++){
			//List usable items
			if(locationArray.get(player1.getLoc()).inventory.get(i).item.usable){
				update_display(n + ". " + locationArray.get(player1.getLoc()).inventory.get(i).item.name);
				
				//If at last item, ask for response
				if (!(i == (locationArray.get(player1.getLoc()).inventory.usableItems() - 1))){
					update_display("\n");
				}
				else {
					update_display("\n\nType n, where n is the item number: ");
				}
			}
		}
			
		//Hold user response in 'response' variable
		response = scan.next();
		//Try to add item to player inventory, and remove from location inventory
		//If response provides a wrong index value causing an IndexOutOfBoundsException, display error message
		try {
			inv_item = locationArray.get(player1.getLoc()).inventory.get((Integer.parseInt(response) - 1));
			player1.inventory.addItem(inv_item.item, inv_item.quantity);
			update_display("You have taken " + inv_item.quantity +  " x " + inv_item.item.name + ".");
			if (inv_item.item.name.equals("Map")){
				update_display("\n\nYou have the in-game map! You can access it by typing m or map.");
			}
			locationArray.get(player1.getLoc()).inventory.removeItem(inv_item.item, inv_item.quantity);
		} catch ( IndexOutOfBoundsException e ) {
		    update_display("Item is not available at this location!");
		}
	}
	
	private static void navigate(Integer dir){
		//Get new location index using current player location and direction
		Integer newLoc = new Integer(getNewLoc(player1.getLoc(), dir));
		//Check if that particular navigation is possible
		if(newLoc != -1){
			//Use move method from Person class to assign new player location
			player1.move(newLoc);
			//Increment visited integer for that location
			locationArray.get(player1.getLoc()).visited();
		}
		else{
			//If action is not possible, display error message
			switch(dir){
			case 0:
				update_display("You cannot go north!");
				break;
			case 1:
				update_display("You cannot go east!");
				break;
			case 2:
				update_display("You cannot go south!");
				break;
			case 3:
				update_display("You cannot go west!");
				break;
			}
		}
	}
	
	//Navigation method, returns -1 for navigation actions that are not permitted
	private static Integer getNewLoc(Integer current_loc, Integer dir){
		//Uses location navigation data from the current location navigation array
		Integer dest = locationArray.get(current_loc).getNav()[dir];
		return dest;
	}
	
	//Display method
	private static void update_display(String str){
		//Checks for string length. If it is greater than 100 characters, it breaks it down into multiple lines and displays it as such
		if (str.length() > 100){
			System.out.print(str.substring(0, 100) + "-" + "\n");
			if (str.length() > 200) {
				System.out.print(str.substring(100, 200) + "-" + "\n");
				if (str.length() > 300) {
					System.out.print(str.substring(200, 300) + "-" + "\n");
					if (str.length() > 400){
						System.out.print(str.substring(300, 400) + "-" + "\n");
						if (str.length() > 500){
							System.out.print(str.substring(400, 500) + "-" + "\n");
							if (str.length() > 600){
								System.out.print(str.substring(500, 600) + "-" + "\n");
								if (str.length() > 700){
									System.out.print(str.substring(600, 700) + "-" + "\n");
									System.out.print(str.substring(700));
								}
								else {
									System.out.print(str.substring(600));
								}
							}
							else {
								System.out.print(str.substring(500));
							}
						}
						else {
							System.out.print(str.substring(400));
						}
					}
					else {
						System.out.print(str.substring(300));
					}
				}
				else {
					System.out.print(str.substring(200));
				}
			}
			else {
				System.out.print(str.substring(100));
			}
		}
		else {
			System.out.print(str);
		}
	}
}
