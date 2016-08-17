package environment.osmquerygrammar;

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
        HIGHWAY, TOURISM, BUILDING, SHOP, WATERWAYS, RAILWAYS, JUNCTION
    }

    /**
     * Define enum for possible relational operators
     */
    public enum ERoperator
    {
        EQUALS, NOT_EQUALS
    }

    private ETags m_tag;
    private ERoperator m_operator;
    private String m_value;

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
    public String getTag()
    {
        return m_tag.toString();
    }

    /**
     * get tag value
     * @return operator
     *
     */
    public String getROperator()
    {
        return m_operator.toString();
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
