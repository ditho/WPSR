package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

/**
 *
 * @author tb006
 */
public class JSDOMImplementation implements DOMImplementation, IJSConversion {

    private JSObject impl;

    public JSDOMImplementation(JSObject impl) {
        this.impl = impl;
    }

    @Override
    public boolean hasFeature(String feature, String version) {
        return (Boolean) getJSObject().call("hasFeature", new Object[]{feature, version});
    }

    @Override
    public DocumentType createDocumentType(String qualifiedName, String publicId, String systemId) throws DOMException {
        return new JSDocumentType((JSObject) getJSObject().call("createDocumentType", new Object[]{qualifiedName, publicId, systemId}));
    }

    @Override
    public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
        JSObject doctypeJS = ((JSDocumentType) doctype).getJSObject();
        return new JSDocument((JSObject) getJSObject().call("createDocument", new Object[]{namespaceURI, qualifiedName, doctypeJS}));
    }

    @Override
    public Object getFeature(String feature, String version) {
        return getJSObject().call("getFeature", new Object[]{feature, version});
    }

    @Override
    public JSObject getJSObject() {
        return impl;
    }
}