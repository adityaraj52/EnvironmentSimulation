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
     * country code for query builder
     */
    private static final CQueryBuilder.ECountry COUNTRY = CQueryBuilder.ECountry.FR;

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

                new CQueryBuilder( COUNTRY )
                        //.rectangle( 50.745, 7.17, 50.75, 7.18 )
                        .circle( 50.745, 7.17, 100 )
                        //.polygon( 50.7, 7.1, 50.7, 7.2, 50.75, 7.15 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "bus_stop" )
                        .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "." )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        //.rectangle( 50.745, 7.17, 50.75, 7.18 )
                        .circle( 50.745, 7.17, 800 )
                        //.polygon( 50.7, 7.1, 50.7, 7.2, 50.75, 7.15 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "." )
                        .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.REGEXPR_EQUALS, "plat" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .polygon( 50.7, 7.1, 50.7, 7.2, 50.75, 7.15 )
                        //.rectangle( 50.745, 7.17, 50.75, 8.18 )
                        //.circle( 50.745, 7.17, 800 )
                        //.polygon( 50.7, 7.1, 50.7, 7.2, 50.75, 7.15 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "." )
                        .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.REGEXPR_NOT_EQUALS, "form" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .rectangle( 0.74794, 0.17, 89, 89.17333 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "residential" )
                        .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "stop_area" )
                        .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "light_rail" )
                        .filter( Ikey.SHOP, Ioperator.EQUALS, "coffee" )
                        .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "aquarium" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .rectangle( 50.7479, 7.172, 50.756, 7.17333 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "service" )
                        .filter( Ikey.PUBLIC_TRANSPORT, Ioperator.EQUALS, "user_defined" )
                        .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "monorail" )
                        .filter( Ikey.SHOP, Ioperator.NOT_EQUALS, "chocolate" )
                        .filter( Ikey.TOURISM, Ioperator.EQUALS, "gallery" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .circle( 40.745, 8.17, 900 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "primary_link" )
                        .filter( Ikey.BUILDING, Ioperator.NOT_EQUALS, "apartments" )
                        .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "miniature" )
                        .filter( Ikey.SHOP, Ioperator.EQUALS, "cheese" )
                        .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "hotel" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .rectangle( 50, 7.17, 50.75, 7.17333 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "secondary_link" )
                        .filter( Ikey.BUILDING, Ioperator.EQUALS, "farm" )
                        .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "tram" )
                        .filter( Ikey.SHOP, Ioperator.NOT_EQUALS, "dairy" )
                        .filter( Ikey.TOURISM, Ioperator.EQUALS, "information" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .rectangle( 50, 7.17, 50.75, 7.17333 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "living_street" )
                        .filter( Ikey.BUILDING, Ioperator.NOT_EQUALS, "hotel" )
                        .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "rail" )
                        .filter( Ikey.SHOP, Ioperator.EQUALS, "pastry" )
                        .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "museum" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .circle( 50.745, 7.17, 1000 )
                        .filter( Ikey.HIGHWAY, Ioperator.EQUALS, "raceway" )
                        .filter( Ikey.BUILDING, Ioperator.NOT_EQUALS, "house" )
                        .filter( Ikey.RAILWAYS, Ioperator.NOT_EQUALS, "subway" )
                        .filter( Ikey.TOURISM, Ioperator.EQUALS, "theme_park" )
                        .url(),

                new CQueryBuilder( COUNTRY )
                        .rectangle( 5.74, 3.1581, 50.7565, 77.1777 )
                        .filter( Ikey.HIGHWAY, Ioperator.NOT_EQUALS, "road" )
                        .filter( Ikey.BUILDING, Ioperator.EQUALS, "detached" )
                        .filter( Ikey.RAILWAYS, Ioperator.EQUALS, "preserved" )
                        .filter( Ikey.TOURISM, Ioperator.NOT_EQUALS, "viewpoint" )
                        .url()
                // @todo put for each OSM key and a combination of two different keys and
                // each bounding-box a new query-builder here (at least ( 10 keys + 5 key-pairs ) * 3 bounding-boxes = 45 test-cases,
                // mix-up with different operator like equals / not-equals / regular-expression-equals / regular-expression-not-equals, so
                // each operator should be used
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
