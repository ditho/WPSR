///<reference path="./../dom/DOMCandidateManager.ts" />
///<reference path="./../dom/DOMProcessor.ts" />
///<reference path="./../dom/IDOMCandidate.d.ts" />
///<reference path="./../query/IRDFResource.d.ts" />
///<reference path="./../query/IRDFStatement.d.ts" />
///<reference path="./Predicate.ts" />

module wpsr.parsers.rdf.predicates {
  export class CardinalityPredicate extends Predicate {
    /**
     * TODO: this class is not implemented right, because the presence of a
     * cardinality does not raise the score in a careless way. The statements
     * have to be processed if the right cardinality is given by the candidate
     * or not.
     *  
     * @param domProcessor
     */
    constructor(processor: DOMProcessor) {
      // TODO: precessor will not be needed anymore.
      super(processor);
    }

    public analyze(stmt: IRDFStatement, root: IRDFResource, manager: DOMCandidateManager): void {
      var candidateList: IDOMCandidate[] = manager.get(stmt.getSubject().toString());
      var analyzedList: IDOMCandidate[];
      if (candidateList === null) {
        return;
      }
      for (candidate in candidateList) {
        if (candidate == null) {
          continue;
        }
        candidate.featureSearched();
	candidate.matchedPosFeature();
	// TODO: get javascript array function like push??
        analyzedList.add(candidate);
      }
      manager.put(stmt.getSubject().toString(), analyzedList);
    }
  }
}
