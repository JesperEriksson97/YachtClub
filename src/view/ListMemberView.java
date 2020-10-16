package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

/**
 * List member view
 * @author Jesper Eriksson
 *
 */

public class ListMemberView extends View {

	/**
	 * Print function.
	 */
	
	public void print() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("==== Choose list format ====");
		System.out.println("1. Verbose List");
		System.out.println("2. Compact List");
		int choice = 0;
		
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Invalid choice... try again");
			super.exit();
		}
		
		if(choice == 1) {
			super.printVerboseListOfMembers(mc.getMemberArray());
		} else if (choice == 2) {
			super.printCompactListOfMembers(mc.getMemberArray());
		} else {
			System.err.println("Invalid choice... exiting");
			super.exit();
		}
	}
	
}
