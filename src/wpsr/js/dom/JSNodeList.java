package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author tb006
 */
public class JSNodeList implements NodeList, IJSConversion {

    private JSObject nodeList;

    public JSNodeList(JSObject nodeList) {
        this.nodeList = nodeList;
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
    public JSObject getJSObject() {
        return nodeList;
    }
}