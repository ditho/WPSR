/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wpsr.parsers.rdf.predicates;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMCandidate;
import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFProperty;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

/**
 *
 * @author tb006
 */
public class ExactMatchPredicate extends Predicate {

    static Logger logger = Logger.getLogger(ExactMatchPredicate.class.getName());

    public ExactMatchPredicate(DOMProcessor proc) {
        super(proc);
    }

    @Override
    public void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager) {
        logger.debug("[subject=" + stmt.getSubject()
                + ",predicate=" + stmt.getPredicate()
                + ",object=" + stmt.getObject() + "]");

        IRDFProperty predicate = stmt.getPredicate();
        int index = predicate.toString().indexOf('#');
        String attr = predicate.toString().substring(index + 1);

        IRDFNode object = stmt.getObject();
        index = object.toString().indexOf('#');
        String value = object.toString().substring(index + 1);
        final ArrayList<IDOMCandidate> candidateList = manager.get(stmt.getSubject().toString());
        if (candidateList == null || candidateList.isEmpty()) {
            return;
        }
        ArrayList<IDOMCandidate> exactMatchList = new ArrayList<IDOMCandidate>();
        for (IDOMCandidate candidate : candidateList) {
            if (candidate == null) {
                continue;
            }
            if(candidate.hasAttribute(attr) && candidate.getAttribute(attr).contentEquals(value)) {
                candidate.matchedPosFeature();
                candidate.featureSearched();
                exactMatchList.add(candidate);
            }
        }
        for (IDOMCandidate candidate : exactMatchList) {
            manager.exactMatch(stmt.getSubject().getURI(), candidate);
            manager.remove(stmt.getSubject().getURI(), candidate);
        }
    }
}
