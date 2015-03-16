package Tsiram;
//Kulvinder Lotay and Scott Jaffe

public class Location {
	
	//Counter for location visits
	private int visited = 0;
	
	//Location name
	private String name;
	
	//Location descriptions
	private String[] desc = new String[3];
	
	//Location message, e.g. for particular event that may take place during gameplay
	private String message;
	
	//Location can be locked
	private boolean locked = false;
	
	//Navigation array integer, called in main class
	private Integer[] nav_array = new Integer[4];
	
	//Inventory object - aggregation creates has-a relationship. Every location has an inventory.
	public Inventory inventory = new Inventory();
	
	//Constructor
	public Location(String name){
		this.name = name;
	}
	
	//Set location descriptions
	public void setLocDesc(String desc0, String desc1, String desc2) {
		this.desc[0] = desc0;
		this.desc[1] = desc1;
		this.desc[2] = desc2;
	}
	
	//Set Location description
	public void setMessage(String msg){
		message = msg;
	}
	
	//Set navigation array values for north, south, east, west.
	public void setNav(Integer n, Integer e, Integer s, Integer w){
		Integer[] nav = new Integer[]{n, e, s, w};
		
		for (int i = 0; i < 4; i++){
			nav_array[i] = nav[i]; 
		}
	}
	
	//Return location name
	public String getName() {
		return name;
	}
	
	//Return current description according to visited counter and location inventory
	public String currentDesc() {
		int current_desc;
		String msg = new String();
		
		switch(this.visited) {
		case 0:
			current_desc = 0;
			break;
		case 1:
			current_desc = 1;
			break;
		default:
			current_desc = 2;
		}
		
		if(inventory.usableItems() > 0){
			msg = "\n\nThe location contains:\n";
			msg += inventory.toString();
		}
		
		return desc[current_desc] + msg;
	};
	
	//Return location message
	public String getMessage(){
		return message;
	}
	
	//Return location's specific navigation array
	public Integer[] getNav(){
		return nav_array;
	}
	
	//Check if location is locked
	public Boolean isLocked() {
		return locked;
	}
	
	//Unlock location
	public void unlock(){
		locked = false;
	}
	
	//Lock location
	public void lock(){
		locked = true;
	}
	
	//Increment location visited counter
	public void visited(){
		visited++;
	}
}
