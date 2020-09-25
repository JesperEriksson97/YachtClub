package controller;

import java.util.ArrayList;

import db.MembersDatabase;
import model.Member;

public class MemberController {
	
	MembersDatabase mb = new MembersDatabase();

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

}
