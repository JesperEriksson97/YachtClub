package view;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Remove member view.
 * @author Jesper Eriksson
 */

public class RemoveMemberView extends View{
	
	/**
	 * Print function.
	 */
	
	public void print() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Remove A Member ====");
		
		super.printCompactListOfMembers(mc.getMemberArray());
		// this.printCompactList(mc.getCompactArray());
		System.out.println("Choose the ID of the member you want to delete: ");
		
		try {
			int id = scan.nextInt();
			mc.deleteMember(id);
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid user input... try again");
			this.print();
		}
	}
}
