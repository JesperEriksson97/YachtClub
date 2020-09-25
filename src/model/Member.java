package model;

import java.util.ArrayList;

public class Member {
	private int id;
	private String name;
	private String personalNumber;
	private ArrayList<Boat> ownedBoats;
	
	/**
	 * Constructor.
	 * @param name The members name
	 * @param personalNumber The members personalNumber
	 */
	
	public Member(String name, String personalNumber) {
		this.name = name;
		this.personalNumber = personalNumber;
		this.ownedBoats = new ArrayList<Boat>();
	}
	
	/**
	 * Empty Constructor.
	 */
	public Member() {
		this.ownedBoats = new ArrayList<Boat>();
	}
	
	/**
	 * Adds a boat to the ownedBoats array.
	 * @param boat
	 */
	public void addBoat(Boat boat) {
		this.ownedBoats.add(boat);
	}
	
	/**
	 * Removes a boat from then ownedBoats array.
	 * @param boat
	 * @return boolean
	 */
	public boolean removeBoat(Boat boat) {
		return this.ownedBoats.remove(boat);
	}
	
	/**
	 * toString function.
	 * @return String
	 */
	//TODO THIS SHOULD BE MOVED TO VIEW CONTROLLER
	public ArrayList<String> getStringOfOwnedBoats() {
		
		ArrayList<String> res = new ArrayList<String>();
		if (this.ownedBoats.size() != 0) {
			for (int i = 0; i < this.ownedBoats.size(); i++) {
				res.add(this.ownedBoats.get(i).toString());
			}
			
			return res;
		}
		
		return null;
	}
	
	public String toString() {
		return "ID: " + getId() + ", Name: " + getName() + ", PRNR: " + getPersonalNumber();
	}
	
	// --- Getters and Setters ---
	
	public int getId() {
		return id;
	}
	public void setId(int size) {
		this.id = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public ArrayList<Boat> getOwnedBoats() {
		return ownedBoats;
	}

	public void setOwnedBoats(ArrayList<Boat> ownedBoats) {
		this.ownedBoats = ownedBoats;
	}

}
