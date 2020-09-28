package main;


import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import view.MainMenu;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
	
		
		MainMenu mm = new MainMenu();
		mm.printMainMenu();
		
	}

}
