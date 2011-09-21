package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author tb006
 */
public class JSNamedNodeMap implements NamedNodeMap, IJSConversion {

    private JSObject namedNodeMap;

    public JSNamedNodeMap(JSObject namedNodeMap) {
        this.namedNodeMap = namedNodeMap;
    }

    @Override
    public Node getNamedItem(String name) {
        return new JSNode((JSObject) getJSObject().call("getNamedItem", new Object[]{name}));
    }

    @Override
    public Node setNamedItem(Node arg) throws DOMException {
        JSObject argJS = ((JSNode) arg).getJSObject();
        return new JSNode((JSObject) getJSObject().call("setNamedItem", new Object[]{argJS}));
    }

    @Override
    public Node removeNamedItem(String name) throws DOMException {
        return new JSNode((JSObject) getJSObject().call("removeNamedItem", new Object[]{name}));
    }

    @Override
    public Node item(int index) {
        return new JSNode((JSObject) getJSObject().getSlot(index));
    }

    @Override
    public int getLength() {
        return (Integer) getJSObject().getMember("length");
    }

    @Override
    public Node getNamedItemNS(String namespaceURI, String localName) throws DOMException {
        return new JSNode((JSObject) getJSObject().call("getNamedItemNS", new Object[]{namespaceURI, localName}));
    }

    @Override
    public Node setNamedItemNS(Node arg) throws DOMException {
        JSObject argJS = ((JSNode) arg).getJSObject();
        return new JSNode((JSObject) getJSObject().call("setNamedItemNS", new Object[]{argJS}));
    }

    @Override
    public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
        return new JSNode((JSObject) getJSObject().call("removeNamedItemNS", new Object[]{namespaceURI, localName}));
    }

    @Override
    public JSObject getJSObject() {
        return namedNodeMap;
    }
}