package environment.dataimport;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.osmosis.tagfilter.v0_6.TagFilter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * test for OSM test
 */
public final class TestCOSMReader
{
    /**
     * OSM source
     */
    private ISource<Osm> m_source;

    /**
     * initialization before the test is running
     *
     * @throws MalformedURLException on URL malformat
     */
    @Before
    public void before() throws MalformedURLException
    {
        m_source = new COpenStreetMap( 11.54, 48.14, 11.543, 48.145 );
    }


    /**
     * test for OSM bounding box
     *
     * @throws IOException on io reading
     * @throws JAXBException on XML parsing error
     */
    @Test
    public void testOSMRead() throws IOException, JAXBException
    {
        System.out.println( "\nTest to create a sample xml file in target/map.osm location based on OSM Schema" );
        Assume.assumeNotNull( m_source );

        final Osm l_version = new Osm();
        l_version.setCopyright( "hello" );
        JAXBContext.newInstance( Osm.class ).createMarshaller().marshal( l_version, new File( "target/map.osm" ) );
        final IReader<Osm> l_osm = new CXMLReader<>( m_source );
        System.out.println( l_osm.get().getVersion() );
        System.out.println( l_osm.get().getBounds() );
    }


    /**
     * test for OSM tag filter
     *
     * @throws IOException on io reading
     * @throws JAXBException on XML parsing error
     */
    @Test
    public void testTagFilter() throws IOException, JAXBException
    {
        System.out.println( "\nTest to check tag filter (Still has to tested)\n" );
        Assume.assumeNotNull( m_source );

        final IReader<Osm> l_osm = new CXMLReader<>( m_source );
        final Multimap<String, String> l_tagfilter = HashMultimap.create();
        l_tagfilter.put( "tag-filter", "residential" );

        final TagFilter l_filter = new TagFilter(
                "accept-way",
                l_tagfilter.keySet(),
                l_tagfilter
                        .asMap()
                        .entrySet()
                        .parallelStream()
                        .collect(
                                Collectors.toMap( Map.Entry::getKey, i -> new HashSet<>( i.getValue() ) )
                        )
        );
    }

    /**
     * test for OSM file unmarshalling and reading the contents from the object tree
     *
     * @throws IOException on io reading
     * @throws JAXBException on XML parsing error
     */
    @Test
    public void unmarshall() throws IOException, JAXBException
    {
        System.out.println( "\nTest to read a sample xml file stored in xsd/map.osm location and further store the elements in OSM class" );
        final Osm l_read = (Osm)JAXBContext.newInstance( Osm.class ).createUnmarshaller( ).unmarshal( new File( "src/main/xsd/map.osm" ) );
        System.out.println( l_read.getVersion() );
        System.out.println( l_read.copyright + "\n" );

    }


}
