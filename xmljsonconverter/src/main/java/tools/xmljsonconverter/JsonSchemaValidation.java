package tools.xmljsonconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonSchemaValidation {
	
	public static ValidationException validateJson(String path) throws ValidationException, FileNotFoundException {
		//System.out.println("in validate json");
		// Json schema file
		File jsonSchemaFile = new File("jsonSchema.json");
		//System.out.println(path);
		
		JSONTokener schemaFile = new JSONTokener(new FileInputStream(jsonSchemaFile));
		JSONObject jsonSchema = new JSONObject(schemaFile);
		
		// Json file to validate
		File jsonFile =  new File(path);
		JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonFile));
		JSONObject jsonObject = new JSONObject(jsonDataFile);
		
		// Validate json file
		Schema schemaValidator = SchemaLoader.load(jsonSchema);
		
		try {
			schemaValidator.validate(jsonObject);
		    return null;
		} catch (ValidationException e) {
			if (e.getCausingExceptions().size() > 1) {
	            e.getCausingExceptions().stream()
	                .map(ValidationException::getMessage)
	                .forEach(System.out::println);
			}
		    return e;
		}	
	}
}
