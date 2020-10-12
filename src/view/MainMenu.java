package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import view.boatView.EditBoatView;
import view.boatView.RegisterBoatView;
import view.boatView.RemoveBoatView;

public class MainMenu {
	RemoveBoatView rebv = new RemoveBoatView();
	EditBoatView ebv = new EditBoatView();
	RegisterBoatView rbv = new RegisterBoatView();
	EditMemberView emv = new EditMemberView();
	RegisterMemberView rmv = new RegisterMemberView();
	ListMemberView lmv = new ListMemberView();
	RemoveMemberView remv = new RemoveMemberView();
	
	public void printMainMenu() {	
		int c = 0;
		Scanner m_scan = new Scanner(System.in);
		while(true) {
			System.out.println("==== WELCOME TO THE YACHT CLUB ====");
			System.out.println("1. Register a Member");
			System.out.println("2. Remove a Member");
			System.out.println("3. Edit a Member");
			System.out.println("4. View Members");
			System.out.println("5. Register Boat");
			System.out.println("6. Edit Boat");
			System.out.println("7. Remove Boat");
			System.out.println("8. Exit");
			System.out.println("Choose a option: ");
			
			try {
				c = m_scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Invalid choice... try again");
				this.printMainMenu(); // Fixes the bug where enter "e" for example sets the System in to an infinite loop.
			}

			if(c == 1) {
				rmv.printRegisterMemberView();
			} else if (c == 2) {
				remv.printRemoveMemberView();
			} else if (c == 3) {
				emv.printEditMemberView();
			} else if (c == 4) {
				lmv.printOptions();
			} else if (c == 5) {
				rbv.printRegisterBoatView();
			} else if (c == 6) {
				ebv.printEditBoatView();
			} else if (c == 7) {
				rebv.printRemoveBoatView();
			} else if (c == 8) {
				System.out.println("Exiting... Good Bye!");
				System.exit(0);
			} else if (c != 0) {
				System.err.println("ERROR: Invalid choice... try again");
			}
			
			// scan.close();
		}
	}

}
