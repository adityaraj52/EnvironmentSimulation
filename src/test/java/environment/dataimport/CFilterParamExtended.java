package environment.dataimport;

/**
 * Created by adityaraj on 11/08/16.
 */
public class CFilterParamExtended<T> extends CFilterParams
{
    public CFilterParamExtended( final ETags p_tag, final ERoperator p_operator, final String p_value )
    {
        super( p_tag, p_operator, p_value );
    }

    public void modifyEnumTags()
    {

    }

}
