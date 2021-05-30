package tools.xmljsonconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.JSONTokener;

public class JsonSchemaValidation {
	public static boolean jsonValidator(String jsonData) throws FileNotFoundException {
		//json schema
		File jsonSchemaFile = new File("jsonSchema.json");
		JSONTokener schemaData = new JSONTokener(new FileInputStream(jsonSchemaFile));
		
		return true;
	}
}
