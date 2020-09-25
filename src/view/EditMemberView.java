package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

public class EditMemberView {
	MemberController mc = new MemberController();	
	public void printEditMemberView() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Edit Member ====");
		System.out.println("Choose a member to edit.");
		printCompactList(mc.getCompactArray());
		System.out.println("Enter the ID of the Member you want to edit: ");
		try {
			int id = scan.nextInt();
			;
			scan.nextLine();
			System.out.println("Enter new name: ");
			String newName = scan.nextLine();
			if(newName.matches("[a-zA-Z]+")) {
				System.out.println("Enter new personal number: ");
				String newPR = scan.nextLine();
				
				try {
					Long.parseLong(newPR);
					mc.editMember(id, newName, newPR);
					// scan.close();
				} catch(NumberFormatException ex) {
					System.err.println("ERROR: Enter a 10 digit personalnumber in the [YYMMDDXXXX] format.");
					printEditMemberView();
				}
				
			} else {
				System.err.println("ERROR: Names can only contain letters... try again");
				printEditMemberView();
			}
			
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid user input... try again");
			printEditMemberView();
		}
		
	}
	
	private void printCompactList(ArrayList<String> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
}
