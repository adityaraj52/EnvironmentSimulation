package environment.osm;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;


/**
 * test for query
 */
@RunWith( DataProviderRunner.class )
public final class TestCQueryBuilder
{

    /**
     * query generator for defining filter queries
     *
     * @return array with query objects
     *
     * @throws Exception on any error
     */
    @DataProvider
    public static Object[] querygenerator() throws Exception
    {
        return Stream.of(


            new CQueryBuilder()
                .rectangle( 50.745, 7.17, 50.75, 7.18 )
                .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "bus_stop" )
                .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "platform" )
                .query()

            // @todo put for each OSM key and a combination of two different keys and each bounding-box a new query-builder here (at least ( 9 keys + 4 key-pairs ) * 3 bounding-boxes = 39 test-cases


        ).toArray();
    }


    /**
     * query testing
     *
     * @throws IOException on failure
     */
    @Test
    @UseDataProvider( "querygenerator" )
    public final void testquery( final URL p_url ) throws IOException
    {
        System.out.println( p_url.getContent() );
    }

    /**
     * main test
     *
     * @param p_args CLI arguments
     * @throws Exception is thrown on initializing error
     */
    @SuppressWarnings( "unchecked" )
    public static void main( final String[] p_args ) throws Exception
    {
        Arrays.stream( querygenerator() )
              .parallel()
              .forEach( i -> {
                  try
                  {
                      new TestCQueryBuilder().testquery( (URL) i );
                  }
                  catch ( final IOException l_exception )
                  {
                      throw new UncheckedIOException( l_exception );
                  }
              } );
    }
}
