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
import java.io.File;
import java.util.ArrayList;
import java.util.List;



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

    @Test
    public void validate() throws Exception
    {
        m_xmlValidator.validateFile( new File( "src/main/xsd/query.xsd" ), new File( "src/main/xsd/instance1.xml" ) );
    }

    /**
     * test for XSLT transformation' using JAXB
     *
     * @throws Exception on failure
     */
    @Test
    public void buildQuery() throws Exception
    {
        // List of Filter expressions
        final List<IfilterExpression> l_filter = new ArrayList<>();

        l_filter.add( new IfilterExpression() {
            {
                item.setKey( Iosmkey.HIGHWAY );
                item.setROperator( IoperatorRelational.EQUALS );
                item.setValue( "bus_stop" );
            }
        } );

        final environment.dataimport.Query l_queryString = new environment.dataimport.Query() {
            {
                setPolynomial( new Ipolynomial() {
                    {
                        rectangle.setBottomright( new Ipolynomial.Rectangle.Bottomright() {
                            {
                                setLatitude( 11 );
                                setLongitude( 11 );
                            }
                        } );

                        rectangle.setLefttop( new Ipolynomial.Rectangle.Lefttop()
                        {
                            {
                                setLatitude( 11 );
                                setLongitude( 11 );
                            }
                        } );
                    }
                } );
                filter = l_filter;
            }
        };

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
