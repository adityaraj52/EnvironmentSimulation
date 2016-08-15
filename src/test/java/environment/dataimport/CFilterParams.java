package environment.dataimport;

import java.util.HashMap;

/**
 * Created by adityaraj on 11/08/16.
 * */

public class CFilterParams<K extends Iosmkey, R extends IoperatorRelational>
{
    /**
     * Define enum for possible tag values
     */
    public enum ETags
    {
        highway, railways, waterways, building, junction, tourism
    }

    /**
     * Define enum for possible relational operators
     */
    public enum ERoperator
    {
        equals, not_equals
    }

    private ETags m_tag;
    private ERoperator m_operator;
    private String m_value;


    private HashMap<ETags, K> m_hashTagMapper = new HashMap<ETags, K>()
    {
        {
            put( ETags.highway, (K) K.HIGHWAY );
            put( ETags.railways, (K) K.RAILWAYS );
            put( ETags.building, (K) K.BUILDING );
            put( ETags.junction, (K) K.JUNCTION );
            put( ETags.tourism, (K) K.TOURISM );
            put( ETags.waterways, (K) K.WATERWAYS );
        }
    };

    private HashMap<ERoperator, R> m_roperatorMapper = new HashMap<ERoperator, R>()
    {
        {
            put( ERoperator.equals, (R) R.EQUALS );
            put( ERoperator.not_equals, (R) R.NOT_EQUALS );
        }
    };

    /**
     * Default constructor
     *
     * @param p_tag tag
     * @param p_operator operator
     * @param p_value value
     */
    public CFilterParams( final ETags p_tag, final ERoperator p_operator, final String p_value )
    {
        m_tag = p_tag;
        m_operator = p_operator;
        m_value = p_value;
    }

    /**
     * get tag value
     * @return tag value
     */
    protected K getTag()
    {
        return m_hashTagMapper.get( m_tag );
    }

    /**
     * get tag value
     * @return operator
     *
     */
    protected R getROperator()
    {
        return m_roperatorMapper.get( m_operator );
    }

    /**
     * get tag value
     * @return value
     *
     */
    protected String getValue()
    {
        return m_value;
    }

    protected HashMap setHashMap()
    {
        return this.m_hashTagMapper;
    }
}
