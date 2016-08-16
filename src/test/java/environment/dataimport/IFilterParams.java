package environment.dataimport;

import java.util.HashMap;

/**
 * Created by adityaraj on 16/08/16.
 */
public interface IFilterParams
{
    /**
     * Define enum for possible tag values
     */
    public static enum ETags
    {
        highway, railways, waterways, building, junction, tourism
    }

    /**
     * Define enum for possible relational operators
     */
    public static enum ERoperator
    {
        equals, not_equals
    }

    /**
     * get tag value
     * @return tag value
     */
    Iosmkey getTag();

    /**
     * get tag value
     * @return operator
     *
     */
    IoperatorRelational getROperator();

    /**
     * get tag value
     * @return value
     *
     */
    String getValue();

    /**
     * sets hashmap for R_Operator values
     *
     * @param p_hashmap sets hash tag mapper
     */
    public void setHashRoperatorMapper( final HashMap<ERoperator, IoperatorRelational> p_hashmap );

    /**
     * sets hashmap for tag values
     *
     * @param p_hashmap sets hash tag mapper
     */
    public void setHashTagMapper( final HashMap<ETags, Iosmkey> p_hashmap );

}
