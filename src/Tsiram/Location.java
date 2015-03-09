package Tsiram;

public class Location {
	
	private int visited = 0;
	
	private String name;
	
	private String desc0;
	
	private String desc1;
	
	private String desc2;
	
	private String message;
	
	private String[] desc = {desc0, desc1, desc2};
	
	private boolean locked = false;
	
	private Integer[] nav_array = new Integer[4];
	
	public Inventory inventory = new Inventory();
	
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
		
		return this.desc[current_desc];
	};
	
	public String getMessage(){
		return message;
	}
	
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
