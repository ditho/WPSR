package wpsr.readers;

import java.io.IOException;
import java.net.URI;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

/**
 * Load up some properties from an XML-File.
 *
 * @author tb006
 */
public class XMLPropertiesReader implements IXMLPropertiesReader, SharedResourceObject {

    /**
     * The Log4J logger.
     */
    static Logger logger = Logger.getLogger(XMLPropertiesReader.class);
    /**
     * Map of all Properties
     */
    private Properties prop;
    /**
     * The URI of the resource object
     */
    private URI uri;

    /**
     * {@inheritDoc }
     */
    @Override
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public URI getURI() {
        return uri;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void load(DataResource aData) throws ResourceInitializationException {
        try {
            uri = aData.getUri();
            prop = new Properties();
            prop.loadFromXML(aData.getInputStream());
        } catch (InvalidPropertiesFormatException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}
