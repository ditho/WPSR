package wpsr.parsers.dom;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDOMElement {

    public IDOMElement getParent();

    public ArrayList<IDOMElement> getChildren();

    public HashMap<String, String> getAttributes();

    public IDOMElement getFirstChild();

    public IDOMElement getLastChild();

    public String getLocalName();

    public IDOMElement getNextSibling();

    public String getElementName();

    public String getElementType();

    public String getElementValue();

    public IDOMElement getPreviousSibling();

    public boolean hasAttributes();

    public boolean hasChildren();

    public boolean isEqualElement(IDOMElement e);

    public boolean isSameElement(IDOMElement other);

    public String getAttribute(String name);

    public String getTagName();

    public boolean hasAttribute(String name);

    public int getBegin();

    public int getEnd();

    public Object extractRawElement();
}