package environment.dataimport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * reader for any XSD defined structure
 *
 * @tparam T data type
 */
public final class CXMLReader<T> implements IReader<T>
{
    /**
     * dataset
     */
    private final T m_data;

    /**
     * ctor
     *
     * @param p_source source
     * @throws IOException on IO errors
     * @throws JAXBException on XML errors
     */

    @SuppressWarnings( "unchecked" )
    CXMLReader( final ISource<T> p_source ) throws IOException, JAXBException
    {
        m_data = (T) JAXBContext.newInstance( p_source.getUnmarshallingClass() ).createUnmarshaller().unmarshal( p_source.getURL().openStream() );

    }

    @Override
    public final T get()
    {
        return m_data;
    }

    @Override
    public final String toString()
    {
        return m_data.toString();
    }

    @Override
    public final boolean equals( final Object p_object )
    {
        return m_data.equals( p_object );
    }

    @Override
    public final int hashCode()
    {
        return m_data.hashCode();
    }
}
