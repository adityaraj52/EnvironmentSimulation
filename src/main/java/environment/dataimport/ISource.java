package environment.dataimport;

import java.net.URL;


/**
 * source interface
 */
public interface ISource<T>
{

    /**
     * returns the unmarchalling class
     * @return unmarchalling class type
     */
    Class<?> getUnmarshallingClass();

    /**
     * returns the download URL
     *
     * @return URL
     */
    URL getURL();

}
