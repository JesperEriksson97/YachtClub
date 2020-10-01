package controller;

import java.util.ArrayList;

import db.MembersDatabase;
import model.Member;

/*TODO list:
 * 
 * 1. We have an unnecessary dependency to the Member class by calling mb.getMember() maybe move this to a function
 * inside the MemberDatbase class called "AddBoatToMember(id)" or similar to avoid the dependency.
 * 
 * 
 * 
*/
public class MemberController {
	
	private MembersDatabase mb = new MembersDatabase(); // By following MVC this could easily be 

	public void addMember(String name, String personalNumber) {
		mb.addMember(new Member(name, personalNumber));
	}

	public ArrayList<String> getVerboseArray() {
		return mb.getVerboseList();
		
	}

	public ArrayList<String> getCompactArray() {
		return mb.getCompactList();
	}
	
	public ArrayList<String> getListOfOwnedBoats(int id) {
		return mb.getMemberById(id).getStringOfOwnedBoats();
	}

	public void editMember(int id, String newName, String newPR) {
		Member editedMember = new Member(newName, newPR);
		Member memberToChange = mb.getMemberById(id);
		mb.editMember(memberToChange, editedMember);	
	}

	public void deleteMember(int id) {
		mb.deleteMemberById(id);
	}

	public void registerBoatToMember(int memberId, String type, String length, String name) {
		mb.addBoatToMember(memberId, type, length, name);
	}
	
	public void editBoatOfMember(int memberId, int boatNr, String type, String length, String name) {
		mb.editBoatOfMember(memberId, boatNr, type, length, name);
	}
	
	public void removeBoatFromMember(int memberId, int boatNr) {
		mb.removeBoatOfMember(memberId, boatNr);
	}


}
