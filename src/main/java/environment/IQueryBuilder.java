package environment;


/**
 * interface of a querybuilder
 *
 * @tparam N enum type of filter keys
 * @tparam M enum type of filter relations
 * @tparam T enum type of operators between filters
 * @tparam D data type of filter values
 */
public interface IQueryBuilder<N extends Enum<?>, M extends Enum<?>, T extends Enum<?>, D>
{
    /**
     * returns API specific query
     *
     * @return query
     * @throws Exception any error
     */
    String query() throws Exception;

    /**
     * sets a filter expression
     *
     * @param p_key filter key
     * @param p_operator filter relation
     * @param p_value value for the filter
     * @return self reference
     **/
    IQueryBuilder filter( final N p_key, final M p_operator, final D p_value  );

    /**
     * sets a filter expression
     *
     * @param p_filteroperator operator between filters
     * @param p_key filter key
     * @param p_operator filter relation
     * @param p_value value for the filter
     * @return self reference
     **/
    IQueryBuilder filter( final T p_filteroperator, final N p_key, final M p_operator, final D p_value  );

    /**
     * set bounding-box as circle
     *
     * @param p_latitude latitude
     * @param p_longitude longitude
     * @param p_radius radius
     * @return self reference
     **/
    IQueryBuilder circle( final double p_latitude, final double p_longitude, final double p_radius );

    /**
     * set bounding-box as a rectangle
     *
     * @param p_topleftlatitude top-left latitude
     * @param p_topleftlongitude top-left longitude
     * @param p_bottomrightlatitude bottom-right latitude
     * @param p_bottomrightlongitude bottom-right longitude
     * @return self reference
     */
    IQueryBuilder rectangle( final double p_topleftlatitude, final double p_topleftlongitude, final double p_bottomrightlatitude, final double p_bottomrightlongitude );

    /**
     * sets the bouning-nox as a polygon
     *
     *
     * @param p_value pairwise latitude / longitude values of the polygon position
     * @return self reference
     **/
    public IQueryBuilder polygon( final double ... p_value );

}
