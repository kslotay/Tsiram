package Tsiram;
import java.util.*;

public class NPC extends Person{
	
	public String type;
	
	public Boolean met = false;
	
	public String desc;
	
	public String first_speech;
	
	private ArrayList<String> speech = new ArrayList<String>();
	
	public NPC(String name, Integer health, Integer location, String desc, String first_speech) {
		super(name, health, location);
		this.desc = desc;
		this.first_speech = first_speech;
	}
	
	public void addSpeech(String speech){
		this.speech.add(speech);
	}
	
	private String getSpeech(Integer num){
		return speech.get(num);
	}
	
	public String speak(){
		if (met == false){
			met = true;
			return first_speech;
		}
		else {
			return getSpeech(new Random().nextInt(speech.size()));
		}
	}
}
