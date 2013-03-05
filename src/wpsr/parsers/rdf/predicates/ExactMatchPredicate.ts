///<reference path="./../../dom/DOMCandidateManager.ts" />
///<reference path="./../../dom/DOMProcessor.ts" />
///<reference path="./../../dom/IDOMCandidate.d.ts" />
///<reference path="./../query/IRDFNode.d.ts" />
///<reference path="./../query/IRDFProperty.d.ts" />
///<reference path="./../query/IRDFResource.d.ts" />
///<reference path="./../query/IRDFStatement.d.ts" />

module wpsr.parsers.rdf.predicates {
  export class ExactMatchPredicate extends Predicate {
    constructor(processor: DOMProcessor) {
      super(processor);
    }
    public analyze(stmt: IRDFStatement, root: IRDFResource, manager: DOMCandidateManager): void {
      predicate: IRDFProperty = stmt.getPredicate();
      index: number = predicate.toString().indexOf('#');
      attr: string = predicate.toString().substring(index + 1);
      object: IRDFNode = stmt.getObject();
      index = object.toString().indexOf('#');
      value: string = object.toString().substring(index + 1);
      candidateList: IDOMCandidate[] = manager.get(stmt.getSubject().toString());
      if (candidateList == null || candidateList.isEmpty()) {
        return;
      }
      exactMatchList: IDOMCandidate[];
      for (candidate in candidateList) {
        if (candidate == null) {
          continue;
        }
        if(candidate.hasAttribute(attr) && candidate.getAttribute(attr).contentEquals(value)) {
          candidate.matchedPosFeature();
          candidate.featureSearched();
          exactMatchList.add(candidate);
        }
      }
      for (candidate in exactMatchList) {
        manager.exactMatch(stmt.getSubject().getURI(), candidate);
        manager.remove(stmt.getSubject().getURI(), candidate);
      }
    }
  }
}
