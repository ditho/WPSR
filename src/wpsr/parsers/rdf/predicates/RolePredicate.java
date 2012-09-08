package wpsr.parsers.rdf.predicates;

import java.util.ArrayList;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMCandidate;
import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

/**
 * 
 * @author tb006
 */
public class RolePredicate extends Predicate {

    public RolePredicate(DOMProcessor proc) {
        super(proc);
    }

    @Override
    public void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager) {
        IRDFNode obj = stmt.getObject();
        // get the WAI-ARIA-Role of the resource description
        int i = obj.toString().indexOf('#');
        String role = obj.toString().substring(i + 1);
        // annotate all candidates for a specific subject
        final ArrayList<IDOMCandidate> candidateList = manager.get(stmt.getSubject().toString());
        if (candidateList == null) {
            return;
        }
        for (IDOMCandidate candidate : candidateList) {
            if (candidate == null) {
                continue;
            }
            candidate.setRole(role);
        }
    }
}
