package environment.osmquerygrammar;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringWriter;


/**
 * base implementation of query builder
 * with XML / XD support
 */
public abstract class IXMLQueryBuilder<N extends Enum<?>, M extends Enum<?>, T extends Enum<?>, D> implements IQueryBuilder<N,M,T,D>
{
    /**
     * Jaxb marshalling / unmarshalling context
     */
    private final JAXBContext m_context;
    /**
     * XSLT transformer
     */
    private final Transformer m_transform;


    /**
     * ctor
     *
     * @param p_class
     * @param p_xslt
     * @throws JAXBException
     */
    protected IXMLQueryBuilder( final Class<?> p_class, final StreamSource p_xslt ) throws JAXBException, TransformerConfigurationException
    {
        m_context = JAXBContext.newInstance( p_class );
        m_transform = TransformerFactory.newInstance().newTransformer( p_xslt );
        m_transform.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "yes" );
    }

    /**
     * transforms the XML Jaxb structure
     *
     * @param p_streamSource
     * @param p_jaxbContext
     * @return
     */
    protected final String transform( final ) throws TransformerException
    {
        // Source
        final JAXBSource l_source = new JAXBSource( m_context, m_polynomial );

        // Transform
        final StringWriter l_writer = new StringWriter();
        m_transform.transform( l_source, new StreamResult( l_writer ) );
        return l_writer.toString();
    }

}
