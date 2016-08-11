package environment.dataimport;

import java.util.HashMap;

/**
 * Created by adityaraj on 11/08/16.
 * */

public class CFilterParams
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


    private HashMap<ETags, Iosmkey> m_hashTagMapper = new HashMap<ETags, Iosmkey>()
    {
        {
            put( ETags.highway, Iosmkey.HIGHWAY );
            put( ETags.railways, Iosmkey.RAILWAYS );
            put( ETags.building, Iosmkey.BUILDING );
            put( ETags.junction, Iosmkey.JUNCTION );
            put( ETags.tourism, Iosmkey.TOURISM );
            put( ETags.waterways, Iosmkey.WATERWAYS );
        }
    };

    private HashMap<ERoperator, IoperatorRelational> m_roperatorMapper = new HashMap<ERoperator, IoperatorRelational>()
    {
        {
            put( ERoperator.equals, IoperatorRelational.EQUALS );
            put( ERoperator.not_equals, IoperatorRelational.NOT_EQUALS );
        }
    };

    private HashMap<ERoperator, IoperatorRelational> m_filterRoperatorMapper;

    CFilterParams( final ETags p_tag, final ERoperator p_operator, final String p_value )
    {
        m_tag = p_tag;
        m_operator = p_operator;
        m_value = p_value;
    }

    /**
     * get tag value
     * @return tag value
     */
    protected Iosmkey getTag()
    {
        return m_hashTagMapper.get( m_tag );
    }

    /**
     * get tag value
     * @return operator
     *
     */
    protected IoperatorRelational getROperator()
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
}
