package tools.xmljsonconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 *  This is the main file
 */

public class xjconverter 
{
    public static void main( String[] args ) throws IOException
    { 	
    	initialInput();
    }
    
    private static void initialInput() throws IOException
    {
    	// Flag if the input is a valid file
    	boolean validateInput = true;
    	// Read the file scanner
    	Scanner scan = new Scanner(System.in);
    	
    	// Will keep asking for a valid file
    	while(validateInput) {		
    		System.out.println( "> Paste your file path here or 'x' to exit: "); 
    		String fileName = scan.nextLine();
         
	        // In case user wants to quit
	        if(fileName.equals("x")) {   
	        	System.out.println("> Good bye");
	        	scan.close();
	        	System.exit(0);
	        }
	        
	        // Else verify we have a valid file type
	        String typeOfFile = FileCheck.getExtension(fileName);
	        
	        // What type of file we have?
            if(typeOfFile.equals(".xml")) {
            	// Get the file name only no extension
	          	String nameOfFile = FileCheck.getFileName(fileName);
	          	System.out.println("> \"" + nameOfFile + "\" XML file converted to JSON: ");
	          	// Change flag
	          	validateInput = false;	
	          	
	          	// Parse content
	          	String fileContent = readFile(fileName); 
	          	//System.out.println(fileContent);
	          	String jsonString = XmlToJson.xml2json(fileContent);
	          	System.out.println(jsonString);
	          	afterOptions(jsonString);
            }
            else if(typeOfFile.equals(".json")) {
            	// Get the file name only no extension
	          	String nameOfFile = FileCheck.getFileName(fileName);
            	System.out.println("> \"" + nameOfFile + "\" JSON converted to XML: ");
	          	// Change flag
	          	validateInput = false;
	          	
	          	// Parse content
	          	String fileContent = readFile(fileName); 
	          	//System.out.println(fileContent);
	          	String xmlString = JsonToXml.json2xml(fileContent);
	          	System.out.println(xmlString);
	          	afterOptions(xmlString);
           } else {
        	   System.out.println("> File extension is needed.");
           }         	
    	}
    }   
    
    private static String readFile(String fileName) throws IOException
    {
    	// Create file object
		File file = new File(fileName);
		
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line, fileContent = "";

        while((line = reader.readLine()) != null) {
        	//System.out.println(line);
        	fileContent = fileContent.concat(line);
        	//System.out.println(line);
        }
        reader.close();
        
        return fileContent;
    }
    
    private static void afterOptions(String resultString) throws IOException {
    	System.out.println("Type 'r' to return to main menu");
    	System.out.println("or to save file enter path: ");
    	Scanner scan = new Scanner(System.in);
    	String option = "";
    	Boolean confirmation = false;
    	
    	while(!confirmation) {
    		option = scan.nextLine().trim();
    		if(option.equals("r")) {
    			confirmation = true;
    			initialInput();
    		} else {
    			// Write to file
    		}
    	}
    }
}