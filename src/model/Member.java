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
	public void addBoat(String type, String length, String name) {
		this.ownedBoats.add(new Boat(type, length, name));
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
	 * Edits a boat.
	 * @param boatNr the index number of the boat we want to edit.
	 * @param type new type of boat.
	 * @param length new length of boat.
	 * @param name new name of boat.
	 */
	public void editBoat(int boatNr, String type, String length, String name) {
		Boat boatToEdit = this.ownedBoats.get(boatNr);

		boatToEdit.setType(type);
		boatToEdit.setLength(length);
		boatToEdit.setName(name);
	}
	
	/**
	 * toString function.
	 * @return String
	 */
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
	
	/**
	 * toString() method, return a String representation of the Member object.
	 * @return String
	 */
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
