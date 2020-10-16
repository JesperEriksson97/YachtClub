package view.boatView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;
import view.View;

/**
 * Edit boat view.
 * @author Jesper Eriksson
 *
 */
public class EditBoatView extends View {

	/**
	 * Print function.
	 */
	public void print() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Edit a boat ====");
		
		super.printCompactListOfMembers(mc.getMemberArray());
		
		System.out.println("Choose the ID of the owner which boat you want to edit: ");
		int id = 0;
		int boatId;
		try {
			id = scan.nextInt();
			if(mc.getMemberById(id) == null) {
				System.err.println("ERROR: The Member entered does not exist... exiting");
				super.exit();
			}
			else if(mc.getAmountOfBoatsOfMember(id) != 0) {
				super.printListOfMembersOwnedBoats(mc.getMemberById(id));
				
				System.out.println("Chose which boat you want to edit by entering the list number: ");
				try {
					boatId = scan.nextInt();
					scan.nextLine();
					System.out.println("Enter new information. If you want to keep the old values just enter the same value as before.");
					System.out.println("Enter boat type(yacht, speedboat etc.): ");
					String type = scan.nextLine();
					if(type.matches("^[\\p{L} .'-]+$")) {
						System.out.println("Enter boat length(meters): ");
						String length = scan.nextLine();
						System.out.println("Enter boat name: ");
						String name = scan.nextLine();
						if(name.matches("^[\\p{L} .'-]+$")) {
							mc.editBoatOfMember(id, boatId, type, length, name);
						} else {
							System.err.println("ERROR: A name can only contain letters... exiting");
							super.exit();
						}	
					} else {
						System.err.println("ERROR: A type can only contain letters... exiting");
						super.exit();
					}
				} catch(InputMismatchException e) {
					System.err.println("ERROR: Invalid choice... exiting");
					super.exit();
				}
			} else {
				System.err.println("ERROR: The Member has no boats... exiting");
				super.exit();
			}
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... exiting");
			super.exit();
		}
		
		
		
	}

}
