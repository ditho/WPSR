package wpsr.js.dom;

import netscape.javascript.JSObject;

/**
 *
 * @author tb006
 */
public class MOZDocument extends JSDocument implements IMOZDocument {

    public MOZDocument(JSObject document) {
        super(document);
    }

    @Override
    public IMOZXPathExpression createExpression(String xpathText, Object namespaceURLMapper) {
        return new MOZXPathExpression((JSObject) getJSObject().call("createExpression", new Object[]{xpathText, namespaceURLMapper}));
    }
}