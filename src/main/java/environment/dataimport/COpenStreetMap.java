package environment.dataimport;

import org.apache.commons.lang3.tuple.Pair;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Locale;


/**
 * openstreetmap bounding-box download
 */
public final class COpenStreetMap implements ISource<Osm>
{
    /**
     * locale for number formating
     */
    private static final Locale NUMBERLOCAL = Locale.ENGLISH;
    /**
     * number format
     */
    private static final String NUMBERFORMAT = "%1.4f";
    /**
     * download URL
     */
    private final URL m_url;

    /**
     * ctor
     *
     * @param p_upperleft upper-left position
     * @param p_lowerright lower-right position
     * @throws MalformedURLException on URL errors
     */
    public COpenStreetMap( final Pair<Double, Double> p_upperleft, final Pair<Double, Double> p_lowerright ) throws MalformedURLException
    {
        this( p_upperleft.getLeft(), p_upperleft.getRight(), p_lowerright.getLeft(), p_lowerright.getRight() );
    }

    /**
     * ctor
     *
     * @param p_upperleftlatitude upper-left latitude
     * @param p_upperleftlongitude upper-left longitude
     * @param p_lowerrightlatitude lower-right latitude
     * @param p_lowerrightlongitude lower-right longitude
     * @throws MalformedURLException on URL errors
     *
     */
    public COpenStreetMap( final double p_upperleftlatitude, final double p_upperleftlongitude, final double p_lowerrightlatitude,
                           final double p_lowerrightlongitude
    )
    throws MalformedURLException
    {
        m_url = new URL(
            MessageFormat.format(
                "http://api.openstreetmap.org/api/0.6/map?bbox={0},{1},{2},{3}",
                String.format( NUMBERLOCAL, NUMBERFORMAT, p_upperleftlatitude ),
                String.format( NUMBERLOCAL, NUMBERFORMAT, p_upperleftlongitude ),
                String.format( NUMBERLOCAL, NUMBERFORMAT, p_lowerrightlatitude ),
                String.format( NUMBERLOCAL, NUMBERFORMAT, p_lowerrightlongitude )
            )
        );
    }

    @Override
    public final Class<?> getUnmarshallingClass()
    {
        return Osm.class;
    }

    @Override
    public final URL getURL()
    {
        return m_url;
    }

    @Override
    public final int hashCode()
    {
        return m_url.hashCode();
    }

    @Override
    public final boolean equals( final Object p_object )
    {
        return ( p_object != null ) && ( p_object instanceof ISource<?> ) && ( m_url.equals( p_object ) );
    }

    @Override
    public final String toString()
    {
        return m_url.toString();
    }
}
