package Tsiram;

public class Safe extends Item{

	private String code;
	
	public String current_code;
	
	private Boolean locked;
	
	private Inventory contents;
	
	public Safe(String name, String desc, Integer value, String code, Boolean locked) {
		super(name, desc, value, false);
		this.code = code;
		this.locked = locked;
	}
	
	public Boolean isLocked(){
		return locked;
	}
	
	public void unlock(String current_code){
		if(current_code.equals(code)){
			locked = false;
		}
	}
	
	public void addContents(Item item, Integer amount){
		this.contents.addItem(item, amount);
	}
	
	public void removeContents(Item item, Integer amount){
		this.contents.removeItem(item, amount);
	}
}
