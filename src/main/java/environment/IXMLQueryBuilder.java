package environment;


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
public abstract class IXMLQueryBuilder<N, M, T> implements IQueryBuilder<N, M, T>
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
     * @param p_class Jaxb class definition
     * @param p_xslt XSLT stream source
     * @throws JAXBException is thrown on Jaxb instantiation
     * @throws TransformerConfigurationException is thrown on XSLT definition
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
     * @param p_data transform dataset
     * @return result as string
     */
    protected final String transform( final Object p_data ) throws TransformerException, JAXBException
    {
        final StringWriter l_writer = new StringWriter();
        m_transform.transform( new JAXBSource( m_context, p_data ), new StreamResult( l_writer ) );
        return l_writer.toString();
    }

}
