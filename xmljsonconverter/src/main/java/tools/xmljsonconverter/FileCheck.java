package tools.xmljsonconverter;

/* This class checks if the file provided is valid and what
 * extension it has (xml or json)
 */

public class FileCheck {
	public static String getExtension(String file) {
		
		if(file.indexOf(".") == -1) {
			return "error";
		} else {
			return file.substring(file.indexOf("."), file.length());				
		}
	}
	
	public static String getFileName(String file) {

		return file.substring(file.lastIndexOf("/") + 1, file.indexOf("."));
	}
}
