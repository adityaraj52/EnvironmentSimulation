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

        //Define a circular bounding region with centre latitude, longitude followed by
        //radius of the region to look for.

        //Get the query string by
        //1. set bounding box
        //2. set filter stream
        //3. set queryString by chosing the right transformation files specified in order XSLT file and
        // then the JAXB generated Class
        //4. Get the Query String

        System.out.println(
                l_queryBuilder
                        //.defineRectangle( 12, 11, 14, 13 )
                        .defineCircle( 0, 0, 50 )
                        .defineList( 10, 11, 12, 13, 14, 15, 10, 11 )
                .setFiltersStream( new CFilterParams<Iosmkey, IoperatorRelational>( CFilterParams.ETags.highway, CFilterParams.ERoperator.equals, "primary" ),
                        new CFilterParams( CFilterParams.ETags.highway, CFilterParams.ERoperator.not_equals, "bus_stop" ) )
                .setQueryString()
                .getQueryString() );

    }

}
