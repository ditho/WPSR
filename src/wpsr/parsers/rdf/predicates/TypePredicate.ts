package wpsr.parsers.rdf.predicates;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMElement;
import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

public class TypePredicate extends Predicate {

    static Logger logger = Logger.getLogger(TypePredicate.class.getName());

    public TypePredicate(DOMProcessor proc) {
        super(proc);
    }
  
    @Override
    public void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager) {
        logger.debug("[subject=" + stmt.getSubject()
                + ",predicate=" + stmt.getPredicate()
                + ",object=" + stmt.getObject() + "]");

        final IRDFNode object = stmt.getObject();
        if (object == null) {
            return;
        }
        // Get the HTML-Tag of the RDF/XML-Statement
        final int begin = object.toString().indexOf('#');
        String xpathText = object.toString().substring(begin + 1);
      
        // simulate simple XPath: thats an workaround
        int end = xpathText.indexOf('[');
        int or = xpathText.indexOf('|');
        if (end != -1) {
            final int endAttr = xpathText.indexOf('=');
            final int endValue = xpathText.indexOf("'", endAttr + 2);
            final String tagName = xpathText.substring(2, end);
            final String attr = xpathText.substring(end + 2, endAttr);
            final String value = xpathText.substring(endAttr + 2, endValue);
            final ArrayList<IDOMElement> elementList = getDOMProcessor().getAllElements(tagName);
            final ArrayList<IDOMElement> analyzedList = new ArrayList<IDOMElement>();
            for (IDOMElement e : elementList) {
                if (e.hasAttribute(attr) && value.contentEquals(e.getAttribute(attr))) {
                    analyzedList.add(e);
                    continue;
                }
                final int hasAttrBegin = xpathText.indexOf("not");
                if (hasAttrBegin != -1) {
                    final int hasAttrEnd = xpathText.indexOf(")");
                    String hasAttr = xpathText.substring(hasAttrBegin + 5, hasAttrEnd);
                    if(!e.hasAttribute(hasAttr))
                        analyzedList.add(e);
                }
            }
            manager.add(stmt.getSubject(), analyzedList);
        } else if (or != -1) {
            xpathText = xpathText.replaceAll("//", "");
            String[] split = xpathText.split("\\|");
            for(int i = 0; i < split.length; i++){
                manager.add(stmt.getSubject(), getDOMProcessor().getAllElements(split[i].trim()));
            }
        } else {
            final String tagName = xpathText.substring(2);
            final ArrayList<IDOMElement> elementList = getDOMProcessor().getAllElements(tagName);
            manager.add(stmt.getSubject(), elementList);
        }
    }
}