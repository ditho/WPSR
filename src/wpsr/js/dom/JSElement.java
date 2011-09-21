package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

/**
 *
 * @author tb006
 */
public class JSElement extends JSNode implements Element {

    public JSElement(JSObject element) {
        super(element);
    }

    @Override
    public String getTagName() {
        return (String) getJSObject().getMember("tagName");
    }

    @Override
    public String getAttribute(String name) {
        return (String) getJSObject().call("getAttribute", new Object[]{name});
    }

    @Override
    public void setAttribute(String name, String value) throws DOMException {
        getJSObject().call("setAttribute", new Object[]{name, value});
    }

    @Override
    public void removeAttribute(String name) throws DOMException {
        getJSObject().call("removeAttribute", new Object[]{name});
    }

    @Override
    public Attr getAttributeNode(String name) {
        return new JSAttr((JSObject) getJSObject().call("getAttributeNode", new Object[]{name}));
    }

    @Override
    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        JSObject newAttrJS = ((JSAttr) newAttr).getJSObject();
        return new JSAttr((JSObject) getJSObject().call("setAttributeNode", new Object[]{newAttrJS}));
    }

    @Override
    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        JSObject oldAttrJS = ((JSAttr) oldAttr).getJSObject();
        return new JSAttr((JSObject) getJSObject().call("removeAttributeNode", new Object[]{oldAttrJS}));
    }

    @Override
    public NodeList getElementsByTagName(String name) {
        return new JSNodeList((JSObject) getJSObject().call("getElementsByTagName", new Object[]{name}));
    }

    @Override
    public String getAttributeNS(String namespaceURI, String localName) throws DOMException {
        return (String) getJSObject().call("getAttributeNS", new Object[]{namespaceURI, localName});
    }

    @Override
    public void setAttributeNS(String namespaceURI, String qualifiedName, String value) throws DOMException {
        getJSObject().call("setAttributeNS", new Object[]{namespaceURI, qualifiedName, value});
    }

    @Override
    public void removeAttributeNS(String namespaceURI, String localName) throws DOMException {
        getJSObject().call("removeAttributeNS", new Object[]{namespaceURI, localName});
    }

    @Override
    public Attr getAttributeNodeNS(String namespaceURI, String localName) throws DOMException {
        return new JSAttr((JSObject) getJSObject().call("getAttributeNodeNS", new Object[]{namespaceURI, localName}));
    }

    @Override
    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
        JSObject newAttrJS = ((JSAttr) newAttr).getJSObject();
        return new JSAttr((JSObject) getJSObject().call("setAttributeNodeNS", new Object[]{newAttrJS}));
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) throws DOMException {
        return new JSNodeList((JSObject) getJSObject().call("getElementsByTagNameNS", new Object[]{namespaceURI, localName}));
    }

    @Override
    public boolean hasAttribute(String name) {
        return (Boolean) getJSObject().call("hasAttribute", new Object[]{name});
    }

    @Override
    public boolean hasAttributeNS(String namespaceURI, String localName) throws DOMException {
        return (Boolean) getJSObject().call("hasAttributeNS", new Object[]{namespaceURI, localName});
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        return new JSTypeInfo((JSObject) getJSObject().getMember("schameTypeInfo"));
    }

    @Override
    public void setIdAttribute(String name, boolean isId) throws DOMException {
        getJSObject().call("setIdAttribute", new Object[]{name, isId});
    }

    @Override
    public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) throws DOMException {
        getJSObject().call("setIdAttributeNS", new Object[]{namespaceURI, localName, isId});
    }

    @Override
    public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
        JSObject idAttrJS = ((JSAttr) idAttr).getJSObject();
        getJSObject().call("setIdAttributeNode", new Object[]{idAttrJS, isId});
    }
}