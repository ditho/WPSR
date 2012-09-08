package wpsr.parsers.rdf.predicates;

import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

public abstract class Predicate {

    private DOMProcessor proc;

    public Predicate(DOMProcessor proc) {
	this.proc = proc;
    }

    public DOMProcessor getDOMProcessor() {
	return proc;
    }

    public void analyze(IRDFStatement stmt, DOMCandidateManager manager) {
        analyze(stmt, null, manager);
    }

    public abstract void analyze(IRDFStatement stmt, IRDFResource root, DOMCandidateManager manager);
}