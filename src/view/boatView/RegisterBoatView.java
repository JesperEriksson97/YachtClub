package view.boatView;

import java.util.InputMismatchException;
import java.util.Scanner;

import view.View;


/**
 * Register Boat View.
 * @author Jesper Eriksson
 *
 */
public class RegisterBoatView extends View {

	/**
	 * Print function
	 */
	
	public void print() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Register Boat ====");
		super.printCompactListOfMembers(mc.getMemberArray());
		System.out.println("Choose the ID of the Member that owns the boat: ");
		try {
			try {
				int memberId = scan.nextInt();
				scan.nextLine();
				
				System.out.println("Enter type of boat: ");
				String type = scan.nextLine();
				if(type.matches("^[\\p{L} .'-]+$")) {
					System.out.println("Enter length of boat(meters): ");
					String length = scan.nextLine();
					System.out.println("Enter name of boat: ");
					String name = scan.nextLine();
					if(name.matches("^[\\p{L} .'-]+$")) {
						mc.registerBoatToMember(memberId, type, length, name);	
					} else {
						System.err.println("ERROR: Name can only contain letters... exiting");
						super.exit();
					}
				} else {
					System.err.println("ERROR: Boat type can only contain letters... exiting");
					super.exit();
				}
				
			} catch (NumberFormatException ex) {
				System.err.println("ERROR: Invalid input should be integer... exiting");
				super.exit();
			}
			
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... exiting");
			super.exit();
		}
	}

}
