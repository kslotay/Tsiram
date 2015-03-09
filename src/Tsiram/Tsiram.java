/**
 * 
 */
package Tsiram;
import java.util.*;
import java.io.*;

/*
 * 03/09/2015
 * Kulvinder Lotay and Scott Jaffe
 * Software Development 1
 * Project 2
 */

public class Tsiram {
	
	//ArrayList that holds all Location navigation arrays
	
	public static ArrayList<Integer[]> mapArray = new ArrayList<Integer[]>();
	
	//ArrayList that holds all instantiated Location objects
	
	public static ArrayList<Location> locationArray = new ArrayList<Location>();
	
	//public static ArrayList<Command> commandsArray = new ArrayList<Command>();
	
	//starts player object
	public static Player player1;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//Holds player response
		String response;
		Scanner scan = new Scanner(System.in);
		
		//Initialize game parameters
		init_locations();
		init_items();
		init_game();
		
		//Handles player responses
		do{
			drawMap();
			update_display("Your current Location is: " + locationArray.get(player1.location).getName() + "\n");
			update_display(locationArray.get(player1.location).currentDesc());
			update_display("\n\nEnter your next move: ");
			response = scan.next();
			if (response.equalsIgnoreCase("n")){
				Integer newLoc = navigate(player1.location, 0);
				if(newLoc != -1){
					player1.location = newLoc;
				}
				else{
					update_display("You cannot go north!\n");
				}
			}
			else if (response.equalsIgnoreCase("e")){
				Integer newLoc = navigate(player1.location, 1);
				if(newLoc != -1){
					player1.location = newLoc;
				}
				else{
					update_display("You cannot go east!\n");
				}
			}
			else if (response.equalsIgnoreCase("s")){
				Integer newLoc = navigate(player1.location, 2);
				if(newLoc != -1){
					player1.location = newLoc;
				}
				else{
					update_display("You cannot go south!\n");
				}
			}
			else if (response.equalsIgnoreCase("w")){
				Integer newLoc = navigate(player1.location, 3);
				if(newLoc != -1){
					player1.location = newLoc;
				}
				else{
					update_display("You cannot go west!\n");
				}
			}
			else if (response.equalsIgnoreCase("q")){
				update_display("Thanks for playing!\n");
			}
		} while (!response.equalsIgnoreCase("q"));
		scan.close();
	}
	
	//Initialize Locations
	private static void init_locations() {
	
		//Instantiate location objects
		Location laboratory0 = new Location("Laboratory");
		Location jaffe_center1 = new Location("S. Jaffe Center");
		Location president_office2 = new Location("President's Office");
		Location dining_hall3 = new Location("Dining Hall");
		Location courtyard4 = new Location("Courtyard");
		Location elevator_room5 = new Location("Elevator Room");
		Location throne_room6 = new Location("Throne Room");
		Location weapons_shop7 = new Location("Weapons Shop");
		Location magic_shop8 = new Location("Magic Shop");
		
		laboratory0.setLocDesc("The lab is brightly lit, with a gleaming stainless steel table running nearly the length of the room.\nWhite cabinets line the walls, punctuated by floor-to-ceiling glass cases that display a combination of equipment.\nA fume hood lurks in one corner of the room. The table has two sinks and gas hook ups at the center.\nNothing else rests on its surface; the space is immaculate, and you know that there is a place for everything, and everything is in its place. ","","");
		laboratory0.setMessage("");
		laboratory0.setNav(-1, 1, 3, -1);
		locationArray.add(laboratory0);
		
		jaffe_center1.setLocDesc("There is no one here.","","");
		jaffe_center1.setMessage("");
		jaffe_center1.setNav(-1, 2, 4, 0);
		locationArray.add(jaffe_center1);
		
		president_office2.setLocDesc("THE BLOOD. WHY IS THERE SO MUCH BLOOD?!","A cherry desk that seems almost superfluous, since there's little to no paperwork to be seen. A deep burgundy leather desk chair, that reclines to an almost obscene angle.\n There are only two small table lamps - one near the door atop a wicker table that clashes with the rest of the room's executive motif, and another on the cherry wood desk with a long brass pull-cord.\n The absence of overhead light casts shadows into the corners, spilling under the desk and over top the framed black-and-white pictures on the wall.\n The deep purple curtains are drawn to one side, allowing the light from outside the second story window to paint the eastern wall.\n It highlights a lone filing cabinet, suspiciously locked and coated in dust.","");
		president_office2.setMessage("");
		president_office2.setNav(-1, -1, 5, 1);
		locationArray.add(president_office2);
		
		dining_hall3.setLocDesc("A huge hallway with long rows of stone tables leading to a raised dias where the king and nobles eat.\n The floors in between the tables are carved with the history of the fort as are the three stories of walls. In places on the walls windows break up the engravings and offer a viewpoint from various others areas.\n One of the long walls of the hall is a series of double doors leading to the storerooms and kitchens while both short ends are studded with doors leading into the corridors.\n The whole thing resembles a viking longhouse but carved out of stone in my mind.\n If I could place cooking pits and roaring fires I would.","","");
		dining_hall3.setMessage("");
		dining_hall3.setNav(0, 4, 6, -1);
		locationArray.add(dining_hall3);
		
		courtyard4.setLocDesc("Unclassifiable might be the best adjective to describe the central courtyard garden.\n True, it has many of the elements of a classic Santa Fe courtyard - heavily plastered walls with sensuous, rounded edges; deep, shady portals held up by peeled pine logs; a central fountain sparkling in the sun.\n Yet, somehow, Eden's garden doesn't come across as traditional. \nMaybe it's her restrained use of plants: Instead of the usual riot of annuals and perennials, Newman narrowed her plant palette to two pairs of gray-green 'Wichita Blue' junipers and a series of yellow 'Graham Thomas' roses.\n The result feels almost Tuscan or Provençal, and the paler-than-typical colors she's chosen for gravel, sandstone, and stucco reinforce this Mediterranean mood.","","");
		courtyard4.setMessage("");
		courtyard4.setNav(1, 5, 7, 3);
		locationArray.add(courtyard4);
		
		elevator_room5.setLocDesc("The elevator is broken.","","");
		elevator_room5.setMessage("");
		elevator_room5.setNav(2, -1, 8, 4);
		locationArray.add(elevator_room5);
		
		throne_room6.setLocDesc("As you walk into the palace throne room you can feel the power that emanates from the throne its self.\n The throne is a high back chair made of a deep dark oak finish with a red velvet cushion.\n The throne sit at the top of a three stepped platform which just adds to the Prestige of power that you feel.\n The rest of the room looks to be the same as the rest of the keep.\n High windows, many tapestries hang from the walls as well as a crackling fire to warm the room on those cold and dismal winter nights.","","");
		throne_room6.setMessage("");
		throne_room6.setNav(3, 7, -1, -1);
		throne_room6.lock();
		locationArray.add(throne_room6);
		
		weapons_shop7.setLocDesc("RPGs and Revolvers around you.","","");
		weapons_shop7.setMessage("");
		weapons_shop7.setNav(4, 8, -1, 6);
		locationArray.add(weapons_shop7);
		
		magic_shop8.setLocDesc("MAGIC", "", "");
		magic_shop8.setMessage("");
		magic_shop8.setNav(5, -1, -1, 7);
		locationArray.add(magic_shop8);
		
		
		//adds location navigation arrays to mapArray ArrayList
		for (int i = 0, len = locationArray.size(); i < len; i++){
			mapArray.add(locationArray.get(i).getNav());
		}
		
	}
	
	//Initialize game items
	private static void init_items() throws IOException {
		
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
				cash_amount =  Integer.parseInt(fileScan.next());
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
		}
		
		fileScan.close();
		
	}

	//Initialize game
	private static void init_game() {	
		
		/*Command north = new Command("north", "Move north.");
		Command east = new Command("east", "Move east.");
		Command south = new Command("south", "Move south.");
		Command west = new Command("west", "Move west.");
		commandsArray.add(north);
		commandsArray.add(east);
		commandsArray.add(south);
		commandsArray.add(west);*/
		
		player1 = new Player("Player1", 0, 1000, 100);
		
		update_display("Welcome to Tsiram!\n\n");
		update_display("Move using n, e, s, w. To quit the game, type q at any time\n\n");
	}
	
	private static void drawMap(){
		switch(player1.location){
		case 0:
			update_display(
			"___________ __________ __________\n"+
			"|          |          |          |\n" +
			"|          |  JAFFE   |          |\n" +
			"|   LAB    |          |  OFFICE  |\n" +
			"|    X     |  CENTER  |          |\n" +
			"|__________|__________|__________|\n" +
			"|          |          |          |\n" +
			"|          |  COURT   |          |\n" +
			"|RESTAURANT|          | ELEVATOR |\n" +
			"|          |  YARD    |          |\n" +
			"|__________|__________|__________|\n" +
			"|          |          |          |\n" +
			"|  THRONE  |  WEAPONS |  MAGIC   |\n" +
			"|          |          |          |\n" +
			"|   ROOM   |  SHOP    |  SHOP    |\n" +
			"|__________|__________|__________|\n");
			break;
		case 1:
			update_display(
			"___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |    X     |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|          |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |          |          |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 2:
			update_display(
			"___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |     X    |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|          |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |          |          |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 3:
			update_display
			("___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|    X     |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |          |          |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 4:
			update_display
			("___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|    X     | ELEVATOR |\n"+
			"|          |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |          |          |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 5:
			update_display
			("___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|          |  YARD    |    X     |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |          |          |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 6:
			update_display
			("___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|          |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|     X    |          |          |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 7:
			update_display
			("___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|          |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |    X     |          |\n"+
			"|   ROOM   |   SHOP   |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		case 8:
			update_display
			("___________ __________ __________\n"+
			"|          |          |          |\n"+
			"|          |  JAFFE   |          |\n"+
			"|   LAB    |          |  OFFICE  |\n"+
			"|          |  CENTER  |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|          |  COURT   |          |\n"+
			"|RESTAURANT|          | ELEVATOR |\n"+
			"|          |  YARD    |          |\n"+
			"|__________|__________|__________|\n"+
			"|          |          |          |\n"+
			"|  THRONE  |  WEAPONS |  MAGIC   |\n"+
			"|          |          |    X     |\n"+
			"|   ROOM   |  SHOP    |  SHOP    |\n"+
			"|__________|__________|__________|\n");
			break;
		}
	}
	
	//navigation method
	private static Integer navigate(Integer current_loc, Integer dir){
		Integer dest = mapArray.get(current_loc)[dir];
		return dest;
	}
	
	private static void update_display(String str){
		System.out.print(str);
	}
}
