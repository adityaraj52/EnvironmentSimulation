package environment.dataimport;

import org.junit.Before;
import org.junit.Test;
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
    private environment.dataimport.Query m_queryString;

    @Before
     public void before()
    {
        m_queryString = new environment.dataimport.Query();
    }

    /**
     * Set Filters for OSM File
     *
     * @param p_key a parameter for tag key
     * @param p_operator a parameter for query string
     * @param p_value a parameter for query string
     *
     **/
    public void setFilters( final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value )
    {
        final IfilterExpression l_filters = new IfilterExpression();
        final IfilterItem l_item = new IfilterItem();
        l_item.setKey( p_key );
        l_item.setROperator( p_operator );
        l_item.setValue( p_value );
        l_filters.setItem( l_item );
        m_queryString.getFilter().add( l_filters );
    }

    /**
     * Define rectangle polynomial and set it
     *
     *
     * @param p_bottomLatitude a parameter for bottomLatitude
     * @param p_bottomLongitude a parameter for bottomLongitude
     * @param p_topLatitude a parameter for setting topLatitude
     * @param p_topLongitude a parameter for setting top Longitude
     **/
    public void defineRectangle( final double p_bottomLatitude, final double p_bottomLongitude,
                                                                                    final double p_topLatitude, final double p_topLongitude )
    {
        //Specifying the node value... In this test case rectangle
        final Ipolynomial l_tempIpolynomial = new Ipolynomial();

        //Instantiate a rectangle class to set the values
        final Ipolynomial.Rectangle l_rectangle = new Ipolynomial.Rectangle();

        //Set BottomRight latitudes
        final Ipolynomial.Rectangle.Bottomright l_bottomRight = new Ipolynomial.Rectangle.Bottomright();
        l_bottomRight.setLatitude( p_bottomLatitude );
        l_bottomRight.setLongitude( p_bottomLongitude );

        //Set LeftTop Latitudes
        final Ipolynomial.Rectangle.Lefttop l_leftTop = new Ipolynomial.Rectangle.Lefttop();
        l_leftTop.setLatitude( p_topLatitude );
        l_leftTop.setLongitude( p_topLongitude );

        //Set the properties for the rectangle
        l_rectangle.setBottomright( l_bottomRight );
        l_rectangle.setLefttop( l_leftTop );

        //Set the properties of polynomial
        l_tempIpolynomial.setRectangle( l_rectangle );

        //Add the polynomial to querystring
        m_queryString.setPolynomial( l_tempIpolynomial );
    }

    /**
     * Define circle polynomial and set it
     *
     *
     * @param p_centreLatitude a parameter for centre latitude
     * @param p_centreLongitude a parameter for centre longitude
     * @param p_value radius for the circle
     *
     **/
    public void defineCircle( final double p_centreLatitude, final double p_centreLongitude, final double p_value )
    {
        final Ipolynomial l_tempIpolynomial = new Ipolynomial();

        //Instantiate a rectangle class to set the values
        final Ipolynomial.Circle l_circle = new Ipolynomial.Circle();

        //Set Centre Cordinates in terms of latitudes and longitudes
        final Ipolynomial.Circle.Centre l_centre = new Ipolynomial.Circle.Centre();
        l_centre.setLatitude( p_centreLatitude );
        l_centre.setLongitude( p_centreLongitude );

        //Set the properties for the rectangle
        l_circle.setCentre( l_centre );
        l_circle.setRadius( p_value );

        //Add the polynomial to querystring
        m_queryString.setPolynomial( l_tempIpolynomial );
    }

    /**
     * Set Filters for OSM File (Not working yet.... has to be fixed)
     *
     *
     * @param p_key a parameter for tag key
     * @param p_operator a parameter for query string
     * @param p_value a parameter for query string
     *
     **/
    public void defineList( final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value )
    {
        final IfilterExpression l_filters = new IfilterExpression();
        final IfilterItem l_item = new IfilterItem();
        l_item.setKey( p_key );
        l_item.setROperator( p_operator );
        l_item.setValue( p_value );
        l_filters.setItem( l_item );
        m_queryString.getFilter().add( l_filters );
    }


    /**
     * test for XSLT transformation' using JAXB
     *
     * @throws Exception on failure
     */
    @Test
    public void buildQuery() throws Exception
    {
        //Define bounding box in format South, West, North, East
        //Format BottomRight (Latitude, Longitude) LeftTop (Latitude, Longitude)
        this.defineRectangle( 12, 11, 14, 13 );

        //Define a circcular bounding region with centre latitude, longitude followed by
        //radius of the region to look for
        //this.defineCircle( 11, 11, 100 );

        //Set filters for the query
        this.setFilters( Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "primary" );
        this.setFilters( Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "bus_stop" );

        //Create Transformer
        final Transformer l_transformer = TransformerFactory.newInstance().newTransformer( new StreamSource( "src/main/xsd/query.xsl" ) );
        l_transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );

        // Source
        final JAXBSource l_source = new JAXBSource( JAXBContext.newInstance( environment.dataimport.Query.class ), m_queryString );

        // Transform
        final StringWriter l_sw = new StringWriter();
        l_transformer.transform( l_source, new StreamResult( l_sw ) );
        System.out.println(  "\n" + l_sw + "\n" );
    }
}
