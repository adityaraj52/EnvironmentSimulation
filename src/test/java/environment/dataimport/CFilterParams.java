package environment.dataimport;

import java.util.HashMap;

/**
 * Created by adityaraj on 11/08/16.
 * */

public class CFilterParams implements IFilterParams
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

    private HashMap<ETags, Iosmkey> m_hashTagMapper;
    private HashMap<ERoperator, IoperatorRelational> m_roperatorMapper;

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

        m_hashTagMapper = new HashMap<ETags, Iosmkey>();
        this.setHashTagMapper( m_hashTagMapper );

        m_roperatorMapper = new HashMap<ERoperator, IoperatorRelational>();
        this.setHashRoperatorMapper( m_roperatorMapper );
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

    /**
     * sets hashmap for tag values
     *
     * @param p_hashmap sets hash tag mapper
     */
    public void setHashTagMapper( final HashMap<ETags, Iosmkey> p_hashmap )
    {
        p_hashmap.put( ETags.highway, Iosmkey.HIGHWAY );
        p_hashmap.put( ETags.railways, Iosmkey.RAILWAYS );
        p_hashmap.put( ETags.building, Iosmkey.BUILDING );
        p_hashmap.put( ETags.junction, Iosmkey.JUNCTION );
        p_hashmap.put( ETags.tourism, Iosmkey.TOURISM );
        p_hashmap.put( ETags.waterways, Iosmkey.WATERWAYS );
    }

    /**
     * sets hashmap for R_Operator values
     *
     * @param p_hashmap sets hash tag mapper
     */
    public void setHashRoperatorMapper( final HashMap<ERoperator, IoperatorRelational> p_hashmap )
    {
        p_hashmap.put( ERoperator.equals, IoperatorRelational.EQUALS );
        p_hashmap.put( ERoperator.not_equals, IoperatorRelational.NOT_EQUALS );
    }
}
