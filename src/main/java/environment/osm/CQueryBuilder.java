package environment.osm;

import com.codepoetics.protonpack.StreamUtils;
import environment.IQueryBuilder;
import environment.IXMLQueryBuilder;

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
public final class CQueryBuilder extends IXMLQueryBuilder<Iosmkey, IoperatorRelational, IoperatorBoolean, String>
{
    /**
     * data of ther query
     */
    private final Query m_querydata = new Query();

    /**
     * ctor
     *
     * @throws JAXBException is thrown on initializing error
     * @throws TransformerConfigurationException is thrown on XSLT instantiation
     */
    public CQueryBuilder() throws JAXBException, TransformerConfigurationException
    {
        super( Query.class, new StreamSource( CQueryBuilder.class.getResourceAsStream( "/environment/osm/query.xsl" ) ) );
    }

    @Override
    public final URL query() throws TransformerException, JAXBException, MalformedURLException
    {
        return new URL( "http://overpass-api.de/api/interpreter?data=" + this.transform( m_querydata ) );
    }

    @Override
    public final IQueryBuilder filter( final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value )
    {
        m_querydata.getFilter().add( this.createfilter( p_key, p_operator, p_value ) );
        return this;
    }

    @Override
    public final IQueryBuilder filter( final IoperatorBoolean p_filteroperator, final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value
    )
    {
        final IfilterExpression l_filter = this.createfilter( p_key, p_operator, p_value );
        l_filter.getItem().setFilteroperator( p_filteroperator );

        m_querydata.getFilter().add( l_filter );
        return this;
    }

    /**
     * creates the filter expression
     *
     * @param p_key keys
     * @param p_operator operator
     * @param p_value value
     * @return filter expression
     */
    private IfilterExpression createfilter( final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value )
    {
        final IfilterItem l_item = new IfilterItem();
        l_item.setKey( p_key );
        l_item.setExpressionoperator( p_operator );
        l_item.setValue( p_value );

        final IfilterExpression l_filter = new IfilterExpression();
        l_filter.setItem( l_item );
        return l_filter;
    }

    @Override
    public final IQueryBuilder circle( final double p_latitude, final double p_longitude, final double p_radius )
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
    public final IQueryBuilder rectangle( final double p_topleftlatitude, final double p_topleftlongitude, final double p_bottomrightlatitude,
                                    final double p_bottomrightlongitude
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
    public final IQueryBuilder polygon( final double... p_value )
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
                       .collect( Collectors.toSet() )
        );


        final Ipolynomial l_polynomial = new Ipolynomial();
        l_polynomial.setList( l_polygon );
        m_querydata.setPolynomial( l_polynomial );
        return this;
    }
}
