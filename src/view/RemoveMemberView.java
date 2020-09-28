package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

public class RemoveMemberView {

	MemberController mc = new MemberController();
	
	public void printRemoveMemberView() {
		Scanner scan = new Scanner(System.in);
		System.out.println("==== Remove A Member ====");
		this.printCompactList(mc.getCompactArray());
		System.out.println("Choose the ID of the member you want to delete: ");
		
		try {
			int id = scan.nextInt();
			mc.deleteMember(id);
		} catch(InputMismatchException e) {
			System.err.println("ERROR: Invalid user input... try again");
			printRemoveMemberView();
		}
		
		
	}
	
	// TODO Refactor this function into a helper function, used in multiple views.
	private void printCompactList(ArrayList<String> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}

}
