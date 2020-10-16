package view.boatView;

import java.util.InputMismatchException;
import java.util.Scanner;

import view.View;

/**
 * Remove boat view.
 * @author Jesper Eriksson
 */

public class RemoveBoatView extends View {

	/**
	 * Print function.
	 */
	
	public void print() {
		System.out.println("==== Remove Boat ====");
		int id = 0;
		int boatNr = 0;
		Scanner scan = new Scanner(System.in);
		
		super.printCompactListOfMembers(mc.getMemberArray());
		System.out.println("Enter the ID of the owner which boat you want to edit: ");
		try {
			id = scan.nextInt();
			
			if(mc.getMemberById(id) == null) {
				System.err.println("ERROR: The Member entered does not exist... exiting");
				super.exit();
			} else if(mc.getAmountOfBoatsOfMember(id) != 0) {
				super.printListOfMembersOwnedBoats(mc.getMemberById(id));
				System.out.println("Enter the list number of the boat you want to delete: ");
				try {
					boatNr = scan.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("ERROR: Invalid choice... exiting");
					super.exit();
				}
				
				mc.removeBoatFromMember(id, boatNr);
			} else {
				System.err.println("ERROR: The ID of the member entered owns no boats.");
				super.exit();
			}
		} catch (InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... exiting");
			super.exit();
		}
		
		
	}
	
}
