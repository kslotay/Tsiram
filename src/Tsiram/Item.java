package Tsiram;
//Kulvinder Lotay and Scott Jaffe

public class Item {

	//public values for generic Item class
	
	public String name;
	
	public String desc;
	
	public Integer value;
	
	public Boolean usable;
	
	public Item(String name, String desc, Integer value, Boolean usable){
		this.name = name;
		this.desc = desc;
		this.value = value;
		this.usable = usable;
	}
}
