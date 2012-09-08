package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;

/**
 * 
 *
 * @author tb006
 */
public class JSDOMConfiguration implements DOMConfiguration, IJSConversion {

    private JSObject config;

    public JSDOMConfiguration(JSObject config) {
        this.config = config;
    }

    @Override
    public void setParameter(String name, Object value) throws DOMException {
        config.call("setParameter", new Object[]{name, value});
    }

    @Override
    public Object getParameter(String name) throws DOMException {
        return config.call("getParameter", new Object[]{name});
    }

    @Override
    public boolean canSetParameter(String name, Object value) {
        return (Boolean) config.call("canSetParameter", new Object[]{name, value});
    }

    @Override
    public DOMStringList getParameterNames() {
        return new JSDOMStringList((JSObject) config.getMember("parameterNames"));
    }

    @Override
    public JSObject getJSObject() {
        return config;
    }
}