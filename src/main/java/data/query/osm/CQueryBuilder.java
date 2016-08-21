package data.query.osm;

import com.codepoetics.protonpack.StreamUtils;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * builder of OSM query
 */
public final class CQueryBuilder extends IXMLQueryBuilder<Ikey, Ioperator, String>
{
    /**
     * contry specific url
     */
    private final ECountry m_country;
    /**
     * data of the query
     */
    private final Query m_querydata = new Query();

    /**
     * ctor
     *
     * @param p_county country defintition
     * @throws JAXBException is thrown on initializing error
     * @throws TransformerConfigurationException is thrown on XSLT instantiation
     */
    public CQueryBuilder( final ECountry p_county ) throws JAXBException, TransformerConfigurationException
    {
        super( Query.class, new StreamSource( CQueryBuilder.class.getResourceAsStream( "/data/osm/query.xsl" ) ) );
        m_country = p_county;
    }

    @Override
    public final URL url() throws TransformerException, JAXBException, MalformedURLException
    {
        return new URL( m_country.url() + this.transform( m_querydata ) );
    }

    @Override
    public final IQueryBuilder<Ikey, Ioperator, String> filter( final Ikey p_key, final Ioperator p_operator, final String p_value )
    {
        final Ifilteritem l_item = new Ifilteritem();
        l_item.setKey( p_key );
        l_item.setOperator( p_operator );
        l_item.setValue( p_value );

        final Ifilterexpression l_filter = new Ifilterexpression();
        l_filter.setItem( l_item );


        m_querydata.getFilter().add( l_filter );
        return this;
    }

    @Override
    public final IQueryBuilder<Ikey, Ioperator, String> circle( final double p_latitude, final double p_longitude, final double p_radius )
    {
        final Ipolynomial.Circle.Centre l_center = new Ipolynomial.Circle.Centre();
        l_center.setLatitude( p_latitude );
        l_center.setLongitude( p_longitude );

        final Ipolynomial.Circle l_circle = new Ipolynomial.Circle();
        l_circle.setRadius( p_radius );
        l_circle.setCentre( l_center );


        final Ipolynomial l_polynomial = new Ipolynomial();
        l_polynomial.setCircle( l_circle );
        m_querydata.setPolynomial( l_polynomial );
        return this;
    }

    @Override
    public final IQueryBuilder<Ikey, Ioperator, String> rectangle( final double p_topleftlatitude, final double p_topleftlongitude,
                                                                   final double p_bottomrightlatitude, final double p_bottomrightlongitude
    )
    {
        final Ipolynomial.Rectangle.Bottomright l_bottom = new Ipolynomial.Rectangle.Bottomright();
        l_bottom.setLatitude( p_bottomrightlatitude );
        l_bottom.setLongitude( p_bottomrightlongitude );

        final Ipolynomial.Rectangle.Topleft l_top = new Ipolynomial.Rectangle.Topleft();
        l_top.setLatitude( p_topleftlatitude );
        l_top.setLongitude( p_topleftlongitude );

        final Ipolynomial.Rectangle l_rectangle = new Ipolynomial.Rectangle();
        l_rectangle.setBottomright( l_bottom );
        l_rectangle.setTopleft( l_top );


        final Ipolynomial l_polynomial = new Ipolynomial();
        l_polynomial.setRectangle( l_rectangle );
        m_querydata.setPolynomial( l_polynomial );
        return this;
    }

    @Override
    public final IQueryBuilder<Ikey, Ioperator, String> polygon( final double... p_value )
    {
        if ( ( p_value == null ) || ( p_value.length == 0 ) || ( p_value.length % 2 != 0 ) )
            throw new RuntimeException( "number of arguments must be greater than zero and even" );

        final Ipolynomial.List l_polygon = new Ipolynomial.List();
        l_polygon.getPosition().addAll(
            StreamUtils.windowed( Arrays.stream( p_value ).boxed(), 2, 1 )
                       .map( i -> {
                           final Ipolynomial.List.Position l_position = new Ipolynomial.List.Position();
                           l_position.setLatitude( i.get( 0 ) );
                           l_position.setLongitude( i.get( 1 ) );
                           return l_position;
                       } )
                       .collect( Collectors.toList() )
        );


        final Ipolynomial l_polynomial = new Ipolynomial();
        l_polynomial.setList( l_polygon );
        m_querydata.setPolynomial( l_polynomial );
        return this;
    }


    /**
     * country codes for
     * contry specific url
     *
     * @see http://wiki.openstreetmap.org/wiki/Overpass_API
     */
    public enum ECountry
    {
        DE( "http://overpass-api.de/api/interpreter?data=" ),
        FR( "http://api.openstreetmap.fr/oapi/interpreter?data=" ),
        CH( "http://overpass.osm.ch/api/interpreter?data=" );

        /**
         * server url
         */
        private final String m_url;

        /**
         * ctor
         *
         * @param p_url endpoint url
         */
        ECountry( final String p_url )
        {
            m_url = p_url;
        }

        /**
         * returns the endpoint url
         *
         * @return url as string
         */
        public final String url()
        {
            return m_url;
        }
    }
}
