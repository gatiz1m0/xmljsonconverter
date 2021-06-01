package tools.xmljsonconverter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class JsonToXml {
	
	public static String json2xml (String jsonFileContent) {
		String xmlString = "";
		try {
			//System.out.println("In json2xml");

			//Convert json to xml
			JSONObject json = new JSONObject(jsonFileContent);
			xmlString = XML.toString(json);
		} catch(JSONException exception) {
			System.out.println(exception.toString());
		}
		
		return xmlString;
	}
}
