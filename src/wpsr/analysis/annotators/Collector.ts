///<reference path="./../../parsers/dom/DOMCandidateManager.ts" />
///<reference path="./../../parsers/dom/IDOMCandidate.d.ts" />
// TODO: last step
///<reference path="./../../parsers/rdf/RDFDOMFactory.ts" />
///<reference path="./../../parsers/rdf/RDFParserMap.ts" />
///<reference path="./../../parsers/rdf/RDFProcessor.ts" />

///<reference path="./../cas/ICommonAnalysisStructure.d.ts" />
///<reference path="./IAnnotator.d.ts" />

module wpsr.analysis.annotators {
  export public class Collector implements IAnnotator {
    public process(cas) {
      // TODO: build a new Factory for creating a Parser
      // TODO: RDFFileParser.create(cas.getAnnoType());
      rdfFileParser = RDFFileParser.create(cas);
      rdfProcessor: RDFProcessor = new RDFProcessor();
      // TODO: Get a generic manager object, create an interface
      candidateManager: DOMCandidateManager = new DOMCandidateManager();
      candidateManager.process(rdfProcessor.sortStatements(rdfFileParser));
      for (candidate in candidateManager.getBest()) {
        AnnotationFactory.create(cas, candidate);
      }
    }
    public static getRDFParser(): wpsr.parsers.rdf.IRDFFileParser {
      return Collector.rdfFileParser;
    }
  }
}
