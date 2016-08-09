package environment.dataimport;

import org.junit.Test;

/**
 * Created by adityaraj on 09/08/16.
 */
public class TestCQueryBuilder
{
    /**
     * test for XSLT transformation' using JAXB
     *
     * @throws Exception on failure
     */
    @Test
    public void buildQuery() throws Exception
    {
        final CQueryBuilder l_queryBuilder = new CQueryBuilder();

        //Define bounding box in format South, West, North, East
        //Format BottomRight (Latitude, Longitude) LeftTop (Latitude, Longitude)
        l_queryBuilder.defineRectangle( 12, 11, 14, 13 );

        //Define a circcular bounding region with centre latitude, longitude followed by
        //radius of the region to look for
        //l_queryBuilder.defineCircle( 1, 0, 50 );

        //Set filters for the query
        l_queryBuilder.setFilters( Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "primary" );
        l_queryBuilder.setFilters( Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "bus_stop" );

        //setQueryString
        l_queryBuilder.setQueryString( l_queryBuilder.createTransformer( "src/main/xsd/query.xsl", environment.dataimport.Query.class ) );

        //Print QueryString
        System.out.println( l_queryBuilder.getQueryString() );

    }

}
