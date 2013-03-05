// TODO: import java.util.regex.Matcher;
// TODO: import java.util.regex.Pattern;
///<reference path="./../../../engines/annotators/WPSRInformationCollector.ts" />
///<reference path="./../../dom/DOMCandidateManager.ts" />
///<reference path="./../../dom/DOMProcessor.ts" />
///<reference path="./../../dom/IDOMCandidate.d.ts" />
///<reference path="./../RDFConstants.ts" />
///<reference path="./../query/IRDFResource.d.ts" />
///<reference path="./../query/IRDFStatement.d.ts" />

module wpsr.parsers.rdf.predicates {
  export class ElementValuePredicate extends Predicate {
    constructor(processor: DOMProcessor) {
        super(processor);
    }
    public analyze(stmt: IRDFStatement, root: IRDFStatement, manager: DOMCandidateManager): void {
      if (!stmt.getObject().isResource()) {
        return;
      }
      candidateList: IDOMCandidate[] = manager.get(stmt.getSubject().toString());
      if (candidateList == null) {
        return;
      }
      for (candidate in candidateList) {
        if (candidate == null) {
          continue;
	}
	// TODO: getStatement should not return an iterator
        objectStmtIter: IRDFStatement[] = WPSRInformationCollector.getRDFParser().getStatements(stmt.getObject(), null, null);
	for (objStmt in objectStmtIter) {
          // count score for catchwords
          value: string = candidate.getElementValue();
	  if (value != null) {
	    // TODO: get javascript regular expressions
            p: Pattern = Pattern.compile(objStmt.getObject().toString());
            m: Matcher = p.matcher(value.toLowerCase());
            find: bool = m.find();
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
}
