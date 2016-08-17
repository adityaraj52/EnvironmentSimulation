package environment.osmquerygrammar;

import environment.dataimport.IfilterExpression;
import environment.dataimport.IfilterItem;
import environment.dataimport.IoperatorRelational;
import environment.dataimport.Iosmkey;
import environment.dataimport.Ipolynomial;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by adityaraj on 28/07/16.
 */
public class CQueryBuilder implements IQueryBuilder
{
    private environment.dataimport.Query m_polynomial;
    private String m_queryString;

    /**
     * querybuilder
     */
    public CQueryBuilder()
    {
        m_polynomial = new environment.dataimport.Query();
    }

    /**
     * Set a query string
     * @return IQueryBuilder
     * @throws Exception for making transformation errors
     */
    public IQueryBuilder setQueryString() throws Exception
    {
        m_queryString = this.createTransformer( "src/main/xsd/query.xsl", environment.dataimport.Query.class );
        return this;
    }

    /**
     * Gets the query String
     * @return String
     */
    public String getQueryString()
    {
        return m_queryString;
    }

    /**
     * Define rectangle polynomial and set it
     *
     *
     * @param p_bottomLatitude a parameter for bottomLatitude
     * @param p_bottomLongitude a parameter for bottomLongitude
     * @param p_topLatitude a parameter for setting topLatitude
     * @param p_topLongitude a parameter for setting top Longitude
     * @return IQueryBuilder the object itself
     **/
    public IQueryBuilder defineRectangle( final double p_bottomLatitude, final double p_bottomLongitude,
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
        m_polynomial.setPolynomial( l_tempIpolynomial );

        return this;
    }

    /**
     * Define circle polynomial and set it
     *
     *
     * @param p_centreLatitude a parameter for centre latitude
     * @param p_centreLongitude a parameter for centre longitude
     * @param p_value radius for the circle
     * @return IQueryBuilder returns the instance itself
     **/
    public IQueryBuilder defineCircle( final double p_centreLatitude, final double p_centreLongitude, final double p_value )
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

        //Add circle to polynomial
        l_tempIpolynomial.setCircle( l_circle );

        //Add the polynomial to querystring
        m_polynomial.setPolynomial( l_tempIpolynomial );

        return this;
    }

    /**
     * Set Filters for OSM File (Use Streams to optiimise for loop)
     *
     *
     * @param p_value a parameter for query string
     * @return IQueryBuilder
     **/
    public IQueryBuilder defineList( final double ... p_value )
    {
        final Ipolynomial l_tempIpolynomial = new Ipolynomial();

        //Instantiate a List class to set the values
        final Ipolynomial.List l_list = new Ipolynomial.List();

        final Collection<Ipolynomial.List.Position> l_positionCollection = new ArrayList<>();

        if ( p_value.length % 2 == 0 || ( p_value[0] != p_value[ p_value.length - 2 ] ) || ( p_value[1] != p_value[ p_value.length - 1 ] ) )
        {
            //l_positionCollection.parallelStream().filter()
            for ( int i = 0; i < p_value.length; i += 2 )
            {
                //Set Position Cordinates in terms of latitudes and longitudes
                final Ipolynomial.List.Position l_position = new Ipolynomial.List.Position();
                l_position.setLatitude( p_value[i] );
                l_position.setLongitude( p_value[i + 1] );
                l_positionCollection.add( l_position );
            }
            //Add the positions to collection list
            l_list.getPosition().addAll( l_positionCollection );

            //Add list to polynomial
            l_tempIpolynomial.setList( l_list );

            //Add the polynomial to querystring
            m_polynomial.setPolynomial( l_tempIpolynomial );

            return this;
        }
        System.out.println( "\nError: Incorrect number of co-ordinate arguments and/or end co-rodinates must match with starting co-oridnates\n" );
        return null;
    }

    /**
     * Set Filters for OSM File (Not working yet.... has to be fixed)
     *
     *
     * @param p_streamSource a parameter for tag key
     * @param p_jaxbContext a parameter for query string
     * @return String
     * @throws Exception for files
     *
     **/
    public String createTransformer( final String p_streamSource, final Class<?> p_jaxbContext ) throws Exception
    {
        //Create Transformer
        final Transformer l_transformer = TransformerFactory.newInstance().newTransformer( new StreamSource( p_streamSource ) );
        l_transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );

        // Source
        final JAXBSource l_source = new JAXBSource( JAXBContext.newInstance( p_jaxbContext ), m_polynomial );

        // Transform
        final StringWriter l_sw = new StringWriter();
        l_transformer.transform( l_source, new StreamResult( l_sw ) );
        return l_sw.toString();
    }

    /**
     * Set Filters for OSM File Use streams to optmise and remove for loop
     *
     * @param p_filterStrings setting filter strings
     * @return IQueryBuilder
     **/
    public IQueryBuilder setFiltersStream( final IFilterParams... p_filterStrings )
    {
        for ( final IFilterParams l_filter: p_filterStrings )
        {
            final IfilterExpression l_filters = new IfilterExpression();
            final IfilterItem l_item = new IfilterItem();
            l_item.setKey( Iosmkey.valueOf( l_filter.getTag() ) );
            l_item.setROperator( IoperatorRelational.valueOf( l_filter.getROperator() ) );
            l_item.setValue( l_filter.getValue() );
            l_filters.setItem( l_item );
            m_polynomial.getFilter().add( l_filters );
        }
        return this;
    }
}