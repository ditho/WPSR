package wpsr.js.dom;

import netscape.javascript.JSObject;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 *
 * @author tb006
 */
public class JSDocument extends JSNode implements Document {

    public JSDocument(JSObject doc) {
        super(doc);
    }

    @Override
    public DocumentType getDoctype() {
        return new JSDocumentType((JSObject) getJSObject().getMember("doctype"));
    }

    @Override
    public DOMImplementation getImplementation() {
        return new JSDOMImplementation((JSObject) getJSObject().getMember("implementation"));
    }

    @Override
    public Element getDocumentElement() {
        return new JSElement((JSObject) getJSObject().getMember("documentElement"));
    }

    @Override
    public Element createElement(String tagName) throws DOMException {
        return new JSElement((JSObject) getJSObject().call("createElement", new Object[]{tagName}));
    }

    @Override
    public DocumentFragment createDocumentFragment() {
        return new JSDocumentFragment((JSObject) getJSObject().call("createDocumentFragment", null));
    }

    @Override
    public Text createTextNode(String data) {
        return new JSText((JSObject) getJSObject().call("createTextNode", new Object[]{data}));
    }

    @Override
    public Comment createComment(String data) {
        return new JSComment((JSObject) getJSObject().call("createComment", new Object[]{data}));
    }

    @Override
    public CDATASection createCDATASection(String data) throws DOMException {
        return new JSCDATASection((JSObject) getJSObject().call("createCDATASection", new Object[]{data}));
    }

    @Override
    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
        return new JSProcessingInstruction((JSObject) getJSObject().call("createProcessingInstruction", new Object[]{target, data}));
    }

    @Override
    public Attr createAttribute(String name) throws DOMException {
        return new JSAttr((JSObject) getJSObject().call("createAttribute", new Object[]{name}));
    }

    @Override
    public EntityReference createEntityReference(String name) throws DOMException {
        return new JSEntityReference((JSObject) getJSObject().call("createEntityReference", new Object[]{name}));
    }

    @Override
    public NodeList getElementsByTagName(String tagname) {
        return new JSNodeList((JSObject) getJSObject().call("getElementsByTagName", new Object[]{tagname}));
    }

    @Override
    public Node importNode(Node importedNode, boolean deep) throws DOMException {
        JSObject importedNodeJS = ((JSNode) importedNode).getJSObject();
        return new JSNode((JSObject) getJSObject().call("importNode", new Object[]{importedNodeJS, deep}));
    }

    @Override
    public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
        return new JSElement((JSObject) getJSObject().call("createElementNS", new Object[]{namespaceURI, qualifiedName}));
    }

    @Override
    public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
        return new JSAttr((JSObject) getJSObject().call("createAttributeNS", new Object[]{namespaceURI, qualifiedName}));
    }

    @Override
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        return new JSNodeList((JSObject) getJSObject().call("getElementsByTagNameNS", new Object[]{namespaceURI, localName}));
    }

    @Override
    public Element getElementById(String elementId) {
        return new JSElement((JSObject) getJSObject().call("getElementById", new Object[]{elementId}));
    }

    @Override
    public String getInputEncoding() {
        return (String) getJSObject().getMember("inputEncoding");
    }

    @Override
    public String getXmlEncoding() {
        return (String) getJSObject().getMember("xmlEncoding");
    }

    @Override
    public boolean getXmlStandalone() {
        return (Boolean) getJSObject().getMember("xmlStandalone");
    }

    @Override
    public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
        getJSObject().setMember("xmlStandalone", xmlStandalone);
    }

    @Override
    public String getXmlVersion() {
        return (String) getJSObject().getMember("xmlVersion");
    }

    @Override
    public void setXmlVersion(String xmlVersion) throws DOMException {
        getJSObject().setMember("xmlVersion", xmlVersion);
    }

    @Override
    public boolean getStrictErrorChecking() {
        return (Boolean) getJSObject().getMember("strictErrorChecking");
    }

    @Override
    public void setStrictErrorChecking(boolean strictErrorChecking) {
        getJSObject().setMember("strictErrorChecking", strictErrorChecking);
    }

    @Override
    public String getDocumentURI() {
        return (String) getJSObject().getMember("documentURI");
    }

    @Override
    public void setDocumentURI(String documentURI) {
        getJSObject().setMember("documentURI", documentURI);
    }

    @Override
    public Node adoptNode(Node source) throws DOMException {
        JSObject sourceJS = ((JSNode) source).getJSObject();
        return new JSNode((JSObject) getJSObject().call("adoptNode", new Object[]{sourceJS}));
    }

    @Override
    public DOMConfiguration getDomConfig() {
        return new JSDOMConfiguration((JSObject) getJSObject().getMember("domConfig"));
    }

    @Override
    public void normalizeDocument() {
        getJSObject().call("normalizeDocument", null);
    }

    @Override
    public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException {
        JSObject nJS = ((JSNode) n).getJSObject();
        return new JSNode((JSObject) getJSObject().call("renameNode", new Object[]{nJS, namespaceURI, qualifiedName}));
    }
}