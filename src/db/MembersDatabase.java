package db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import model.Member;

public class MembersDatabase {
	private DatabaseXMLParser xmlp = new DatabaseXMLParser();
	private int totalMembers;
	private HashSet<Member> db;
	
	/**
	 * Constructor. Checks if a Database is already existent otherwise creates a new.
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public MembersDatabase() {
		try {
			db = xmlp.readFromXMLFile();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			db = new HashSet<Member>();
		}

		try {
			totalMembers = xmlp.getTotalAmountOfMembersRegistered();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			totalMembers = 0;
		}
	}
	
	/**
	 * Adds a Member to the members HashSet.
	 * @param m A Member to be added.
	 */
	public boolean addMember(Member member) {
		// TODO Maybe refactor this part into a separate function.
		for (Iterator<Member> it = db.iterator(); it.hasNext(); ) {
			Member m = it.next();
			if (m.getPersonalNumber().contentEquals(member.getPersonalNumber())) {
				System.err.println("ERROR: ID: " + m.getPersonalNumber() + " is already an registered user");
				return false;
			}
		}
		
		this.totalMembers++;
		member.setId(this.totalMembers);
		db.add(member);
		this.saveDatabase();
		return true;
		
	}
	
	/**
	 * Gets a member by id
	 * @param id
	 */
	public Member getMemberById(int id) {
		for (Iterator<Member> it = db.iterator(); it.hasNext(); ) {
			Member m = it.next();
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Edits a members values.
	 * @param oldMember The member to be changed.
	 * @param changedMember The member which values should be inserted.
	 */
	public void editMember(Member oldMember, Member changedMember) {
		try {
			oldMember = getMemberById(oldMember.getId()); // TODO This line might unnecessary.
			
			oldMember.setName(changedMember.getName());
			boolean isRegistered = false;
			for (Iterator<Member> it = db.iterator(); it.hasNext(); ) {
				Member m = it.next();
				if (m.getPersonalNumber().contentEquals(changedMember.getPersonalNumber())) {
					isRegistered = true;
				}
			}
			
			if(!isRegistered) {
				oldMember.setPersonalNumber(changedMember.getPersonalNumber());
				this.saveDatabase();
			} else {
				System.err.println("ERROR: ID: " + changedMember.getPersonalNumber() + " is already an registered user");
			}
			
		} catch (NullPointerException e) {
			System.err.println("ERROR: Couldn't find a member with that ID");
		}
	}
	
	/**
	 * Deletes a member.
	 * @param member
	 */
	public void deleteMemberById(int id) {
		
		if(getMemberById(id) != null) {
			db.remove(getMemberById(id));
			this.saveDatabase();
		} else {
			System.err.println("ERROR: Couldn't find a member with that ID");
		}
		
	}
	
	/**
	 * Adds a boat to a specific member.
	 * @param id
	 * @param type
	 * @param length
	 * @param name
	 */
	public void addBoatToMember(int id, String type, String length, String name) {
		if(this.getMemberById(id) != null) {
			this.getMemberById(id).addBoat(type, length, name);
			this.saveDatabase();
		} else {
			System.err.println("ERROR: No member with that id...");
		}
	}
	
	/**
	 * Edits a boat of a specific member.
	 * @param memberId
	 * @param boatNr
	 * @param type
	 * @param length
	 * @param name
	 */
	public void editBoatOfMember(int memberId, int boatNr, String type, String length, String name) {
		if(this.getMemberById(memberId) != null) {
			this.getMemberById(memberId).editBoat(boatNr, type, length, name);
			this.saveDatabase();
		} else {
			System.err.println("ERROR: No member with that id...");
		}
	}
	
	/**
	 * Removes a boat from a specific member.
	 * @param boatNr 
	 * @param memberId 
	 */
	public void removeBoatOfMember(int memberId, int boatNr) {
		Member m = this.getMemberById(memberId);
		if(m != null) {
			m.removeBoat(m.getOwnedBoats().get(boatNr)); // Is this a dependency to Boat? Or just to the members array.
		} else {
			System.err.println("ERROR: No member with that id...");
		}
		this.saveDatabase();
	}
	
	/**
	 * Saves the Database to a XML File. Returns true if successful or false if not.
	 * @return boolean
	 */
	public boolean saveDatabase() {
		try {
			xmlp.writeToXMLFile(db, totalMembers);
			return reloadDatabase(); 
		} catch (ParserConfigurationException | TransformerException e) {
			System.err.println(e.toString());
			return false;
		}
	}
	
	/**
	 * Reloads the database. Returns true if successful or false if not.
	 * @return boolean
	 */
	private boolean reloadDatabase() {
			try {
				this.db = xmlp.readFromXMLFile();
				return true;
			} catch (SAXException | IOException | ParserConfigurationException e) {
				System.err.println(e.toString());
				return false;
			}
	}
	
	/**
	 * Returns the size of the database HashSet.
	 * @return int
	 */
	public int size() {
		return db.size();
	}

	/**
	 * Returns a ArrayList<String> representation of the database in a verbose format.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getVerboseList() {
		
		ArrayList<String> returnArr = new ArrayList<String>();
		for (Iterator<Member> it = db.iterator(); it.hasNext(); ) {
			Member m = it.next();
			if(m.getStringOfOwnedBoats() != null) {
				String toAdd = m.toString() + " Owned boats: "+ m.getStringOfOwnedBoats();
				returnArr.add(toAdd);
			} else {
				String toAdd = m.toString() + ", 0 Boats Owned.";
				returnArr.add(toAdd);
			}
		}
		
		return returnArr;
	}

	/**
	 * Returns a ArrayList<String> representation of the database in a compact format.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getCompactList() {
		ArrayList<String> returnArr = new ArrayList<String>();
		for (Iterator<Member> it = db.iterator(); it.hasNext(); ) {
			Member m = it.next();
	
			String toAdd = m.toString() + ", " + m.getOwnedBoats().size() + " Boats Owned.";
			returnArr.add(toAdd);
		}
		
		return returnArr;
	}
	
}
