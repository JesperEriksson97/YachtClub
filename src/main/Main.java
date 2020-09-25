package main;


import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import db.DatabaseXMLParser;
import db.MembersDatabase;
import model.Boat;
import model.Member;
import view.MainMenu;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		/*System.out.println("Works");
		MembersDatabase mb = new MembersDatabase();
		
		Member m1 = new Member("Jesper Eriksson", "9705145433");
		Member m2 = new Member("Victor Eriksson", "9904023451");
		Member m3 = new Member("Björn Eriksson", "7210241234");
		
		Member m4 = new Member("Kicki Eriksson", "7308021234");
		
		/*mb.addMember(m4);
		mb.addMember(m2);
		mb.addMember(m3);
		mb.addMember(m1);*/
		
		MainMenu mm = new MainMenu();
		mm.printMainMenu();
		// Member createdMember = new Member("Jeppe Eriksson", "9705145433");
		
		
		// mb.editMember(mb.getMemberById(4), createdMember);
		// mb.deleteMemberById(4);
		
	
		// test.writeToXMLFile();
		// test.readFromXMLFile();
		// test.getTotalAmountOfMembersRegistered();
		
		
	}

}
