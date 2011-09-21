package wpsr.readers;

import java.net.URI;

/**
 * Load up some properties from an XML-File.
 *
 * @author tb006
 */
public interface IXMLPropertiesReader {

    /**
     * Get a Property readed from the resource
     *
     * @param key
     * @return
     */
    String getProperty(String key);

    /**
     * Get the URI of the resource object for some debugging.
     *
     * @return
     */
    URI getURI();
}
