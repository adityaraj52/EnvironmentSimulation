package environment.osm;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.text.MessageFormat;
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

            //new URL( "http://overpass-api.de/api/interpreter?data=node(50.745,7.17,50.75,7.18)[highway=\"bus_stop\"][public_transport=\"platform\"];out;" ),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                .rectangle( 50.745, 7.17, 50.75, 7.18 )
                .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "." )
                .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "*" )
                .url(),

            // @todo put for each OSM key and a combination of two different keys and
            // each bounding-box a new query-builder here (at least ( 10 keys + 5 key-pairs ) * 3 bounding-boxes = 45 test-cases,
            // mix-up with different operator like equals / not-equals / regular-expression-equals / regular-expression-not-equals, so
            // each operator should be used

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                .rectangle( 50.74794, 7.17, 50.75, 7.17333 )
                .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "trunk" )
                .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "stop_position" )
                .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "abandoned" )
                .filter( Ikey.SHOP, Ioperator.REGEXPR_NOT_EQUALS, "cohol" )
                .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 35.74794, 9.17, 36.75, 10.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "secondary" )
                    .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "platform" )
                    .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "construction" )
                    .filter( Ikey.SHOP, Ioperator.EQUALS, "bakery" )
                    .filter( Ikey.TOURISM, Ioperator.REGEXPR_NOT_EQUALS, "apart$" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 45.74794, 6.17, 46.75, 8.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.NOT_EQUALS, "unclassified" )
                    .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "station" )
                    .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "disused" )
                    .filter( Ikey.SHOP, Ioperator.NOT_EQUALS, "beverages" )
                    .filter( Ikey.TOURISM, Ioperator.EQUALS, "artwork" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.74794, 7.17, 50.75, 7.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "residential" )
                    .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "stop_area" )
                    .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "light_rail" )
                    .filter( Ikey.SHOP, Ioperator.EQUALS, "coffee" )
                    .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "aquarium" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.7479, 7.172, 50.756, 7.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "service" )
                    .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "user_defined" )
                    .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "monorail" )
                    .filter( Ikey.SHOP, Ioperator.NOT_EQUALS, "chocolate" )
                    .filter( Ikey.TOURISM, Ioperator.EQUALS, "gallery" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.747, 7.078, 50.79, 7.17399 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "primary_link" )
                    .filter( Ikey.BUILDING, Ioperator.NOT_EQUALS, "apartments" )
                    .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "miniature" )
                    .filter( Ikey.SHOP, Ioperator.EQUALS, "cheese" )
                    .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "hotel" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.74794, 7.17, 50.75, 7.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "secondary_link" )
                    .filter( Ikey.BUILDING, Ioperator.EQUALS, "farm" )
                    .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "tram" )
                    .filter( Ikey.SHOP, Ioperator.NOT_EQUALS, "dairy" )
                    .filter( Ikey.TOURISM, Ioperator.EQUALS, "information" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.74794, 7.17, 50.75, 7.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "living_street" )
                    .filter( Ikey.BUILDING, Ioperator.NOT_EQUALS, "hotel" )
                    .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "rail" )
                    .filter( Ikey.SHOP, Ioperator.EQUALS, "pastry" )
                    .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "museum" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.74, 7.1581, 50.7565, 7.1777 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "raceway" )
                    .filter( Ikey.BUILDING, Ioperator.NOT_EQUALS, "house" )
                    .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "subway" )
                    .filter( Ikey.TOURISM, Ioperator.EQUALS, "theme_park" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.74, 7.1581, 50.7565, 7.1777 )
                    .filter( Ikey.HIGHWAY, Ioperator.NOT_EQUALS, "road" )
                    .filter( Ikey.BUILDING, Ioperator.EQUALS, "detached" )
                    .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "preserved" )
                    .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "viewpoint" )
                    .url(),

            new CQueryBuilder( CQueryBuilder.ECountry.FR )
                    .rectangle( 50.7, 7.1, 50.79, 7.17333 )
                    .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "steps" )
                    .filter( Ikey.BUILDING, Ioperator.EQUALS, "dormitory" )
                    .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "abandoned" )
                    .filter( Ikey.TOURISM, Ioperator.EQUALS, "yes" )
                    .url()
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
        System.out.println( MessageFormat.format( ">>=== {0} ", p_url ) );

        final InputStreamReader l_stream = new InputStreamReader( p_url.openConnection().getInputStream() );
        new BufferedReader( l_stream ).lines().forEach( System.out::println );
        l_stream.close();

        System.out.println( "\n" );
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
              .forEach( i ->
              {
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
