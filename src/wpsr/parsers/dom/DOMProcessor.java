package wpsr.parsers.dom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.Element;

public class DOMProcessor {
    static Logger logger = Logger.getLogger(DOMProcessor.class);
    private Source content = null;

    public DOMProcessor(Source content) {
	this.content = content;
    }

    public String getDocumentText() {
	return content.getSourceFormatter().setIndentString("  ")
		.setTidyTags(true).toString();
    }

    public Iterator<IDOMElement> getRootList() {
	final ArrayList<IDOMElement> rootList = new ArrayList<IDOMElement>();
	if (content != null) {
	    final List<Element> childlist = content.getChildElements();
	    for (Element child : childlist) {
		final IDOMElement e = new DOMElement(child);
		rootList.add(e);
	    }
	}
	return rootList.iterator();
    }

    public ArrayList<IDOMElement> getAllElements(String name) {
	final ArrayList<IDOMElement> elements = new ArrayList<IDOMElement>();
        final List<Element> elementList = content.getAllElements(name);
	for (Element rawElem : elementList) {
	    final IDOMElement element = new DOMElement(rawElem);
	    elements.add(element);
	}
	return elements;
    }

    public boolean isAncestor(IDOMElement element, IDOMElement possibleAncestor) {
	final IDOMElement parentNode = element.getParent();
	if (parentNode != null) {
	    if (parentNode == possibleAncestor) {
		return true;
	    } else {
		return isAncestor(parentNode, possibleAncestor);
	    }
	} else {
	    return false;
	}
    }

    @Override
    public String toString() {
	return getClass().getName() + "[content=" + this.content + "]";
    }
}