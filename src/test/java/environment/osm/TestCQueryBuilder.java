package environment.osm;

import org.junit.Test;

/**
 * test for query
 */
public final class TestCQueryBuilder
{
    /**
     * query testing
     *
     * @throws Exception on failure
     */
    @Test
    public final void buildQuery() throws Exception
    {
        System.out.println(
                new CQueryBuilder()
                        .rectangle( 50.745, 7.17, 50.75, 7.18 )
                        //.circle( 0, 0, 50 )
                        //.polygon( 10, 11, 12, 13, 14, 15, 10, 11 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "bus_stop" )
                        .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "platform" )
                        .query()

        );
    }

    /**
     * main test
     * @param p_args CLI arguments
     * @throws Exception is thrown on initializing error
     */
    public static void main( final String[] p_args ) throws Exception
    {
        new TestCQueryBuilder().buildQuery();
    }
}
