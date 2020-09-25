package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
	EditMemberView emv = new EditMemberView();
	RegisterMemberView rmv = new RegisterMemberView();
	ListMemberView lmv = new ListMemberView();
	
	public void printMainMenu() {	
		while(true) {
			int c = 0;
			Scanner scan = new Scanner(System.in);
			System.out.println("==== WELCOME TO THE YACHT CLUB ====");
			System.out.println("1. Register a Member");
			System.out.println("2. Remove a Member");
			System.out.println("3. Edit a Member");
			System.out.println("4. View Members");
			System.out.println("5. Exit");
			System.out.println("Choose a option: ");
			
			try {
				c = scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Invalid choice... try again");
			}

			if(c == 1) {
				rmv.printRegisterMemberView();
			} else if (c == 2) {
				System.out.println("Moving user to removing page");
			} else if (c == 3) {
				emv.printEditMemberView();
			} else if (c == 4) {
				lmv.printOptions();
			} else if (c == 5) {
				System.out.println("Exiting... Good Bye!");
				scan.close();
				System.exit(0);
			} else if (c != 0) {
				System.err.println("Invalid choice... try again");
			}
			
			// scan.close();
		}
	}

}
