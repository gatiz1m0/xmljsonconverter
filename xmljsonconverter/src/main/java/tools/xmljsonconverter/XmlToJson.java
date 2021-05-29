package tools.xmljsonconverter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {
	private static int INDENT = 4;
	
	public static String xml2json(String xmlFileContent) {
		String jsonIndentedString = "";
		try {
			//System.out.println("In xml2json");
			// Convert xml to json and indent string
			JSONObject json = XML.toJSONObject(xmlFileContent);
			jsonIndentedString = json.toString(INDENT);
		} catch(JSONException exception) {
			System.out.println(exception.toString());
		}
		
		return jsonIndentedString;
	}
}
