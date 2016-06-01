package environment.dataimport;

import environment.dataimport.source.Osm;
import org.junit.Test;
import org.openstreetmap.osmosis.tagfilter.v0_6.TagFilter;
import org.openstreetmap.osmosis.tagfilter.v0_6.TagFilterFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * test for reader test
 */
public final class TestCReader
{

    /**
     * test for OSM bounding box
     *
     * @throws IOException on io errors
     * @throws JAXBException on XML errors
     */
    @Test
    public void testOSM() throws IOException, JAXBException
    {
        final CReader<Osm> l_osm = new CReader( Osm.class, new URL( "http://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145" ) );
        System.out.println( l_osm.get().getVersion() );
        System.out.println( l_osm.get().getBounds() );


        final TagFilterFactory l_filter = new TagFilterFactory();

        final Set<String> l_tag = new HashSet<>();
        final Set<String> l_tagvalue = new HashSet<>();

        final Map<String, Set<String>> l_tagkeyvalues = new HashMap<>();

        l_tag.add( "tag-filter" );

        l_tagvalue.add( "residential" );

        l_tagkeyvalues.put( "tag-filter", l_tagvalue );


        final TagFilter l_tagfilter = new TagFilter( "accept-way", l_tag, l_tagkeyvalues );
        /*
        //tf.initialize("accept-way", );
        l_tagfilter.complete();
        */
    }

}
