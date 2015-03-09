/**
 * 
 */
package Tsiram;
import java.util.*;
import java.io.*;

/**
 * @author kslotay
 *
 */
public class Tsiram {
	
	public static ArrayList<Integer[]> mapArray = new ArrayList<Integer[]>();
	
	public static ArrayList<Location> locationArray = new ArrayList<Location>();
	
	//public static ArrayList<Command> commandsArray = new ArrayList<Command>();
	
	public static Player player1;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String response;
		Scanner scan = new Scanner(System.in);
		
		init_locations();
		init_items();
		init_game();
		
		do{
			drawMap();
			update_display("Your current Location is: " + locationArray.get(player1.location).getName() + "\n");
			update_display("Enter your next move: ");
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
	
	private static void init_locations() {
	
		Location laboratory0 = new Location("Laboratory");
		Location jaffe_center1 = new Location("S. Jaffe Center");
		Location president_office2 = new Location("President's Office");
		Location dining_hall3 = new Location("Dining Hall");
		Location courtyard4 = new Location("Courtyard");
		Location elevator_room5 = new Location("Elevator Room");
		Location throne_room6 = new Location("Throne Room");
		Location weapons_shop7 = new Location("Weapons Shop");
		Location magic_shop8 = new Location("Magic Shop");
		
		laboratory0.setLocDesc("","","");
		laboratory0.setMessage("");
		laboratory0.setNav(-1, 1, 3, -1);
		locationArray.add(laboratory0);
		
		jaffe_center1.setLocDesc("","","");
		jaffe_center1.setMessage("");
		jaffe_center1.setNav(-1, 2, 4, 0);
		locationArray.add(jaffe_center1);
		
		president_office2.setLocDesc("","","");
		president_office2.setMessage("");
		president_office2.setNav(-1, -1, 5, 1);
		locationArray.add(president_office2);
		
		dining_hall3.setLocDesc("","","");
		dining_hall3.setMessage("");
		dining_hall3.setNav(0, 4, 6, -1);
		locationArray.add(dining_hall3);
		
		courtyard4.setLocDesc("","","");
		courtyard4.setMessage("");
		courtyard4.setNav(1, 5, 7, 3);
		locationArray.add(courtyard4);
		
		elevator_room5.setLocDesc("","","");
		elevator_room5.setMessage("");
		elevator_room5.setNav(2, -1, 8, 4);
		locationArray.add(elevator_room5);
		
		throne_room6.setLocDesc("","","");
		throne_room6.setMessage("");
		throne_room6.setNav(3, 7, -1, -1);
		throne_room6.lock();
		locationArray.add(throne_room6);
		
		weapons_shop7.setLocDesc("","","");
		weapons_shop7.setMessage("");
		weapons_shop7.setNav(4, 8, -1, 6);
		locationArray.add(weapons_shop7);
		
		magic_shop8.setLocDesc("", "", "");
		magic_shop8.setMessage("");
		magic_shop8.setNav(5, -1, -1, 7);
		locationArray.add(magic_shop8);
		
		
		for (int i = 0, len = locationArray.size(); i < len; i++){
			mapArray.add(locationArray.get(i).getNav());
		}
		
	}
	
	private static void init_items() throws IOException {
		
		int loc_id, value, damage, ammo, quantity, cash_amount, coins_amount;
		String name, desc, code;
		Boolean usable, locked;
		Weapon wp;
		Money money;
		Coins coins;
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
				money = new Money();
				coins = new Coins();
				safe.addContents(money, cash_amount);
				safe.addContents(coins, coins_amount);
				locationArray.get(loc_id).inventory.addItem(safe, 1);
			}
			else if (item_type.equals("Coins")){
				loc_id = Integer.parseInt(fileScan.next());
				quantity = Integer.parseInt(fileScan.next());
				coins = new Coins();
				locationArray.get(loc_id).inventory.addItem(coins, quantity);
			}
		}
		
		fileScan.close();
		
	}

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
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |     X    |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|          | ELEVATOR |\n");
			update_display("|          |  YARD    |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|          |          |          |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		case 3:
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|          | ELEVATOR |\n");
			update_display("|    X     |  YARD    |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|          |          |          |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		case 4:
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|    X     | ELEVATOR |\n");
			update_display("|          |  YARD    |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|          |          |          |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		case 5:
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|          | ELEVATOR |\n");
			update_display("|          |  YARD    |    X     |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|          |          |          |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		case 6:
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|          | ELEVATOR |\n");
			update_display("|          |  YARD    |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|    X     |          |          |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		case 7:
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|          | ELEVATOR |\n");
			update_display("|          |  YARD    |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|          |    X     |          |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		case 8:
			update_display("___________ __________ __________\n");
			update_display("|          |          |          |\n");
			update_display("|          |  JAFFE   |          |\n");
			update_display("|   LAB    |          |  OFFICE  |\n");
			update_display("|          |  CENTER  |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|          |  COURT   |          |\n");
			update_display("|RESTAURANT|          | ELEVATOR |\n");
			update_display("|          |  YARD    |          |\n");
			update_display("|__________|__________|__________|\n");
			update_display("|          |          |          |\n");
			update_display("|  THRONE  |  WEAPONS |  MAGIC   |\n");
			update_display("|          |          |    X     |\n");
			update_display("|   ROOM   |  SHOP    |  SHOP    |\n");
			update_display("|__________|__________|__________|\n");
			break;
		}
	}
	
	private static Integer navigate(Integer current_loc, Integer dir){
		Integer dest = mapArray.get(current_loc)[dir];
		return dest;
	}
	
	private static void update_display(String str){
		System.out.print(str);
	}
}
