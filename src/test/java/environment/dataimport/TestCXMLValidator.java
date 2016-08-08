package environment.dataimport;

import org.junit.Before;
import org.junit.Test;
import validator.CXmlValidator;
import validator.IXmlValidator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;



/**
 * Created by adityaraj on 28/07/16.
 */
public class TestCXMLValidator
{

    private IXmlValidator m_xmlValidator;

    @Before
    public void before()
    {
        m_xmlValidator = new CXmlValidator();
    }

    /*
    @Test
    public void validate() throws Exception
    {
        m_xmlValidator.validateFile( new File( "src/main/xsd/query.xsd" ), new File( "src/main/xsd/instance1.xml" ) );
    }
    */

    /**
     * test for XSLT transformation' using JAXB
     *
     * @throws Exception on failure
     */
    @Test
    public void buildQuery() throws Exception
    {

        final environment.dataimport.Query l_queryString = new environment.dataimport.Query();
        final Ipolynomial l_tempIpolynomial = new Ipolynomial();

        final Ipolynomial.Rectangle l_rectangle = new Ipolynomial.Rectangle();
        final Ipolynomial.Rectangle.Bottomright l_bottomRight = new Ipolynomial.Rectangle.Bottomright();
        l_bottomRight.setLatitude( 11 );
        l_bottomRight.setLongitude( 11 );

        final Ipolynomial.Rectangle.Lefttop l_leftTop = new Ipolynomial.Rectangle.Lefttop();
        l_leftTop.setLatitude( 11 );
        l_leftTop.setLongitude( 11 );

        l_rectangle.setBottomright( l_bottomRight );
        l_rectangle.setLefttop( l_leftTop );

        l_tempIpolynomial.setRectangle( l_rectangle );
        l_queryString.setPolynomial( l_tempIpolynomial );

        final IfilterExpression l_filters = new IfilterExpression();
        final IfilterItem l_item = new IfilterItem();
        l_item.setKey( Iosmkey.HIGHWAY );
        l_item.setROperator( IoperatorRelational.EQUALS );
        l_item.setValue( "bus_stop" );

        l_filters.setItem( l_item );

        System.out.println( " printing filters " );
        System.out.println( l_queryString.getFilter() );

        //Create Transformer
        final Transformer l_transformer = TransformerFactory.newInstance().newTransformer( new StreamSource( "src/main/xsd/query.xsl" ) );

        // Source
        final JAXBSource l_source = new JAXBSource( JAXBContext.newInstance( environment.dataimport.Query.class ), l_queryString );

        // Result
        final StreamResult l_result = new StreamResult( System.out );

        // Transform
        l_transformer.transform( l_source, l_result );

    }
}
