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
	DatabaseXMLParser xmlp = new DatabaseXMLParser();
	int totalMembers;
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
			oldMember = getMemberById(oldMember.getId()); // TODO This line might unneccessary.
			
			oldMember.setName(changedMember.getName());
			// oldMember.setOwnedBoats(changedMember.getOwnedBoats()); TODO maybe move this part to a separate boat database.
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
	 * Saves the Database to a XML File. Returns true if successful or false if not.
	 * @return boolean
	 */
	public boolean saveDatabase() {
		// Save the HashSet to a xml file.
		try {
			xmlp.writeToXMLFile(db, totalMembers);
			return true;
		} catch (ParserConfigurationException | TransformerException e) {
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
				String toAdd = m.toString() + m.getStringOfOwnedBoats();
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
