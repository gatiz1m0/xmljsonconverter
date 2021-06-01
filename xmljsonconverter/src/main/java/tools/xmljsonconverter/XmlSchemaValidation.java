package tools.xmljsonconverter;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlSchemaValidation {
	    
	    public static boolean validateXML(String xmlFilePath){
	    	String xsdSchema = "xmlSchema.xsd";
	    	//System.out.println(xmlFilePath);
	        try {
	            SchemaFactory factory =
	                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Schema schema = factory.newSchema(new File(xsdSchema));
	            Validator validator = schema.newValidator();
	            validator.setErrorHandler(new ErrorHandler()
	            {

	              public void warning(SAXParseException exception) throws SAXException
	              {
	                System.err.println("Notice: " + exception.toString());
	              }

	              public void fatalError(SAXParseException exception) throws SAXException
	              {
	                error(exception);
	              }

	              public void error(SAXParseException exception) throws SAXException
	              {
	                System.err.println("Validation error: " + exception.toString());
	              }
	            });
	            validator.validate(new StreamSource(new File(xmlFilePath)));
	        } catch (IOException | SAXException e) {
	            System.out.println("Exception: "+ e);
	            return false;
	        }
	        return true;
	    }
}
