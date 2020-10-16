package db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Boat;
import model.Member;

/**
 * Responsible for writing and reading data concerning Members to a .xml file.
 * @author Jesper Eriksson
 *
 */
public class DatabaseXMLParser {
	

	/** 
	 * Takes a HashSet of any types and writes it to a xml file.
	 *  
	 * @param members
	 * @param size
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 */
	public void writeToXMLFile(HashSet<Member> members, int totalSizeOfMembers) throws ParserConfigurationException, TransformerException {
		System.out.println("Write to database....");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Iterator<Member> it = members.iterator();
		
		Element e = doc.createElement("root");
		doc.appendChild(e);
		
		while(it.hasNext()) {
			Member m = it.next();
			
			Element member = doc.createElement("Member");
			e.appendChild(member);
			
			Attr attr = doc.createAttribute("Id");
			attr.setValue("" + m.getId());
			member.setAttributeNode(attr);
			
			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode(m.getName()));
			member.appendChild(name);
			
			Element prnr = doc.createElement("PersonalNumber");
			prnr.appendChild(doc.createTextNode(m.getPersonalNumber()));
			member.appendChild(prnr);
			
			Element boats = doc.createElement("Boats");
			
			
			for(Boat b : m.getOwnedBoats()) {
				Element ownedBoat = doc.createElement("OwnedBoat");
				
				Element length = doc.createElement("Length");
				length.appendChild(doc.createTextNode(b.getLength()));
				ownedBoat.appendChild(length);
				
				Element type = doc.createElement("Type");
				type.appendChild(doc.createTextNode(b.getType()));
				ownedBoat.appendChild(type);
				
				Element boatName = doc.createElement("BoatName");
				boatName.appendChild(doc.createTextNode(b.getName()));
				ownedBoat.appendChild(boatName);
				
				boats.appendChild(ownedBoat);
				
				
			}
			
			member.appendChild(boats);

		}
			
			
		Element size = doc.createElement("size");
		size.appendChild(doc.createTextNode("" + totalSizeOfMembers));
		e.appendChild(size);
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult sr = new StreamResult(new File("members.xml"));
		transformer.transform(source, sr);
		
		System.out.println("Done");
	}
	
	/**
	 * Reads from XML file "members.xml" if existent. Returns a HashSet with the read Members from the XML file.
	 * @return HashSet<Member>
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	
	public HashSet<Member> readFromXMLFile() throws SAXException, IOException, ParserConfigurationException {
		File xmlFile = new File("members.xml");
		HashSet<Member> returnArr = new HashSet<Member>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.parse(xmlFile);
		
		NodeList list = d.getElementsByTagName("Member");
		
		for(int i = 0; i< list.getLength(); i++) {
			Node node = list.item(i);
			Member m = new Member();
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element) node;
				m.setId(Integer.parseInt(el.getAttribute("Id")));
				m.setName(el.getElementsByTagName("Name").item(0).getTextContent());
				m.setPersonalNumber(el.getElementsByTagName("PersonalNumber").item(0).getTextContent());
				
				ArrayList<Boat> boats = new ArrayList<Boat>();
				
				if(el.getElementsByTagName("OwnedBoat").getLength() != 0) {
					for(int j = 0; j < el.getElementsByTagName("OwnedBoat").getLength(); j++) {
						Boat b = new Boat(); // Here is the dependency to Boat
						b.setLength(el.getElementsByTagName("Length").item(j).getTextContent());
						b.setName(el.getElementsByTagName("BoatName").item(j).getTextContent());
						b.setType(el.getElementsByTagName("Type").item(j).getTextContent());
						boats.add(b);
					}
				}
				
				m.setOwnedBoats(boats);
				
				if(!returnArr.contains(m)) returnArr.add(m);

			}
		}
		
		return returnArr;
	}
	
	/**
	 * By saving the amount of total registered members we can create unique IDs-.
	 * However this will be limited to the size of Integer but it will be sufficent for this task.
	 * 
	 * @return Integer
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	
	public int getTotalAmountOfMembersRegistered() throws SAXException, IOException, ParserConfigurationException {
		File xmlFile = new File("members.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.parse(xmlFile);
			
		int size = Integer.parseInt(d.getElementsByTagName("size").item(0).getTextContent());
		return size;
	}
}


