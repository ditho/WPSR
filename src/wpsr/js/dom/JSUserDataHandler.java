package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

/**
 *
 * @author tb006
 */
public class JSUserDataHandler implements UserDataHandler, IJSConversion {

    private JSObject userDataHandler;

    public JSUserDataHandler(JSObject userDataHandler) {
        this.userDataHandler = userDataHandler;
    }

    @Override
    public void handle(short operation, String key, Object data, Node src, Node dst) {
        getJSObject().call("handle", new Object[]{operation, key, data, src, dst});
    }

    @Override
    public JSObject getJSObject() {
        return userDataHandler;
    }
}