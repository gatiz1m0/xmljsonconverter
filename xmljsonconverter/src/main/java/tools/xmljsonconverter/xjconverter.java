package tools.xmljsonconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
    	Boolean validateInput = true;
    	// Read the file scanner
    	Scanner scan = new Scanner(System.in);
    	
    	// Will keep asking for a valid file
    	while(validateInput) {		
    		System.out.println( "Paste your file path here or 'x' to exit: "); 
    		String fileName = scan.nextLine();
         
	        // In case user wants to quit
	        if(fileName.equals("x")) {   
	        	System.out.println("Good bye");
	        	scan.close();
	        	System.exit(0);
	        }
	        
	        // Else verify we have a valid file type
	        String typeOfFile = ExtensionCheck.getExtension(fileName);
	        
	        // What type of file we have?
            if(typeOfFile.equals(".xml")) {
	          	System.out.println("Is xml file");
	          	// Change flag
	          	validateInput = false;	          	
	          	// Parse content
	          	String fileContent = readFile(fileName); 
	          	System.out.println(fileContent);
            }
            else if(typeOfFile.equals(".json")) {
          	// Change flag
          	validateInput = false;
           } else {
        	   System.out.println("File extension is needed.");
           }
    		
    		// Create file object
    		File file = new File(fileName);
    		
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null) {
            	System.out.println(line);
            }
            
            // To verify the file extension
           // String verifyExtension = fileName.next().trim();         
            // In case user wants to quit
//            if(verifyExtension.equals("x")) {   
//            	System.out.println("Good bye");
//            	System.exit(0);
//            }
            // Else verify
//            String typeOfFile = ExtensionCheck.getExtension(verifyExtension);
//            
//            // What type of file we have?
//            if(typeOfFile.equals(".xml")) {
//            	System.out.println("Is xml file");
//            	// Change flag
//            	validateInput = false;
//            	fileName.close();
//            	
//            	// Parse content
//            	xmlReader(inputFile);
//            	
//            }
//            else if(typeOfFile.equals(".json")) {
//            	// Change flag
//            	validateInput = false;
//            	fileName.close();
//            	
//            	// Parse content
//            	//String jsonToXml = fileToString(fileName.next());
//            } else {
//            	System.out.println("File extension is needed.");
//            }
            	
    	}
    }
    
    
    // Reads the file into a String
    private static String fileToString(Scanner fileName) {
    	System.out.println("in fileToString method");
    	String fileContent = "";
    	
    	while(fileName.hasNextLine()) {
    		fileContent = fileContent.concat(fileName.nextLine() + "\n");
    	}
    	System.out.println(fileContent);
    	return fileContent;
    }
    
    private static void xmlReader(File file) throws FileNotFoundException {
		System.out.println("In xml reader ");
		Scanner scan = new Scanner(file);
		String fileContent = "";
		
		while(scan.hasNextLine()) {
			fileContent = fileContent.concat(scan.nextLine() + "\n");
		}
		
		System.out.print(fileContent);
	}
    
    private static String readFile(String fileName) throws IOException
    {
    	// Create file object
		File file = new File(fileName);
		
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line, fileContent = "";

        while((line = reader.readLine()) != null) {
        	//System.out.println(line);
        	fileContent.concat(line);
        }
        
        return fileContent;
    }
}