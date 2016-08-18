package environment.osmquerygrammar;

import environment.dataimport.IoperatorBoolean;
import environment.dataimport.IoperatorRelational;
import environment.dataimport.Iosmkey;
import environment.dataimport.Ipolynomial;
import environment.dataimport.Query;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;


/**
 *
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
    public IQueryBuilder filter( final Iosmkey p_key, final IoperatorRelational p_relation, final String p_value )
    {
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
        return this;
    }

    @Override
    public IQueryBuilder polygon( final double... p_value )
    {
        return this;
    }
}
