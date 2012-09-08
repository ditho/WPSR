package wpsr.parsers.rdf.predicates;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;

import wpsr.engines.annotators.WPSRInformationCollector;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMCandidate;
import wpsr.parsers.dom.IDOMElement;
import wpsr.parsers.rdf.RDFConstants;
import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

/**
 * Component is deactivated -> TODO:search for a better solution
 * 
 * @author tb006
 */
public class ChildrenPredicate extends Predicate {

    static Logger logger = Logger.getLogger(ChildrenPredicate.class.getName());
    private final ArrayList<IDOMCandidate> tmpintern = new ArrayList<IDOMCandidate>();
    private static ArrayList<IDOMCandidate> intern = new ArrayList<IDOMCandidate>();
    public static ArrayList<ArrayList<IDOMCandidate>> heap = new ArrayList(new ArrayList());

    public ChildrenPredicate(DOMProcessor proc) {
        super(proc);
    }

    public String analyse(IRDFStatement stmt, IDOMCandidate candidate,
            IRDFResource root, DOMCandidateManager manager) {
        final ArrayList<String> results = new ArrayList<String>();
        final IRDFNode object = stmt.getObject();
        final IRDFResource subject = stmt.getSubject();

        if (root == null) {
            if (subject.isURIResource() && object.isResource()) {
                root = subject;
            } else {
                return "-1";
            }
        }

        // NESTED STATEMENT
        if (!object.isLiteral() && !object.isURIResource()) {
            try {
                final Iterator<IRDFStatement> statements = WPSRInformationCollector.getRDFParser().getStatements(object, null,
                        null);
                while (statements.hasNext()) {
                    results.add(analyse(statements.next(), candidate, root, manager));
                }

                // ALTERNATIVE
                if (results.contains("-2")) {
                    if (results.contains("1")) {
                        logger.debug("[alternative=\"1\"]");
                        intern.add(selectCandidates());
                        return "1";
                    } else {
                        logger.debug("[alternative=\"0\"]");
                        return "0";
                    }

                    // BAG
                } else if (results.contains("-3")) {
                    int tmpcounter = 0;
                    for (int i = 0; i <= results.size() - 1; i++) {
                        if (Integer.parseInt(results.get(i)) > -1) {
                            tmpcounter = tmpcounter
                                    + Integer.parseInt(results.get(i));
                        }
                    }
                    if (tmpcounter == results.size() - 1) {
                        logger.debug("[bag=\"1\"]");
                        intern.add(selectCandidates());
                        return "1";
                    } else {
                        logger.debug("[bag=\"0\",results=\"" + results + "\"]");
                        return "0";
                    }
                }
            } catch (final Exception e) {
                logger.error(e.getMessage());
                return "-1";
            }
            // LITERAL STATEMENT
        } else {
            if (object.toString().equals(RDFConstants.ALT_URI)) {
                return "-2";
            } else if (object.toString().equals(RDFConstants.BAG_URI)) {
                return "-3";
            } else {
                return execute(stmt, candidate, manager);
            }
        }
        return execute(stmt, candidate, manager);
    }

    private IDOMCandidate selectCandidates() {
        if (tmpintern.isEmpty()) {
            return null;
        }
        IDOMCandidate selectedCandidate = tmpintern.get(0);
        for (int i = 0; i <= tmpintern.size() - 1; i++) {
            final IDOMCandidate current = tmpintern.get(i);
            if (current.hasMatchedFeatures()) {
                if (selectedCandidate.hasMatchedFeatures()) {
                    if (current.getScore() > selectedCandidate.getScore()) {
                        selectedCandidate = current;
                    }
                } else {
                    selectedCandidate = current;
                }
            }
        }
        tmpintern.clear();
        return selectedCandidate;
    }

    public String execute(IRDFStatement stmt, IDOMCandidate candidate, DOMCandidateManager manager) {
        final ArrayList<IDOMCandidate> candidateList = manager.get(stmt.getObject().toString());
        if (candidateList == null || candidateList.isEmpty()) {
            return "0";
        }
        String isAncestor = "0";
        for (IDOMCandidate c : candidateList) {
            // TODO: Do we need AncestorList?
            final ArrayList<IDOMElement> ancestorList = c.getAncestorList();
            for (IDOMElement parent : ancestorList) {
                if (parent.getBegin() == candidate.getBegin()) {
                    isAncestor = "1";
                    tmpintern.add(c);
                }
            }
        }
        logger.debug("[isAncestor=" + isAncestor + ",object=" + stmt.getObject().toString() + "]");
        return isAncestor;
    }

    @Override
    public void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager) {
        logger.debug("[subject=" + stmt.getSubject()
                + ",predicate=" + stmt.getPredicate()
                + ",object=" + stmt.getObject() + "]");

        /*       
        final ArrayList<IDOMCandidate> candidateList = manager.get(stmt.getSubject().toString());
        IDOMCandidate topCandidate = null;
        if (candidateList == null || candidateList.isEmpty()) {
            return;
        }
        for (final IDOMCandidate candidate : candidateList) {
            // TODO: why is he doing this? temporally exclude <a> tags
            if (!candidate.getTagName().equals("a")) {
                intern = new ArrayList<IDOMCandidate>();
                final String result = analyse(stmt, candidate, null, manager);
                candidate.featureSearched();
                if (result.equals("1")) {
                    candidate.matchedPosFeature();
                    if (topCandidate != null) {
                        if (candidate.getScore() > topCandidate.getScore()) {
                            topCandidate = candidate;
                        }
                    } else {
                        topCandidate = candidate;
                    }
                }
                if (intern.size() > 0) {
                    if (topCandidate != null) {
                        intern.add(topCandidate);
                        heap.add(intern);
                    } else {
                        heap.add(intern);
                    }
                }
                candidateList.set(candidateList.indexOf(candidate), candidate);
            } // end if
        } // end for
        manager.put(stmt.getSubject().toString(), candidateList);*/
    }
}
