package controller;

import java.util.ArrayList;

import db.MembersDatabase;
import model.Member;

/**
 * Handles delegation of system operations by commands from the View layer.
 * @author Jesper Eriksson
 *
 */
public class MemberController {
	
	
	private MembersDatabase md = new MembersDatabase();

	/**
	 * Calls on md to add a Member.
	 * @param name
	 * @param personalNumber
	 */
	public void addMember(String name, String personalNumber) {
		md.addMember(new Member(name, personalNumber));
	}

	/**
	 * Calls on md to edit a Member.
	 * @param id
	 * @param newName
	 * @param newPR
	 */
	public void editMember(int id, String newName, String newPR) {
		Member editedMember = new Member(newName, newPR);
		Member memberToChange = md.getMemberById(id);
		md.editMember(memberToChange, editedMember);	
	}

	/**
	 * Calls on md to delete a Member.
	 * @param id
	 */
	public void deleteMember(int id) {
		md.deleteMember(md.getMemberById(id));
	}

	/**
	 * Calls on md to register a boat to a Member.
	 * @param memberId
	 * @param type
	 * @param length
	 * @param name
	 */
	public void registerBoatToMember(int memberId, String type, String length, String name) {
		md.addBoatToMember(md.getMemberById(memberId), type, length, name);
	}
	
	/**
	 * Calls on md to edit a boat of a Member.
	 * @param memberId
	 * @param boatNr
	 * @param type
	 * @param length
	 * @param name
	 */
	public void editBoatOfMember(int memberId, int boatNr, String type, String length, String name) {
		md.editBoatOfMember(md.getMemberById(memberId), boatNr, type, length, name);
	}
	
	/**
	 * Calls on md to remove a boat from a Member.
	 * @param memberId
	 * @param boatNr
	 */
	public void removeBoatFromMember(int memberId, int boatNr) {
		md.removeBoatOfMember(md.getMemberById(memberId), boatNr);
	}

	/**
	 * Retrieves all Members in the database.
	 * @return
	 */
	public ArrayList<Member> getMemberArray() {
		return md.getAllMembers();
	}
	
	/**
	 * Calls on md to get a specific Member.
	 * @param id
	 * @return
	 */
	public Member getMemberById(int id) {
		return md.getMemberById(id);
	}
	
	/**
	 * Calls on md to get the amount of Boats a specific Member owns.
	 * @param id
	 * @return
	 */
	public int getAmountOfBoatsOfMember(int id) {
		return md.getMemberById(id).getOwnedBoats().size();
	}


}
