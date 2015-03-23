package Tsiram.Person;
import java.util.*;

//Non-Player Characters, extends Person class
public class NPC extends Person{
	
	//Identify NPC type, e.g. Shopkeeper etc.
	public String type;
	
	//Has player met NPC?
	public Boolean met = false;
	
	//NPC Description
	public String desc;
	
	//First speech when player meets NPC for first time
	private String first_speech;
	
	//Hold other speeches
	private ArrayList<String> speech = new ArrayList<String>();
	
	//Constructor
	public NPC(String name, Integer health, Integer location, String desc, String first_speech) {
		super(name, health, location);
		this.desc = desc;
		this.first_speech = first_speech;
	}
	
	//Add a speech
	public void addSpeech(String speech){
		this.speech.add(speech);
	}
	
	//Return Speech
	private String getSpeech(Integer num){
		return speech.get(num);
	}
	
	//NPC speak action, first speech if player meets NPC for the first time, random speech from speech array for all other times
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
