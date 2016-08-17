package environment.osmquerygrammar;

/**
 * Created by adityaraj on 15/08/16.
 */
public interface IQueryBuilder
{
    /**
     * Set a query string
     * @return IQueryBuilder
     * @throws Exception for making transformation errors
     */
    public IQueryBuilder setQueryString() throws Exception;

    /**
     * Gets the query String
     * @return String
     */
    public String getQueryString();

    /**
     * Set multiple Filters for OSM File
     * Use streams to optmise and remove for loop
     *
     * @param p_filterStrings setting filter strings
     * @return IQueryBuilder
     **/
    public IQueryBuilder setFiltersStream( final IFilterParams... p_filterStrings );

    /**
     * Define rectangle polynomial and set it
     *
     *
     * @param p_bottomLatitude a parameter for bottomLatitude
     * @param p_bottomLongitude a parameter for bottomLongitude
     * @param p_topLatitude a parameter for setting topLatitude
     * @param p_topLongitude a parameter for setting top Longitude
     * @return IQueryBuilder the object itself
     **/
    public IQueryBuilder defineRectangle( final double p_bottomLatitude, final double p_bottomLongitude,
                                          final double p_topLatitude, final double p_topLongitude );

    /**
     * Define circle polynomial and set it
     *
     *
     * @param p_centreLatitude a parameter for centre latitude
     * @param p_centreLongitude a parameter for centre longitude
     * @param p_value radius for the circle
     * @return IQueryBuilder returns the instance itself
     **/
    public IQueryBuilder defineCircle( final double p_centreLatitude, final double p_centreLongitude, final double p_value );

    /**
     * Set Filters for OSM File (Use Streams to optiimise for loop)
     *
     *
     * @param p_value a parameter for query string
     * @return IQueryBuilder
     **/
    public IQueryBuilder defineList( final double ... p_value );

    /**
     * Set Filters for OSM File (Not working yet.... has to be fixed)
     *
     *
     * @param p_streamSource a parameter for tag key
     * @param p_jaxbContext a parameter for query string
     * @return String
     * @throws Exception for files
     *
     **/
    public String createTransformer( final String p_streamSource, final Class<?> p_jaxbContext ) throws Exception;

}
