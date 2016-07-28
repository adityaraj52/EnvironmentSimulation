package validator;

import java.io.File;

/**
 * Created by adityaraj on 27/07/16.
 */
public interface IXmlValidator
{
    public void validateFile( final File p_xsd, final File p_xml ) throws Exception;

    public void validateStream( File p_xsd, File p_xml ) throws Exception;
}
