package main;


import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import view.MainMenu;
import view.View;

/**
 * Main class
 * @author Jesper Eriksson
 *
 */
public class Main {

	/**
	 * Main function.
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		MainMenu mm = new MainMenu();
		mm.printMainMenu();
		
		/*View v = new View();
		v.goToMainMenu();*/
	}

}
