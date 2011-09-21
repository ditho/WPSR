package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMStringList;

/**
 * This is not implemented in mozilla
 *
 * @author tb006
 */
public class JSDOMStringList implements DOMStringList, IJSConversion {

    private JSObject stringList;

    public JSDOMStringList(JSObject stringList) {
        this.stringList = stringList;
    }

    @Override
    public String item(int index) {
        return (String) stringList.getSlot(index);
    }

    @Override
    public int getLength() {
        return (Integer) stringList.getMember("length");
    }

    @Override
    public boolean contains(String str) {
        return (Boolean) stringList.call("contains", new Object[]{str});
    }

    @Override
    public JSObject getJSObject() {
        return stringList;
    }
}