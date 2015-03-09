package Tsiram;
//Kulvinder Lotay and Scott Jaffe

public class Location {
	
	//Counter for location visits
	private int visited = 0;
	
	private String name;
	
	//Location descriptions
	private String desc0;
	private String desc1;
	private String desc2;
	
	private String message;
	
	private boolean locked = false;
	
	//Navigation array integer, called in main class
	private Integer[] nav_array = new Integer[4];
	
	//Inventory object - aggregation creates has-a relationship. Every location has an inventory.
	public Inventory inventory = new Inventory();
	
	//Constructor
	public Location(String name){
		this.name = name;
	}
	
	public void setLocDesc(String desc0, String desc1, String desc2) {
		this.desc0 = desc0;
		this.desc1 = desc1;
		this.desc2 = desc2;
	}
	
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
	
	public String getName() {
		return name;
	}
	
	public String currentDesc() {
		int current_desc;
		
		String[] desc = {desc0, desc1, desc2};
		
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
		
		return desc[current_desc];
	};
	
	public String getMessage(){
		return message;
	}
	
	//Returns instance's navigation array
	public Integer[] getNav(){
		return nav_array;
	}
	
	public Boolean isLocked() {
		return locked;
	}
	
	public void unlock(){
		locked = false;
	}
	
	public void lock(){
		locked = true;
	}
	
	public void visited(){
		visited++;
	}
}
