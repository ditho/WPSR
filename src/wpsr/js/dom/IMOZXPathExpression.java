package wpsr.js.dom;

import netscape.javascript.JSObject;

/**
 *
 * @author tb006
 */
public interface IMOZXPathExpression {
    IMOZXPathResult evaluate(JSObject context, short type, JSObject result);
}
