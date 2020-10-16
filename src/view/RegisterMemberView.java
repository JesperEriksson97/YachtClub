package view;

import java.util.Scanner;

import controller.MemberController;


/**
 * Register Member view.
 * @author Jesper Eriksson
 *
 */
public class RegisterMemberView extends View{
	
	/**
	 * Prints a form that lets a user register a member.
	 */
	
	public void print() {
		
		Scanner scan = new Scanner(System.in);
		String name;
		String personalNumber;
		
		System.out.println("==== Add Member ====");
		System.out.println("");
		
		System.out.println("Full Name: ");
		name = scan.nextLine();
		
		if(name.matches("^[\\p{L} .'-]+$")) {
		
			System.out.println("Personal number[YYMMDDXXXX]: ");
			personalNumber = scan.nextLine();
			
			if(personalNumber.length() != 10) {
				System.err.println("ERROR: Enter a 10 digit personalnumber in the [YYMMDDXXXX] format... exiting");
				super.exit();
			} else {
				try {
					Long.parseLong(personalNumber);
					mc.addMember(name, personalNumber);
				} catch(NumberFormatException ex) {
					System.err.println("ERROR: Enter a 10 digit personalnumber in the [YYMMDDXXXX] format... exiting");
					super.exit();
				}
			}
		
		} else {
			System.err.println("ERROR: A name can only contains letters... exiting");
			super.exit();
		}
		
	}
}
