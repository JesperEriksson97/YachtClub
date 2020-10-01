package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.MemberController;

public class ListMemberView {

	MemberController mc = new MemberController();
	
	public void printOptions() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("==== Choose list format ====");
		System.out.println("1. Verbose List");
		System.out.println("2. Compact List");
		int choice = 0;
		
		try {
			choice = scan.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Invalid choice... try again");
			printOptions();
		}
		
		if(choice == 1) {
			printVerboseList(mc .getVerboseArray());
		} else if (choice == 2) {
			printCompactList(mc.getCompactArray());
		} else {
			System.err.println("Invalid choice... try again");
			printOptions();
		}
	}
	
	private void printVerboseList(ArrayList<String> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
	private void printCompactList(ArrayList<String> arr) {
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
	
}
