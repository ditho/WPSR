package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

/**
 *
 * @author tb006
 */
public class JSAttr extends JSNode implements Attr {

    public JSAttr(JSObject attribute) {
        super(attribute);
    }

    @Override
    public String getName() {
        return (String) getJSObject().getMember("name");
    }

    @Override
    public boolean getSpecified() {
        return (Boolean) getJSObject().getMember("specified");
    }

    @Override
    public String getValue() {
        return (String) getJSObject().getMember("value");
    }

    @Override
    public void setValue(String value) throws DOMException {
        getJSObject().setMember("value", value);
    }

    @Override
    public Element getOwnerElement() {
        return new JSElement((JSObject) getJSObject().getMember("ownerElement"));
    }

    @Override
    public TypeInfo getSchemaTypeInfo() {
        return new JSTypeInfo((JSObject) getJSObject().getMember("schemaTypeInfo"));
    }

    @Override
    public boolean isId() {
        return (Boolean) getJSObject().getMember("isId");
    }
}