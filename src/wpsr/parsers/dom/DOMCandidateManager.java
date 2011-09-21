package wpsr.parsers.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import wpsr.factory.PredicateFactory;
import wpsr.parsers.rdf.predicates.Predicate;
import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFStatement;

public class DOMCandidateManager {

    static Logger logger = Logger.getLogger(DOMCandidateManager.class);
    private PredicateFactory predicateFactory = new PredicateFactory();
    private DOMProcessor processor;
    private HashMap<String, ArrayList<IDOMCandidate>> scoreboard;
    private HashMap<String, ArrayList<IDOMCandidate>> exactMatch;

    public DOMCandidateManager(DOMProcessor processor) {
        exactMatch = new HashMap<String, ArrayList<IDOMCandidate>>();
        scoreboard = new HashMap<String, ArrayList<IDOMCandidate>>();
        this.processor = processor;
    }

    public void exactMatch(String uri, IDOMCandidate candidate) {
        if (candidate == null) {
            return;
        }
        if (exactMatch.get(uri) == null) {
            final ArrayList<IDOMCandidate> cContainer = new ArrayList<IDOMCandidate>();
            cContainer.add(candidate);
            exactMatch.put(uri, cContainer);
        } else {
            final ArrayList<IDOMCandidate> cContainer = exactMatch.get(uri);
            if (cContainer != null) {
                cContainer.add(candidate);
                exactMatch.put(uri, cContainer);
            }
        }
    }

    public void remove(String uri, IDOMCandidate candidate) {
        if (candidate == null) {
            return;
        }
        if (scoreboard.get(uri) == null) {
            return;
        } else {
            scoreboard.get(uri).remove(candidate);
        }
    }

    public void add(String uri, IDOMCandidate candidate) {
        if (candidate == null) {
            return;
        }
        if (scoreboard.get(uri) == null) {
            final ArrayList<IDOMCandidate> cContainer = new ArrayList<IDOMCandidate>();
            cContainer.add(candidate);
            scoreboard.put(uri, cContainer);
        } else {
            final ArrayList<IDOMCandidate> cContainer = scoreboard.get(uri);
            if (cContainer != null) {
                cContainer.add(candidate);
                scoreboard.put(uri, cContainer);
            }
        }
    }

    public void add(String uri, List<IDOMCandidate> list) {
        if (scoreboard.get(uri) == null) {
            final ArrayList<IDOMCandidate> cContainer = new ArrayList<IDOMCandidate>();
            cContainer.addAll(list);
            scoreboard.put(uri, cContainer);
        } else {
            final ArrayList<IDOMCandidate> cContainer = scoreboard.get(uri);
            if (cContainer != null) {
                cContainer.addAll(list);
                scoreboard.put(uri, cContainer);
            }
        }
    }

    public void add(IRDFNode subject, IDOMElement element) {
        if (scoreboard.get(subject.toString()) == null) {
            final ArrayList<IDOMCandidate> cContainer = new ArrayList<IDOMCandidate>();
            cContainer.add(new DOMCandidate(element, subject));
            scoreboard.put(subject.toString(), cContainer);
        } else {
            ArrayList<IDOMCandidate> cContainer = scoreboard.get(subject.toString());
            if (cContainer != null) {
                cContainer.add(new DOMCandidate(element, subject));
                scoreboard.put(subject.toString(), cContainer);
            }
        }
    }

    public void add(IRDFNode subject, ArrayList<IDOMElement> list) {
        if (scoreboard.get(subject.toString()) == null) {
            final ArrayList<IDOMCandidate> cContainer = new ArrayList<IDOMCandidate>();
            for (IDOMElement element : list) {
                cContainer.add(new DOMCandidate(element, subject));
            }
            scoreboard.put(subject.toString(), cContainer);
        } else {
            ArrayList<IDOMCandidate> cContainer = scoreboard.get(subject.toString());
            for (IDOMElement element : list) {
                cContainer.add(new DOMCandidate(element, subject));
            }
            scoreboard.put(subject.toString(), cContainer);
        }
    }

    public ArrayList<IDOMCandidate> get(String uri) {
        return scoreboard.get(uri);
    }

    public void put(String uri, ArrayList<IDOMCandidate> list) {
        scoreboard.put(uri, list);
    }

    public void process(ArrayList<IRDFStatement> statements) {
        if (statements == null || statements.isEmpty()) {
            return;
        }
        for (final IRDFStatement stmt : statements) {
            final Predicate predicate = predicateFactory.create(stmt.getPredicate(), processor);
            if (predicate != null) {
                predicate.analyze(stmt, this);
            }
        }
        // TODO: analyze this staff to get a better interface between the predicates and the candidateManager
        //final IRDFResource subject = stmt.getSubject();
        //final Predicate predicateHandler = predicateFactory.create(stmt.getPredicate(), dom);
        //if (scoreboard.get(subject.toString()) != null) {
        //final ArrayList<IDOMCandidate> cContainer = scoreboard.get(subject.toString());
        //final Iterator<IDOMCandidate> iterator = cContainer.iterator();
        //while (iterator.hasNext()) {
        //final IDOMCandidate candidate = iterator.next();
        //predicate.analyze(stmt, candidate);
        //}
        //} else {
        //predicate.analyze(stmt);
        //}
    }

    /**
     * Determine the list of candidates that are scored by the classification
     * algorithm. So each candidate will can be evaluated by their score.
     *
     * @return a list of scored candidates
     */
    public ArrayList<IDOMCandidate> getBest() {
        final ArrayList<IDOMCandidate> scoredList = new ArrayList<IDOMCandidate>();
        for (String subject : scoreboard.keySet()) {
            //logger.debug("subject=" + subject + ",size=" + scoreboard.get(subject).size());
            for (IDOMCandidate candidate : scoreboard.get(subject)) {
                //logger.debug(candidate);
                if (candidate.hasMatchedFeatures()) {
                    logger.debug(candidate);
                    scoredList.add(candidate);
                }
            }
        }
        for (String subject : exactMatch.keySet()) {
            //logger.debug("subject=" + subject + ",size=" + scoreboard.get(subject).size());
            for (IDOMCandidate candidate : exactMatch.get(subject)) {
                //logger.debug(candidate);
                if (candidate.hasMatchedFeatures()) {
                    logger.debug(candidate);
                    scoredList.add(candidate);
                }
            }
        }
        return scoredList;
    }
}
