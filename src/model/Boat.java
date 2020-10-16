package model;

/**
 * Representation of a Boat.
 * @author Jesper Eriksson
 */

public class Boat {

	private String type;
	private String name;
	private String length;
	
	/**
	 * Constructor
	 * @param type Type of boat.
	 * @param name Name of the boat.
	 * @param length Length of the boat.
	 */
	public Boat(String type, String name, String length) {
		this.setType(type);
		this.setName(name);
		this.setLength(length);
	}
	
	/**
	 * Empty constructor.
	 */
	public Boat() {
		
	}
	
	// --- Getters and Setters ---

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
