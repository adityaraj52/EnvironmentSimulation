package environment.dataimport;

import org.junit.Before;
import org.junit.Test;
import validator.CXmlValidator;
import validator.IXmlValidator;

import java.io.File;

/**
 * Created by adityaraj on 28/07/16.
 */
public class TestCXMLValidator
{

    private IXmlValidator m_xmlValidator;

    @Before
    public void before()
    {
        m_xmlValidator = new CXmlValidator();
    }

    @Test
    public void validate() throws Exception
    {
        m_xmlValidator.validateFile( new File( "src/main/xsd/query.xsd" ), new File( "src/main/xsd/instance1.xml" ) );
    }


}
