package view.boatView;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

public class RegisterBoatView {
	
	MemberController mc = new MemberController();

	public void printRegisterBoatView() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Register Boat ====");
		printCompactList(mc.getCompactArray());
		System.out.println("Choose the ID of the Member that owns the boat: ");
		try {
			int memberId = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter type of boat: ");
			String type = scan.nextLine();
			System.out.println("Enter length of boat(meters): ");
			String length = scan.nextLine();
			System.out.println("Enter name of boat: ");
			String name = scan.nextLine();
			mc.registerBoatToMember(memberId, type, length, name);
			scan.close();
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid choice... try again");
			printRegisterBoatView();
		}
		
		
		
	}
	
	private void printCompactList(ArrayList<String> arr) {
		System.out.println(arr.size());
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}

}
