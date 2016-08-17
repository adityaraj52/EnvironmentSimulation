package environment.osmquerygrammar;

/**
 * Created by adityaraj on 17.08.16.
 */
public class CQueryBuilderSample implements IQueryBuilder
{
    @Override
    public IQueryBuilder setQueryString() throws Exception
    {
        return null;
    }

    @Override
    public String getQueryString()
    {
        return null;
    }

    @Override
    public IQueryBuilder setFiltersStream( final IFilterParams... p_filterStrings )
    {
        return null;
    }

    @Override
    public IQueryBuilder defineRectangle( final double p_bottomLatitude, final double p_bottomLongitude, final double p_topLatitude,
                                          final double p_topLongitude
    )
    {
        return null;
    }

    @Override
    public IQueryBuilder defineCircle( final double p_centreLatitude, final double p_centreLongitude, final double p_value )
    {
        return null;
    }

    @Override
    public IQueryBuilder defineList( final double... p_value )
    {
        return null;
    }

    @Override
    public String createTransformer( final String p_streamSource, final Class<?> p_jaxbContext ) throws Exception
    {
        return null;
    }
}
