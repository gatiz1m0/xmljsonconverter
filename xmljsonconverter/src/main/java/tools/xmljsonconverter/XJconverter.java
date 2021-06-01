package tools.xmljsonconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.everit.json.schema.ValidationException;

/*
 *  This is the main file
 */

public class XJconverter 
{
    public static void main( String[] args ) throws IOException, ValidationException
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
            	// Get the file path only no extension
	          	String nameOfFile = FileCheck.getPath(fileName);
	          	System.out.println("> \"" + nameOfFile + "\" xml file converted to json format saved at: ");
	          	// Change flag
	          	validateInput = false;	
	          	
	          	// Parse content
	          	String fileContent = readFile(fileName); 
	          	//System.out.println(fileContent);
	          	String jsonString = XmlToJson.xml2json(fileContent);
	          	//System.out.println(jsonString);
	          	String path = saveFile(jsonString, nameOfFile, "json");
	          	System.out.println(path);
	          	afterOptions(path, "json");
            }
            else if(typeOfFile.equals(".json")) {
            	// Get the file name only no extension
	          	String nameOfFile = FileCheck.getPath(fileName);
            	System.out.println("> \"" + nameOfFile + "\" json converted to xml format saved at: ");
	          	// Change flag
	          	validateInput = false;
	          	
	          	// Parse content
	          	String fileContent = readFile(fileName); 
	          	//System.out.println(fileContent);
	          	String xmlString = JsonToXml.json2xml(fileContent);
	          	//System.out.println(xmlString);
	          	String path = saveFile(xmlString, nameOfFile, "xml");
	          	System.out.println(path);
	          	afterOptions(path, "xml");
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
    
    private static void afterOptions(String path, String fileType) throws IOException {
    	System.out.println("Type 'r' to return to main menu");
    	System.out.println("Type 'v' to validate file");
    	
    	Scanner scan = new Scanner(System.in);
    	String option = "";
    	Boolean confirmation = false;
    	
    	while(!confirmation) {
    		option = scan.nextLine().trim();
    		if(option.equals("r")) { // Return to main menu
    			confirmation = true;
    			initialInput();
    		} else if (option.equals("v") && fileType == "xml"){
    			// Validate xml
    			XmlSchemaValidation.validateXML(path);
    			// Finished, go to main
    			initialInput();
    		} else { 
    			// Validate the json file
    			//System.out.println("last options else");
    			System.out.println(JsonSchemaValidation.validateJson(path));
    			// Finished, go to main
    			initialInput();
    		}
    	}
    	scan.close();
    }
    
    private static String saveFile(String fileContent, String fileName, String type) throws IOException {
    	String path = fileName + "." + type;
    	if(type == "xml") { // Is to be xml file
	    	String fileEncoded = ("<?xml version = \"1.0\" encoding = \"ISO-8859-1\" ?>").concat(fileContent);
	    	//System.out.println(fileEncoded);
	    	FileOutputStream outFile = new FileOutputStream(path);	 
	    	outFile.write(fileEncoded.getBytes());
	    	outFile.close();
    	} else { // Is to be a json file 
    		FileOutputStream outFile = new FileOutputStream(path);	 
	    	outFile.write(fileContent.getBytes());
	    	outFile.close();
    	}
    	return path;
    }
}