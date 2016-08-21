package data.model;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;


/**
 * interface to represent a model factory
 * @tparam T model
 */
public interface IModelFactory<T extends IModel>
{

    /**
     * generates the model
     *
     * @param p_url url to read model
     * @return model
     * @throws IOException is thrown on io errors
     * @throws JAXBException is thrown on jaxb error
     */
    T generate( final URL p_url ) throws IOException, JAXBException;

}
