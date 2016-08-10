package environment.datatraffic;

import com.google.common.collect.Multimap;
import environment.dataimport.Iosmkey;

/**
 * Created by adityaraj on 03.06.16.
 */
public class CCHighwayRoad
{
    private Multimap<String, Iosmkey> m_value;

    public CCHighwayRoad()
    {
        m_value.containsEntry( "highway", Iosmkey.HIGHWAY );
    }

    public void displayValue( final String p_string )
    {
        System.out.println( m_value.get( p_string ) );
    }
}
