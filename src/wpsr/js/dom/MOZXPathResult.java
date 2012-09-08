package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Node;

/**
 *
 * @author tb006
 */
public class MOZXPathResult implements IMOZXPathResult, IJSConversion {

    JSObject result;

    public MOZXPathResult(JSObject result) {
        this.result = result;
    }

    @Override
    public Boolean getBooleanValue() {
        return (Boolean) getJSObject().getMember("booleanValue");
    }

    @Override
    public Boolean getInvalidIteratorState() {
        return (Boolean) getJSObject().getMember("invalidIteratorState");
    }

    @Override
    public double getNumberValue() {
        return (Double) getJSObject().getMember("numberValue");
    }

    @Override
    public int getResultType() {
        return (Integer) getJSObject().getMember("resultType");
    }

    @Override
    public Node getSingleNodeValue() {
        // TODO: get type of node and create the right Node
        return new JSNode((JSObject) getJSObject().getMember("singleNodeValue"));
    }

    @Override
    public int getSnapshotLength() {
        return (Integer) getJSObject().getMember("snapshotLength");
    }

    @Override
    public String getStringValue() {
        return (String) getJSObject().getMember("stringValue");
    }

    @Override
    public void iterateNext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Node snapshotItem(int index) {
        // TODO: get type of node and create the right Node
        return new JSNode((JSObject) getJSObject().call("snapshotItem", new Object[]{index}));
    }

    @Override
    public JSObject getJSObject() {
        return result;
    }
}
