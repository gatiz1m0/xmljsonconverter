package tools.xmljsonconverter;

import java.util.Scanner;

/* This is the 
 * 
 */

public class xjconverter 
{
    public static void main( String[] args )
    { 	
    	// Flags flase if the input is a valid file
    	Boolean validateInput = true;
    	
    	// Will keep asking for a valid file
    	while(validateInput) {
    		System.out.println( "Paste your file path here or 'x' to exit: ");
            Scanner fileName = new Scanner(System.in);  
            //System.out.println("the file is " + file.next()); 
            
            // Verify the file extension
            String verifyExtension = fileName.next();
            
            // In case user wants to quit
            if(verifyExtension.equals("x")) {   
            	System.out.println("Good bye");
            	System.exit(0);
            }
            
            String typeOfFile = ExtensionCheck.getExtension(verifyExtension);
            
            // What type of file we have?
            if(typeOfFile.equals(".xml")) {
            	// Change flag
            	validateInput = false;
            	
            	// Parse content
            	String xmlToJson = fileToString(fileName); 
            	System.out.println(xmlToJson);
            } else 
            if(typeOfFile.equals(".json")) {
            	// Change flag
            	validateInput = false;
            	
            	// Parse content
            	String jsonToXml = fileToString(fileName);
            	System.out.println(jsonToXml);
            } else {
            	System.out.println("File extension is needed.");
            }
            	
    	}
    }
    
    // Reads the file into a String
    private static String fileToString(Scanner fileName) {
    	
    	String fileContent = "";
    	
    	while(fileName.hasNextLine()) {
    		fileContent = fileContent.concat(fileName.nextLine());
    	}
    	return fileContent;
    }
}