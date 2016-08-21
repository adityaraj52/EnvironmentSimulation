package data.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * abstract for XML model factory
 *
 * @tparam N unmarshalling model
 * @tparam T result model
 */
public abstract class IXMLModelFactory<N, T extends IModel> implements IModelFactory<T>
{
    /**
     * Jaxb marshalling / unmarshalling context
     */
    private final JAXBContext m_context;

    /**
     * ctor
     *
     * @param p_class context class
     * @throws JAXBException is thrown on instantiation error
     */
    protected IXMLModelFactory( final Class<?> p_class ) throws JAXBException
    {
        m_context = JAXBContext.newInstance( p_class );
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public T generate( final URL p_url ) throws IOException, JAXBException
    {
        final InputStream l_stream = p_url.openConnection().getInputStream();
        final T l_model = this.build( (N) m_context.createUnmarshaller().unmarshal( l_stream ) );
        l_stream.close();

        return l_model;
    }

    /**
     * bulds the model from the unmarshalled data
     *
     * @param p_object unmarshalled data
     * @return model
     */
    protected abstract T build( final N p_object );

}
