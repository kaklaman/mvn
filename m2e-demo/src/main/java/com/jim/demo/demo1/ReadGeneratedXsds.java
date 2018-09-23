package com.jim.demo.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadGeneratedXsds {

	public static void main(String[] args) {
		ReadGeneratedXsds d = new ReadGeneratedXsds();
		d.execute(args);

	}

	private void execute(String[] args) {
		String directory;
		if (args != null && args.length > 0) {
			for (String ss1 : args) {
				System.out.println(ss1);
			}
			directory = args[0];
		} else
			directory = "xsds";
		System.out.println("checkpoint1");
		File d = new File(directory);
		URL u = Thread.currentThread().getContextClassLoader().getResource(directory);
		try {
			System.out.println("URL" + u);
			System.out.println("\n isDirectory? " + d.isDirectory() + "\n files:" + d.list());
			handleXsd(directory,d.list());
		} catch (Throwable e) {
			System.out.println(d + "\n isDirectoryNull? " + d == null);
			e.printStackTrace();
		}
		
		

	}

	private void handleXsd(String directory, String[] files) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		for(String file:files) {
			if(file.endsWith(".xsd")) {
				Document doc = db.parse(new FileInputStream(new File(directory+"/"+file)));
				System.out.println(doc.toString());
			}
			
			
		}
		
		
/*
		// Given the id, go to correct place in XSD to get all the parameters
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList result = (NodeList) xpath.compile(getExpression(id)).evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < result.getLength(); i++) {
			Element e = (Element) result.item(i);
			System.out.println(e.getAttribute("name") + " = " + e.getNodeValue());
		}
*/
	}

}
