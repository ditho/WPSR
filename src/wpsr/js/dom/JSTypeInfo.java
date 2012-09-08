package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.TypeInfo;

/**
 *
 * @author tb006
 */
public class JSTypeInfo implements TypeInfo, IJSConversion {

    private JSObject typeInfo;

    public JSTypeInfo(JSObject typeInfo) {
        this.typeInfo = typeInfo;
    }

    @Override
    public String getTypeName() {
       return (String) getJSObject().getMember("typeName");
    }

    @Override
    public String getTypeNamespace() {
        return (String) getJSObject().getMember("typeNamespace");
    }

    @Override
    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return (Boolean) getJSObject().call("isDerivedFrom", new Object[]{typeNamespaceArg, typeNameArg, derivationMethod});
    }

    @Override
    public JSObject getJSObject() {
        return typeInfo;
    }
}