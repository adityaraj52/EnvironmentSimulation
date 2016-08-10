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
        //l_queryBuilder.defineRectangle( 12, 11, 14, 13 );

        //Define a circular bounding region with centre latitude, longitude followed by
        //radius of the region to look for.

        //Get the query string by
        //1. set bounding box
        //2. set filter stream
        //3. set queryString by chosing the right transformation files specified in order XSLT file and then the JAXB generated Class
        //4. Get the Query String

        System.out.println(
                l_queryBuilder.defineRectangle( 12, 11, 14, 13 )
                .setFiltersStream( new CFilterStrings( "highway", "equals", "primary" ), new CFilterStrings( "railway", "not equals", "bus_stop" ) )
                .setQueryString( l_queryBuilder.createTransformer( "src/main/xsd/query.xsl", environment.dataimport.Query.class ) )
                .getQueryString() );

    }

}
