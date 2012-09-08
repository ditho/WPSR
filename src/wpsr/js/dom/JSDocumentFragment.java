package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.DocumentFragment;

/**
 *
 * @author tb006
 */
public class JSDocumentFragment extends JSNode implements DocumentFragment {

    public JSDocumentFragment(JSObject fragment) {
        super(fragment);
    }
}