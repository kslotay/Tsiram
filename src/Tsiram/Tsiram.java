/**
 * 
 */
package Tsiram;
import java.util.*;
import java.io.*;

import Tsiram.Items.Item;
import Tsiram.Items.Safe;
import Tsiram.Items.Weapon;
import Tsiram.Person.Player;

/*
 * Kulvinder Lotay and Scott Jaffe
 * Software Development 1
 * Project 2.5
 */

public class Tsiram {
	
	//ArrayList that holds all instantiated Location objects
	public static ArrayList<Location> locationArray = new ArrayList<Location>();
	
	//Creates player object
	public static Player player1;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//Holds player response
		String response;
		//Scanner for user input
		Scanner scan = new Scanner(System.in);
		
		//Initialize game parameters
		init_locations();
		init_items();
		init_game();
		
		//Handle player responses, running applicable methods for the different commands
		do{
			
			//Displays the current location
			update_display("Your current Location is: " + locationArray.get(player1.getLoc()).getName() + "\n\n");
			
			//Displays current location description, along with any items that may be in that location
			update_display(locationArray.get(player1.getLoc()).currentDesc());
			
			//Request player input
			update_display("\n\nEnter your next move: ");
			
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
			else if (response.equalsIgnoreCase("take") || response.equalsIgnoreCase("t")){
				//Check if location has usable items (comparing usable boolean for items)
				if(locationArray.get(player1.getLoc()).inventory.usableItems() > 0){
					//If there are usable items, take 1 of each (if more than 1)
					update_display("\nYou have taken:\n");
					for (int i = 0, len = locationArray.get(player1.getLoc()).inventory.size(); i < len; i++){
						if (locationArray.get(player1.getLoc()).inventory.get(i).item.usable){
							//Add item to player inventory
							player1.inventory.addItem(locationArray.get(player1.getLoc()).inventory.get(i).item, 1);
							update_display(locationArray.get(player1.getLoc()).inventory.get(i).item.name + " x 1\n");
							//Remove item from location inventory
							locationArray.get(player1.getLoc()).inventory.removeItem(locationArray.get(player1.getLoc()).inventory.get(i).item, 1);
						}
					}
				}
			}
			//Display player inventory
			else if (response.equalsIgnoreCase("inventory") || response.equalsIgnoreCase("i")){
				if (!player1.inventory.isEmpty()){
					update_display(player1.getInventory());
				}
				else {
					update_display("\nYou have no items in inventory");
				}
			}
			//Show map if player has map in inventory
			else if (response.equalsIgnoreCase("m") || response.equalsIgnoreCase("map")){
				if (player1.inventory.hasItem("Map")){
					drawMap();
				}
				else {
					update_display("You do not have the map in your inventory!");
				}
			}
			else if (response.equalsIgnoreCase("help")){
				
			}
			//Quit game
			else if (response.equalsIgnoreCase("q")){
				update_display("\nThanks for playing!\n");
			}
		} while (!response.equalsIgnoreCase("q"));
		
		scan.close();
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
		
		//Temporary variables for items data
		int loc_id, value, damage, ammo, quantity, cash_amount, coins_amount;
		String name, desc, code;
		Boolean usable, locked;
		Weapon wp;
		Item money = new Item("Cash", "US Dollars", 10, true);
		Item coins = new Item("Gold Coin(s)", "24 karat gold coins. Looks like you just struck... gold.", 40, true);
		Safe safe;
		Item item;
		
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
		
		fileScan.close();
		
	}

	//Initialize game
	private static void init_game() {	
				
		//Initialize player
		player1 = new Player("Player1", 0, 1000, 100);
		player1.xp.new_max(100);
		
		//Intro display messages
		update_display("Welcome to Tsiram!\n\n");
		update_display("Move using n, e, s, w. To quit the game, type q at any time.\n\n");
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
	
	//private static void take(String args){
		//
	//}
	
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
				update_display("You cannot go north!\n");
				break;
			case 1:
				update_display("You cannot go east!\n");
				break;
			case 2:
				update_display("You cannot go south!\n");
				break;
			case 3:
				update_display("You cannot go west!\n");
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
