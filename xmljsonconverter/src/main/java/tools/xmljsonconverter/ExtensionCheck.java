package tools.xmljsonconverter;

public class ExtensionCheck {
	public static String getExtension(String file) {
		if(file == "exit") {
			System.out.println("exit");
			return "exit";
		} else if(file.indexOf(".") == -1) {
			return "error";
		} else {
			return file.substring(file.length() - 3,
					file.length());				
		}
	}
}
