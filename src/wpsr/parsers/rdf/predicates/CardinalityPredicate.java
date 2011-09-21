package wpsr.parsers.rdf.predicates;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMCandidate;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

public class CardinalityPredicate extends Predicate {

    static Logger logger = Logger.getLogger(CardinalityPredicate.class.getName());

    /**
     * TODO: this class is not implemented right, because the presence of a
     * cardinality does not raise the score in a careless way. The statements
     * have to be processed if the right cardinality is given by the candidate
     * or not.
     *
     * @param domProcessor
     */
    public CardinalityPredicate(DOMProcessor proc) {
        super(proc);
    }

    @Override
    public void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager) {
        logger.debug("[subject=" + stmt.getSubject()
                + ",predicate=" + stmt.getPredicate()
                + ",object=" + stmt.getObject() + "]");

        final ArrayList<IDOMCandidate> candidateList = manager.get(stmt.getSubject().toString());
        ArrayList<IDOMCandidate> analyzedList = new ArrayList<IDOMCandidate>();
        if (candidateList == null) {
            return;
        }
        for (IDOMCandidate candidate : candidateList) {
            if (candidate == null) {
                continue;
            }
            candidate.featureSearched();
            candidate.matchedPosFeature();
            analyzedList.add(candidate);
        }
        manager.put(stmt.getSubject().toString(), analyzedList);
    }
}
