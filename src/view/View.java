package view;

import java.util.ArrayList;

import controller.MemberController;
import model.Boat;
import model.Member;

/**
 * View super class.
 * 
 * @author Jesper Eriksson
 */

public class View {
	
	protected MemberController mc = new MemberController();
	
	/**
	 * Prints a verbose list of all Members in the database.
	 * @param arr
	 */
	
	protected void printVerboseListOfMembers(ArrayList<Member> arr) {
		String print = null;
		for(int i = 0; i < arr.size(); i++) {
			Member cm = arr.get(i);
			print = "ID: " + cm.getId() + ", Name: " + cm.getName() + ", PRNR: " + cm.getPersonalNumber() + ", Boats Owned: ";
			for(int j = 0; j < cm.getOwnedBoats().size(); j++) {
				Boat b = cm.getOwnedBoats().get(j);
				print += "[" + b.getType() + ", " + b.getName() + ", " + b.getLength() + " meters] ";
			}
			System.out.println(print);
			print = null;
		}
		
	}
	
	/**
	 * Prints a compact list of all Members in the database.
	 * @param arr
	 */
	
	protected void printCompactListOfMembers(ArrayList<Member> arr) {
		String print = null;
		for(int i = 0; i < arr.size(); i++) {
			Member cm = arr.get(i);
			print = "ID: " + cm.getId() + ", Name: " + cm.getName() + ", PRNR: " + cm.getPersonalNumber();
			
			System.out.println(print);
			print = null;
		}
	}
	
	
	/**
	 * Prints all the boats of a specific Member.
	 * @param m
	 */
	
	protected void printListOfMembersOwnedBoats(Member m) {
		String print = null;
		for(int j = 0; j < m.getOwnedBoats().size(); j++) {
			Boat b = m.getOwnedBoats().get(j);
			print = "[" + b.getType() + ", " + b.getName() + ", " + b.getLength() + " meters] ";
			System.out.println(print);
			print = null;
		}
	}
	
	/**
	 * Exiting the queue of events, leading back to main menu.
	 */
	
	public void exit() {
		System.out.println("Transfering back to main menu...");
	}
}
