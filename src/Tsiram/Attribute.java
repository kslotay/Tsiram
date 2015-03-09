package Tsiram;

public class Attribute {
	
	private Integer value;

	private Integer max;
	
	public Attribute(Integer value){
		this.value = value;
		this.max = value;
	}
	
	public Integer get(){
		return value;
	}
	
	public void add(Integer amount){
		Integer newval = (value + amount);
		
		if (newval > max){
			value = max;
		}
		else {
			value = newval;
		}
	}
	
	public void remove(Integer amount){
		Integer newval = (value - amount);
		
		if (newval < 0){
			value = 0;
		}
		else {
			value = newval;
		}
	}
	
	public void new_max(Integer value){
		this.max = value;
	}
}
