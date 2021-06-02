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

/*
 * Validates the converted xml address book file against the xsd schema
 */
public class XmlSchemaValidation {
	    
	    public static boolean validateXML(String xmlFilePath){
	    	// Get the xsd schema file
	    	String xsdSchema = "xmlSchema.xsd";
	    	
	    	// Prepare the validator using the xsd schema
	    	try {
	            SchemaFactory factory =
	                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Schema schema = factory.newSchema(new File(xsdSchema));
	            Validator validator = schema.newValidator();
	            // Then validate outputting any errors
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
