package environment.dataimport;

import org.junit.Test;
import validator.IXmlValidator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;


/**
 * Created by adityaraj on 28/07/16.
 */
public class TestCXMLValidator
{

    private IXmlValidator m_xmlValidator;

    /**
     * Set Filters for OSM File
     *
     * @param p_queryString a parameter for query string
     * @param p_key a parameter for tag key
     * @param p_operator a parameter for query string
     * @param p_value a parameter for query string
     *
     **/
    public void setFilters( final environment.dataimport.Query p_queryString, final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value )
    {
        final IfilterExpression l_filters = new IfilterExpression();
        final IfilterItem l_item = new IfilterItem();
        l_item.setKey( p_key );
        l_item.setROperator( p_operator );
        l_item.setValue( p_value );
        l_filters.setItem( l_item );
        p_queryString.getFilter().add( l_filters );
    }

    /**
     * test for XSLT transformation' using JAXB
     *
     * @throws Exception on failure
     */
    @Test
    public void buildQuery() throws Exception
    {
        //specify a query string composed of nodes and filter expression
        final environment.dataimport.Query l_queryString = new environment.dataimport.Query();

        //Specifying the node value... In this test case rectangle
        final Ipolynomial l_tempIpolynomial = new Ipolynomial();

        //Instantiate a rectangle class to set the values
        final Ipolynomial.Rectangle l_rectangle = new Ipolynomial.Rectangle();

        //Set BottomRight latitudes
        final Ipolynomial.Rectangle.Bottomright l_bottomRight = new Ipolynomial.Rectangle.Bottomright();
        l_bottomRight.setLatitude( 11 );
        l_bottomRight.setLongitude( 11 );

        //Set LeftTop Latitudes
        final Ipolynomial.Rectangle.Lefttop l_leftTop = new Ipolynomial.Rectangle.Lefttop();
        l_leftTop.setLatitude( 11 );
        l_leftTop.setLongitude( 11 );

        //Set the properties for the rectangle
        l_rectangle.setBottomright( l_bottomRight );
        l_rectangle.setLefttop( l_leftTop );

        //Set the properties of polynomial
        l_tempIpolynomial.setRectangle( l_rectangle );

        //Add the polynomial to querystring
        l_queryString.setPolynomial( l_tempIpolynomial );

        this.setFilters( l_queryString, Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "primary" );
        this.setFilters( l_queryString, Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "bus_stop" );

        //Create Transformer
        final Transformer l_transformer = TransformerFactory.newInstance().newTransformer( new StreamSource( "src/main/xsd/query.xsl" ) );
        l_transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );

        // Source
        final JAXBSource l_source = new JAXBSource( JAXBContext.newInstance( environment.dataimport.Query.class ), l_queryString );

        // Transform
        final StringWriter l_sw = new StringWriter();
        l_transformer.transform( l_source, new StreamResult( l_sw ) );
        System.out.println(  "\n" + l_sw + "\n" );
    }
}
