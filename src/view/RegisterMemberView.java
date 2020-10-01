package view;

import java.util.Scanner;

import controller.MemberController;


public class RegisterMemberView {

	private MemberController mc = new MemberController();
	
	/**
	 * Prints a form that lets a user register a member.
	 * 
	 */
	public void printRegisterMemberView() {
		
		Scanner scan = new Scanner(System.in);
		String name;
		String personalNumber;
		
		System.out.println("==== Add Member ====");
		System.out.println("");
		
		System.out.println("Full Name: ");
		name = scan.nextLine();
		
		if(name.matches("[a-zA-Z]+")) {
		
			System.out.println("Personal number[YYMMDDXXXX]: ");
			personalNumber = scan.nextLine();
			
			if(personalNumber.length() != 10) {
				System.err.println("ERROR: Enter a 10 digit personalnumber in the [YYMMDDXXXX] format.");
				this.printRegisterMemberView();
			} else {
				try {
					Long.parseLong(personalNumber);
					mc.addMember(name, personalNumber);
					// scan.close();
				} catch(NumberFormatException ex) {
					System.err.println("ERROR: Enter a 10 digit personalnumber in the [YYMMDDXXXX] format.");
					this.printRegisterMemberView();
				}
			}
		
		} else {
			System.err.println("ERROR: A name can only contains letters.");
			this.printRegisterMemberView();
		}
		
	}
}
