package view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Edit member view.
 * @author Jesper Eriksson
 *
 */

public class EditMemberView extends View{

	/**
	 * Print function.
	 */
	
	public void print() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Edit Member ====");
		System.out.println("Choose a member to edit.");
		super.printCompactListOfMembers(mc.getMemberArray());
		System.out.println("Enter the ID of the Member you want to edit: ");
		try {
			int id = scan.nextInt();
			;
			scan.nextLine();
			System.out.println("Enter new name: ");
			String newName = scan.nextLine();
			if(newName.matches("^[\\p{L} .'-]+$")) {
				System.out.println("Enter new personal number: ");
				String newPR = scan.nextLine();
				
				try {
					Long.parseLong(newPR);
					mc.editMember(id, newName, newPR);
				} catch(NumberFormatException ex) {
					System.err.println("ERROR: Wrong format should be 10 digits... exiting");
					super.exit();
				}
				
			} else {
				System.err.println("ERROR: Names can only contain letters... exiting");
				super.exit();
			}
			
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid user input... exiting");
			super.exit();
		}
		
	}
}
