package environment.dataimport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;


/**
 * reader for any XSD defined structure
 * @tparam T data type
 */
public final class CReader<T>
{
    /**
     * dataset
     */
    private final T m_data;

    /**
     * ctor
     *
     * @param p_reader reader class type
     * @param p_url source URL
     * @throws IOException on IO errors
     * @throws JAXBException on XML errors
     */
    @SuppressWarnings( "unchecked" )
    CReader( final Class<?> p_reader, final URL p_url ) throws IOException, JAXBException
    {
        m_data = (T) JAXBContext.newInstance( p_reader ).createUnmarshaller().unmarshal( p_url.openStream() );

    }

    /**
     * retuns the readed data model
     * @return data object
     */
    public final T get()
    {
        return m_data;
    }
}
