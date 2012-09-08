package wpsr.js.dom;

import org.w3c.dom.Document;

/**
 *
 * @author tb006
 */
public interface IMOZDocument extends  Document {
    IMOZXPathExpression createExpression(String xpathText, Object namespaceURLMapper);
}
