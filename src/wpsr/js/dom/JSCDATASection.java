package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.CDATASection;

/**
 *
 * @author tb006
 */
public class JSCDATASection extends JSText implements CDATASection {

    public JSCDATASection(JSObject section) {
        super(section);
    }
}