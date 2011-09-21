package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;

/**
 *
 * @author tb006
 */
public class JSDocumentType extends JSNode implements DocumentType {

    public JSDocumentType(JSObject docType) {
        super(docType);
    }

    @Override
    public String getName() {
        return (String) getJSObject().getMember("name");
    }

    @Override
    public NamedNodeMap getEntities() {
        // TODO: implement entities and JSEntitiesNamedNodeMap
        return new JSNamedNodeMap((JSObject) getJSObject().getMember("entities"));
    }

    @Override
    public NamedNodeMap getNotations() {
        // TODO: implement notations and JSNotationsNamedNodeMap
        return new JSNamedNodeMap((JSObject) getJSObject().getMember("notations"));
    }

    @Override
    public String getPublicId() {
        return (String) getJSObject().getMember("publicId");
    }

    @Override
    public String getSystemId() {
        return (String) getJSObject().getMember("systemId");
    }

    @Override
    public String getInternalSubset() {
        return (String) getJSObject().getMember("internalSubset");
    }
}