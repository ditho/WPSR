package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;

/**
 *
 * @author tb006
 */
public class JSCharacterData extends JSNode implements CharacterData {

    public JSCharacterData(JSObject characterData) {
        super(characterData);
    }

    @Override
    public String getData() throws DOMException {
        return (String) getJSObject().getMember("data");
    }

    @Override
    public void setData(String data) throws DOMException {
        getJSObject().setMember("data", data);
    }

    @Override
    public int getLength() {
        return (Integer) getJSObject().getMember("length");
    }

    @Override
    public String substringData(int offset, int count) throws DOMException {
        return (String) getJSObject().call("substringData", new Object[]{offset, count});
    }

    @Override
    public void appendData(String arg) throws DOMException {
        getJSObject().call("appendData", new Object[]{arg});
    }

    @Override
    public void insertData(int offset, String arg) throws DOMException {
        getJSObject().call("insertData", new Object[]{offset, arg});
    }

    @Override
    public void deleteData(int offset, int count) throws DOMException {
        getJSObject().call("deleteData", new Object[]{offset, count});
    }

    @Override
    public void replaceData(int offset, int count, String arg) throws DOMException {
        getJSObject().call("replaceData", new Object[]{offset, count, arg});
    }
}