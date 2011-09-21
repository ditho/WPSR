/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wpsr.js.dom;

import netscape.javascript.JSObject;

/**
 *
 * @author tb006
 */
public class MOZXPathExpression implements IMOZXPathExpression, IJSConversion {

    JSObject expression;

    MOZXPathExpression(JSObject expression) {
        this.expression = expression;
    }

    @Override
    public IMOZXPathResult evaluate(JSObject context, short type, JSObject result) {
        return new MOZXPathResult((JSObject) getJSObject().call("evaluate", new Object[]{context, type, result}));
    }

    @Override
    public JSObject getJSObject() {
        return expression;
    }
}