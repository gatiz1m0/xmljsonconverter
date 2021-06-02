package tools.xmljsonconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

/*
 * Validates the converted json address book file against the json schema
 */
public class JsonSchemaValidation {
	
	public static ValidationException validateJson(String path) throws ValidationException, FileNotFoundException {

		// Json schema file
		File jsonSchemaFile = new File("jsonSchema.json");
		
		// Prepare json schema file as a JSONObject
		JSONTokener schemaFile = new JSONTokener(new FileInputStream(jsonSchemaFile));
		JSONObject jsonSchema = new JSONObject(schemaFile);
		
		// Taking the json file to validate
		File jsonFile =  new File(path);
		JSONTokener jsonDataFile = new JSONTokener(new FileInputStream(jsonFile));
		JSONObject jsonObject = new JSONObject(jsonDataFile);
		
		// Validate json file
		Schema schemaValidator = SchemaLoader.load(jsonSchema);
		// Get the validation results as errors
		try {
			schemaValidator.validate(jsonObject);
		    return null;
		} catch (ValidationException e) {
			if (e.getCausingExceptions().size() > 1) {
	            e.getCausingExceptions().stream()
	                .map(ValidationException::getMessage)
	                .forEach(System.err::println);
			}
		    return e;
		}	
	}
}
