package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Comment;

/**
 *
 * @author tb006
 */
public class JSComment extends JSCharacterData implements Comment {

    public JSComment(JSObject comment) {
        super(comment);
    }
}