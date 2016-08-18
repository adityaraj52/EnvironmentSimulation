package environment.dataimport;

import environment.osm.CQueryBuilder;
import environment.osm.IoperatorBoolean;
import environment.osm.IoperatorRelational;
import environment.osm.Iosmkey;
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
    public void buildQuery() throws Exception
    {
        System.out.println(
                new CQueryBuilder()
                        .rectangle( 12, 11, 14, 13 )
                        //.circle( 0, 0, 50 )
                        //.polygon( 10, 11, 12, 13, 14, 15, 10, 11 )
                        .filter( Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "primary" )
                        .filter( IoperatorBoolean.AND, Iosmkey.HIGHWAY, IoperatorRelational.EQUALS, "bus_stop" )
                        .filter( Iosmkey.SHOP, IoperatorRelational.EQUALS, "MCD" )
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
