package environment.dataimport;

/**
 * reader interface
 */
public interface IReader<T>
{
    /**
     * returns the readed data
     *
     * @return data object
     */
    T get();

}
