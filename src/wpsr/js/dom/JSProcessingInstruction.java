package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

/**
 *
 * @author tb006
 */
public class JSProcessingInstruction  extends JSNode implements ProcessingInstruction {

    public JSProcessingInstruction(JSObject processingInstruction) {
        super(processingInstruction);
    }

    @Override
    public String getTarget() {
        return (String) getJSObject().getMember("target");
    }

    @Override
    public String getData() {
        return (String) getJSObject().getMember("data");
    }

    @Override
    public void setData(String data) throws DOMException {
        getJSObject().setMember("data", data);
    }
}
