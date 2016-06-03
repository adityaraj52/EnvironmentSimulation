package environment.dataimport;

/**
 * reader interface
 */
public interface IReader<T>
{
    /**
     * returns the read data
     *
     * @return data object
     */
    T get();

}
