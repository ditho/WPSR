package wpsr.parsers.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.htmlparser.jericho.Attribute;
import net.htmlparser.jericho.Element;

public class DOMElement implements IDOMElement {

    private Element element = null;

    public DOMElement(Element element) {
        this.element = element;
    }

    public DOMElement(IDOMElement element) {
        this.element = (Element) element.extractRawElement();
    }

    @Override
    public Element extractRawElement() {
        return element;
    }

    @Override
    public IDOMElement getParent() {
        final Element parent = element.getParentElement();
        if(parent == null)
            return null;
        return new DOMElement(parent);
    }

    @Override
    public ArrayList<IDOMElement> getChildren() {
        final ArrayList<IDOMElement> childList = new ArrayList<IDOMElement>();
        final List<Element> children = element.getChildElements();
        if(children == null)
            return null;
        for (final Element child : children) {
            childList.add(new DOMElement(child));
        }
        return childList;
    }

    @Override
    public HashMap<String, String> getAttributes() {
        final HashMap<String, String> attributeList = new HashMap<String, String>();
        final List<Attribute> attributes = element.getAttributes();
        if(attributes == null)
            return null;
        for (final Attribute a : attributes) {
            attributeList.put(a.getKey(), a.getValue());
        }
        return attributeList;
    }

    @Override
    public IDOMElement getFirstChild() {
        if (hasChildren()) {
            return getChildren().get(0);
        } else {
            return null;
        }
    }

    @Override
    public IDOMElement getLastChild() {
        if (hasChildren()) {
            final ArrayList<IDOMElement> children = getChildren();
            return children.get(children.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public String getLocalName() {
        return element.getName();
    }

    @Override
    public IDOMElement getNextSibling() {
        final ArrayList<IDOMElement> siblings = getParent().getChildren();
        try {
            return siblings.get(siblings.indexOf(this) + 1);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public IDOMElement getPreviousSibling() {
        final ArrayList<IDOMElement> siblings = getParent().getChildren();
        try {
            return siblings.get(siblings.indexOf(this) - 1);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public String getElementName() {
        return element.getName();
    }

    @Override
    public String getElementType() {
        return element.getName();
    }

    @Override
    public String getElementValue() {
        return element.getContent().toString();
    }

    @Override
    public boolean hasAttributes() {
        if (getAttributes().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean hasChildren() {
        if (getChildren().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isEqualElement(IDOMElement e) {
        if (equals(e)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isSameElement(IDOMElement e) {
        if (this == e) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttributeValue(name);
    }

    @Override
    public String getTagName() {
        return getElementType();
    }

    @Override
    public boolean hasAttribute(String name) {
        if (getAttribute(name) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getBegin() {
        return element.getBegin();
    }

    @Override
    public int getEnd() {
        return element.getEnd();
    }

    @Override
    public String toString() {
        return getClass().getName() + "[element=" + this.element + "]";
    }
}
