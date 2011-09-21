package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

/**
 *
 * @author tb006
 */
public class JSText extends JSCharacterData implements Text {

    public JSText(JSObject text) {
        super(text);
    }

    @Override
    public Text splitText(int offset) throws DOMException {
        return new JSText((JSObject) getJSObject().call("splitText", new Object[]{offset}));
    }

    @Override
    public boolean isElementContentWhitespace() {
        return (Boolean) getJSObject().getMember("isElementContentWhitespace");
    }

    @Override
    public String getWholeText() {
        return (String) getJSObject().getMember("wholeText");
    }

    @Override
    public Text replaceWholeText(String content) throws DOMException {
        return new JSText((JSObject) getJSObject().call("replaceWholeText", new Object[]{content}));
    }
}