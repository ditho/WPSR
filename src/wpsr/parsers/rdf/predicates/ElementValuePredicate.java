package wpsr.parsers.rdf.predicates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import wpsr.engines.annotators.WPSRInformationCollector;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMCandidate;
import wpsr.parsers.rdf.RDFConstants;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

/**
 *
 * @author tb006
 */
public class ElementValuePredicate extends Predicate {

    static Logger logger = Logger.getLogger(ElementValuePredicate.class.getName());

    public ElementValuePredicate(DOMProcessor proc) {
        super(proc);
    }

    @Override
    public void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager) {
        logger.debug("[subject=" + stmt.getSubject()
                + ",predicate=" + stmt.getPredicate()
                + ",object=" + stmt.getObject() + "]");
        if (!stmt.getObject().isResource()) {
            return;
        }
        final ArrayList<IDOMCandidate> candidateList = manager.get(stmt.getSubject().toString());
        if (candidateList == null) {
            return;
        }
        for (IDOMCandidate candidate : candidateList) {
            if (candidate == null) {
                continue;
            }
            Iterator<IRDFStatement> objectStmtIter = WPSRInformationCollector.getRDFParser().getStatements(stmt.getObject(), null, null);
            while (objectStmtIter.hasNext()) {
                IRDFStatement objStmt = objectStmtIter.next();
                // count score for catchwords
                String value = candidate.getElementValue();
                if (value != null) {
                    Pattern p = Pattern.compile(objStmt.getObject().toString());
                    Matcher m = p.matcher(value.toLowerCase());
                    Boolean find = m.find();
                    if (find && RDFConstants.POSITIVE.contentEquals(objStmt.getPredicate().toString())) {
                        candidate.matchedPosFeature();
                    } else if (find && RDFConstants.NEGATIVE.contentEquals(objStmt.getPredicate().toString())) {
                        candidate.matchedNegFeature();
                    }
                }
            }
            candidate.featureSearched();
        }
    }
}
