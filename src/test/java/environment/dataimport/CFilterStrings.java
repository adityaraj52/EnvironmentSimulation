package environment.dataimport;

import java.util.HashMap;

/**
 * Created by adityaraj on 11/08/16.
 * */

public class CFilterStrings
{
    private String m_tag;
    private String m_operator;
    private String m_value;

    private HashMap<String, Iosmkey> m_hashTagMapper = new HashMap<String, Iosmkey>()
    {
        {
            put( "highway", Iosmkey.HIGHWAY );
            put( "railway", Iosmkey.RAILWAYS );
            put( "building", Iosmkey.BUILDING );
            put( "junction", Iosmkey.JUNCTION );
            put( "tourism", Iosmkey.TOURISM );
            put( "waterays", Iosmkey.WATERWAYS );
        }
    };

    private HashMap<String, IoperatorRelational> m_roperatorMapper = new HashMap<String, IoperatorRelational>()
    {
        {
            put( "equals", IoperatorRelational.EQUALS );
            put( "not equals", IoperatorRelational.NOT_EQUALS );
        }
    };

    private HashMap<String, IoperatorRelational> m_filterRoperatorMapper;

    CFilterStrings( final String p_tag, final String p_operator, final String p_value )
    {
        m_tag = p_tag;
        m_operator = p_operator;
        m_value = p_value;
    }

    /**
     * get tag value
     * @return tag value
     */
    public Iosmkey getTag()
    {
        return m_hashTagMapper.get( m_tag );
    }

    /**
     * get tag value
     * @return operator
     *
     */
    public IoperatorRelational getROperator()
    {
        return m_roperatorMapper.get( m_operator );
    }

    /**
     * get tag value
     * @return value
     *
     */
    public String getValue()
    {
        return m_value;
    }
}
