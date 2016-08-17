package environment.dataimport;

/**
 * Created by adityaraj on 16/08/16.
 */
public interface IFilterParams
{
    /**
     * get tag value
     * @return tag value
     */
    String getTag();

    /**
     * get tag value
     * @return operator
     *
     */
    String getROperator();

    /**
     * get tag value
     * @return value
     *
     */
    String getValue();

}
