package view.boatView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

public class RemoveBoatView {
	
	MemberController mc = new MemberController();

	public void printRemoveBoatView() {
		System.out.println("==== Remove Boat ====");
		int id = 0;
		int boatNr = 0;
		Scanner scan = new Scanner(System.in);
		printCompactList(mc.getCompactArray());
		System.out.println("Enter the ID of the owner which boat you want to edit: ");
		try {
			id = scan.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... try again");
			this.printRemoveBoatView();
		}
		
		if(mc.getListOfOwnedBoats(id) != null) {
			printListOfBoatsAvailable(mc.getListOfOwnedBoats(id));
			System.out.println("Enter the list number of the boat you want to delete: ");
			try {
				boatNr = scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("ERROR: Invalid choice... try again");
				this.printRemoveBoatView();
			}
			
			mc.removeBoatFromMember(id, boatNr);
		} else {
			System.err.println("ERROR: The ID of the member entered owns no boats.");
			this.printRemoveBoatView();
		}
		
	}
	
	private void printListOfBoatsAvailable(ArrayList<String> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(i + ". " + arr.get(i));
		}
		
	}
	
	private void printCompactList(ArrayList<String> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}

}
