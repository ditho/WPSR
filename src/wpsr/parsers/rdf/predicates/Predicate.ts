///<reference path="./../../dom/DOMCandidateManager.ts" />
///<reference path="./../../dom/DOMProcessor.ts" />
///<reference path="./../query/IRDFResource.d.ts" />
///<reference path="./../query/IRDFStatement.d.ts" />

// TODO: Predicate should be an abstract class or an interface
module wpsr.parsers.rdf.predicates {
  abstract class Predicate {
    private processor: DOMProcessor;
    constructor(processor: DOMProcessor) {
      this.processor = processor;
    }
    public getDOMProcessor(): DOMProcessor {
      return this.processor;
    }
    public analyze(stmt: IRDFStatement, manager: DOMCandidateManager): void {
      analyze(stmt, null, manager);
    }
    public analyze(stmt: IRDFStatement, root: IRDFResource, manager: DOMCandidateManager): void {
    };
  }
}
