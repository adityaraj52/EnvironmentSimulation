package environment.osmquerygrammar;

import com.codepoetics.protonpack.StreamUtils;
import environment.dataimport.IfilterExpression;
import environment.dataimport.IfilterItem;
import environment.dataimport.IoperatorBoolean;
import environment.dataimport.IoperatorRelational;
import environment.dataimport.Iosmkey;
import environment.dataimport.Ipolynomial;
import environment.dataimport.Query;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * builder of OSM query
 */
public final class COSMQueryBuilder extends IXMLQueryBuilder<Iosmkey, IoperatorRelational, IoperatorBoolean, String>
{
    /**
     * data of ther query
     */
    private final Query m_querydata;

    /**
     * ctor
     *
     * @throws JAXBException
     */
    protected COSMQueryBuilder() throws JAXBException, TransformerConfigurationException
    {
        super( Query.class, new StreamSource( "src/main/xsd/query.xsl" ) );
        m_querydata = new Query();
    }

    @Override
    public String query() throws TransformerException, JAXBException
    {
        return this.transform( m_querydata );
    }

    @Override
    public IQueryBuilder filter( final Iosmkey p_key, final IoperatorRelational p_operator, final String p_value )
    {
        final IfilterItem l_item = new IfilterItem();
        l_item.setKey( p_key );
        l_item.setROperator( p_operator );
        l_item.setValue( p_value );

        final IfilterExpression l_filter = new IfilterExpression();
        l_filter.setItem( l_item );


        m_querydata.getFilter().add( l_filter );
        return this;
    }

    @Override
    public IQueryBuilder next( final IoperatorBoolean p_relational )
    {
        return this;
    }

    @Override
    public IQueryBuilder circle( final double p_latitude, final double p_longitude, final double p_radius )
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
    public IQueryBuilder rectangle( final double p_topleftlatitude, final double p_topleftlongitude, final double p_bottomrightlatitude,
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
    public IQueryBuilder polygon( final double... p_value )
    {
        if ( ( p_value == null ) || ( p_value.length == 0) || ( p_value.length % 2 != 0) )
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
