package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/**
 *
 * @author tb006
 */
public class JSNode implements Node, IJSConversion {

    private JSObject node;

    public JSNode(JSObject node) {
        this.node = node;
    }

    @Override
    public String getNodeName() {
        return (String) getJSObject().getMember("nodeName");
    }

    @Override
    public String getNodeValue() throws DOMException {
        return (String) getJSObject().getMember("nodeValue");
    }

    @Override
    public void setNodeValue(String nodeValue) throws DOMException {
        getJSObject().setMember("nodeValue", nodeValue);
    }

    @Override
    public short getNodeType() {
        return (Short) getJSObject().getMember("nodeType");
    }

    @Override
    public Node getParentNode() {
        // TODO: get Node from the correct type
        return new JSNode((JSObject) getJSObject().getMember("parentNode"));
    }

    @Override
    public NodeList getChildNodes() {
        return new JSNodeList((JSObject) getJSObject().getMember("childNodes"));
    }

    @Override
    public Node getFirstChild() {
        return new JSNode((JSObject) getJSObject().getMember("firstChild"));
    }

    @Override
    public Node getLastChild() {
        return new JSNode((JSObject) getJSObject().getMember("lastChild"));
    }

    @Override
    public Node getPreviousSibling() {
        return new JSNode((JSObject) getJSObject().getMember("previousSibling"));
    }

    @Override
    public Node getNextSibling() {
        return new JSNode((JSObject) getJSObject().getMember("nextSibling"));
    }

    @Override
    public NamedNodeMap getAttributes() {
        return new JSAttrNamedNodeMap((JSObject) getJSObject().getMember("attributes"));
    }

    @Override
    public Document getOwnerDocument() {
        return new JSDocument((JSObject) getJSObject().getMember("ownerDocument"));
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        JSObject newJSChild = ((JSNode) newChild).getJSObject();
        JSObject refJSChild = ((JSNode) refChild).getJSObject();
        getJSObject().call("insertBefore", new Object[]{newJSChild, refJSChild});
        return this;
    }

    @Override
    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        JSObject newJSChild = ((JSNode) newChild).getJSObject();
        JSObject oldJSChild = ((JSNode) oldChild).getJSObject();
        getJSObject().call("replaceChild", new Object[]{newJSChild, oldJSChild});
        return this;
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        JSObject oldJSChild = ((JSNode) oldChild).getJSObject();
        getJSObject().call("removeChild", new Object[]{oldJSChild});
        return this;
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        getJSObject().call("appendChild", new Object[]{newChild});
        return this;
    }

    @Override
    public boolean hasChildNodes() {
        return (Boolean) getJSObject().call("hasChildNodes", null);
    }

    @Override
    public Node cloneNode(boolean deep) {
        return new JSNode((JSObject) getJSObject().call("cloneNode", new Object[]{deep}));
    }

    @Override
    public void normalize() {
        getJSObject().call("normalize", null);
    }

    @Override
    public boolean isSupported(String feature, String version) {
        return (Boolean) getJSObject().call("isSupported", new Object[]{feature, version});
    }

    @Override
    public String getNamespaceURI() {
        return (String) getJSObject().getMember("namespaceURI");
    }

    @Override
    public String getPrefix() {
        return (String) getJSObject().getMember("prefix");
    }

    @Override
    public void setPrefix(String prefix) throws DOMException {
        getJSObject().setMember("prefix", prefix);
    }

    @Override
    public String getLocalName() {
        return (String) getJSObject().getMember("localName");
    }

    @Override
    public boolean hasAttributes() {
        return (Boolean) getJSObject().call("hasAttributes", null);
    }

    @Override
    public String getBaseURI() {
        return (String) getJSObject().getMember("baseURI");
    }

    @Override
    public short compareDocumentPosition(Node other) throws DOMException {
        JSObject otherJS = ((JSNode) other).getJSObject();
        return (Short) getJSObject().call("compareDocumentPosition", new Object[]{otherJS});
    }

    @Override
    public String getTextContent() throws DOMException {
        return (String) getJSObject().getMember("textContent");
    }

    @Override
    public void setTextContent(String textContent) throws DOMException {
        getJSObject().setMember("textContent", textContent);
    }

    @Override
    public boolean isSameNode(Node other) {
        JSObject otherJS = ((JSNode) other).getJSObject();
        return (Boolean) getJSObject().call("isSameNode", new Object[]{otherJS});
    }

    @Override
    public String lookupPrefix(String namespaceURI) {
        return (String) getJSObject().call("lookupPrefix", new Object[]{namespaceURI});
    }

    @Override
    public boolean isDefaultNamespace(String namespaceURI) {
        return (Boolean) getJSObject().call("isDefaultNamespace", new Object[]{namespaceURI});
    }

    @Override
    public String lookupNamespaceURI(String prefix) {
        return (String) getJSObject().call("lookupNamespaceURI", new Object[]{prefix});
    }

    @Override
    public boolean isEqualNode(Node arg) {
        JSObject argJS = ((JSNode) arg).getJSObject();
        return (Boolean) getJSObject().call("isEqualNode", new Object[]{argJS});
    }

    @Override
    public Object getFeature(String feature, String version) {
        return getJSObject().call("getFeature", new Object[]{feature, version});
    }

    @Override
    public Object setUserData(String key, Object data, UserDataHandler handler) {
        JSObject handlerJS = ((JSUserDataHandler) handler).getJSObject();
        return getJSObject().call("setUserData", new Object[]{key, data, handlerJS});
    }

    @Override
    public Object getUserData(String key) {
        return getJSObject().call("getUserData", new Object[]{key});
    }

    @Override
    public JSObject getJSObject() {
        return node;
    }
}