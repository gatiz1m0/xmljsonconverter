package tools.xmljsonconverter;

import java.util.Scanner;

public class xjconverter 
{
    public static void main( String[] args )
    { 	
    	Boolean validateInput = true;
    	
    	while(validateInput) {
    		System.out.println( "To exit type 'exit' or ");
    		System.out.println( "Please paste your file path here: ");
            Scanner file = new Scanner(System.in);  
            //System.out.println("the file is " + file.next()); 
            
            // Verify the file extension
            String verifyExtension = file.next();
            //System.out.println(verifyExtension);
            
            if(verifyExtension.equals("exit")) {   
            	//System.out.println("inside exit");
            	System.exit(0);
            }
            
            String typeOfFile = ExtensionCheck.getExtension(verifyExtension);
            
            if(!typeOfFile.equals("error")) {
            	System.out.println("Type of file is " + typeOfFile);
            	validateInput = false;
            }
            	
    	}
    }
}