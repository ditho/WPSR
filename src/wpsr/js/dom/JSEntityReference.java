package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.EntityReference;

/**
 *
 * @author tb006
 */
public class JSEntityReference extends JSNode implements EntityReference {

    public JSEntityReference(JSObject entityReference) {
        super(entityReference);
    }
}