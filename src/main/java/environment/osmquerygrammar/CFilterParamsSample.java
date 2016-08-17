package environment.osmquerygrammar;

/**
 * Created by adityaraj on 17.08.16.
 */
public class CFilterParamsSample implements IFilterParams
{
    /**
     * Define enum for possible relational operators
     */
    public enum ETagsSample
    {
        ABC, DEF, GHI
    }

    /**
     * Define enum for possible relational operators
     */
    public enum ERoperatorSample
    {
        EQ, N_EQ
    }

    private ETagsSample m_eTagsSample;
    private ERoperatorSample m_roperatorSample;
    private String m_string;

    CFilterParamsSample( final ETagsSample p_eTagsSample, final ERoperatorSample p_roperatorSample, final String p_string )
    {
        m_eTagsSample = p_eTagsSample;
        m_roperatorSample = p_roperatorSample;
        m_string = p_string;
    }

    @Override
    public String getTag()
    {
        return m_eTagsSample.toString();
    }

    @Override
    public String getROperator()
    {
        return m_roperatorSample.toString();
    }

    @Override
    public String getValue()
    {
        return m_string;
    }
}
