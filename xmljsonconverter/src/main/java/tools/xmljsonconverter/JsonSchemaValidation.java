package tools.xmljsonconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonSchemaValidation {
	
	public static String validateJson(String path) throws FileNotFoundException {
		//System.out.println("in validate json");
		String jsonSchemaFile = "jsonSchema.json";
		System.out.println(jsonSchemaFile);
		
		JSONTokener schemaFile = new JSONTokener(new FileInputStream(jsonSchemaFile));
		JSONObject jsonSchema = new JSONObject(schemaFile);
		
		// json data
		File jsonFile =  new File(path);
		JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonFile));
		JSONObject jsonObject = new JSONObject(jsonDataFile);
		
		// Validate json file
		Schema schemaValidator = SchemaLoader.load(jsonSchema);
		schemaValidator.validate(jsonObject);
		
		return "Data has been validated";
			
	}
}
