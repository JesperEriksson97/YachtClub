package controller;

import java.util.ArrayList;

import db.MembersDatabase;
import model.Member;

public class MemberController {
	
	MembersDatabase mb = new MembersDatabase(); // By following MVC this could easily be 

	public void addMember(String name, String personalNumber) {
		mb.addMember(new Member(name, personalNumber));
	}

	public ArrayList<String> getVerboseArray() {
		return mb.getVerboseList();
		
	}

	public ArrayList<String> getCompactArray() {
		return mb.getCompactList();
	}

	public void editMember(int id, String newName, String newPR) {
		Member editedMember = new Member(newName, newPR);
		Member memberToChange = mb.getMemberById(id);
		mb.editMember(memberToChange, editedMember);	
	}

	public void deleteMember(int id) {
		mb.deleteMemberById(id);
	}

	public void registerBoat(int memberId, String type, String length, String name) {
		// Here we should create a BoatDatabase that handles 
		
	}

}
