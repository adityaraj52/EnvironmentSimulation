package validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by adityaraj on 27/07/16.
 */
public class CXmlValidator implements IXmlValidator
{

    /**
     * ctor
     *
     * @param p_xsd a parameter for xsd schema file
     * @param p_xml a parameter for xml file
     * @throws ParserConfigurationException on Parsing the XML file
     * @throws SAXException On SaxException
     * @throws IOException on IOExceptions
     */
    public void validateFile( final File p_xsd, final File p_xml ) throws ParserConfigurationException, SAXException, IOException
    {

        // parse an XML document into a DOM tree
        final Document l_document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( p_xml );

        // create a SchemaFactory capable of understanding WXS schemas and then
        // load a WXS schema, represented by a Schema instance
        final Schema l_schema = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI ).newSchema( new StreamSource( p_xsd ) );

        // validate the DOM tree
            // create a Validator instance, which can be used to validate an instance document
        try
        {
            l_schema.newValidator().validate( new DOMSource( l_document ) );
            System.out.println( "Instance is Valid" );
        }
        catch ( final SAXException l_exception )
        {
            System.out.println( "Got SAXException" + l_exception.getMessage() + l_exception.getStackTrace() );
        }
    }

    public void validateStream( final File p_xsd, final File p_xml ) throws Exception
    {

    }
}
