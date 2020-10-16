package db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import model.Member;

/**
 * Responsible for CRUD (Create Read Update Delete) functionality for Members.
 * @author Jesper Eriksson
 *
 */

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
			oldMember = getMemberById(oldMember.getId()); // TODO This line might be unnecessary.
			
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
	
	public void deleteMember(Member m) {
		
		if(m != null) {
			db.remove(m);
			this.saveDatabase();
		} else {
			System.err.println("ERROR: Couldn't find a member with that ID");
		}
		this.saveDatabase();
		
	}
	
	/**
	 * Adds a boat to a specific member.
	 * @param id
	 * @param type
	 * @param length
	 * @param name
	 */
	
	public void addBoatToMember(Member m, String type, String length, String name) {
		if(m != null) {
			m.addBoat(type, length, name);
			this.saveDatabase();
		} else {
			System.err.println("ERROR: No member with that id...");
		}
		this.saveDatabase();
	}
	
	/**
	 * Edits a boat of a specific member.
	 * @param memberId
	 * @param boatNr
	 * @param type
	 * @param length
	 * @param name
	 */
	
	public void editBoatOfMember(Member m, int boatNr, String type, String length, String name) {
		if(m != null) {
			m.editBoat(boatNr, type, length, name);
			this.saveDatabase();
		} else {
			System.err.println("ERROR: No member with that id...");
		}
		this.saveDatabase();
	}
	
	/**
	 * Removes a boat from a specific member.
	 * @param boatNr 
	 * @param memberId 
	 */
	public void removeBoatOfMember(Member m, int boatNr) {
		if(m != null) {
			m.removeBoat(m.getOwnedBoats().get(boatNr));
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
	
	public ArrayList<Member> getAllMembers() {
		
		ArrayList<Member> returnArr = new ArrayList<Member>();
		for (Iterator<Member> it = db.iterator(); it.hasNext(); ) {
			Member m = it.next();
			returnArr.add(m);
			
		}
		 
		return returnArr;
	}

}
