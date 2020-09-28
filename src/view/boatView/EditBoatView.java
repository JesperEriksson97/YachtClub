package view.boatView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

public class EditBoatView {
	MemberController mc = new MemberController();
	
	public void printEditBoatView() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Edit a boat ====");
		
		printCompactList(mc.getCompactArray());
		
		System.out.println("Choose the ID of the owner which boat you want to edit: ");
		int id = 0;
		int boatId;
		try {
			id = scan.nextInt();
			System.out.println(mc.getListOfOwnedBoats(id).size());
			this.printListOfBoatsAvailable(mc.getListOfOwnedBoats(id));
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... try again");
			this.printEditBoatView();
		}
		
		System.out.println("Chose which boat you want to edit by entering the list number: ");
		try {
			// TODO insert some security checks.
			boatId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter new information. If you want to keep the old values just enter the same value as before.");
			System.out.println("Enter boat type(yacht, speedboat etc.): ");
			String type = scan.nextLine();
			System.out.println("Enter boat length(meters): ");
			String length = scan.nextLine();
			System.out.println("Enter boat name: ");
			String name = scan.nextLine();
			
			mc.editBoatOfMember(id, boatId, type, length, name);
			scan.close();
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... try again");
			this.printEditBoatView();
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
