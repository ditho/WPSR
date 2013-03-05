///<reference path="./../../factory/PredicateFactory.ts" />
///<reference path="./../rdf/predicates/Predicate.ts" />
///<reference path="./../rdf/query/IRDFNode.d.ts" />
///<reference path="./../rdf/query/IRDFStatement.d.ts" />

module wpsr.parsers.dom {
 export class DOMCandidateManager {

    private predicateFactory: PredicateFactory = new PredicateFactory();
    private processor: DOMProcessor;
    // get assoziativ array
    private scoreboard: HashMap<String, IDOMCandidate[]>;
    private exactMatch: HashMap<String, IDOMCandidate[]>;

    constructor(processor: DOMProcessor) {
      exactMatch = new HashMap<String, IDOMCandidate[]>();
      scoreboard = new HashMap<String, IDOMCandidate[]>();
      this.processor = processor;
    }

    public exactMatch(uri: String, candidate: IDOMCandidate): void {
      if (candidate == null) {
        return;
      }
      if (exactMatch.get(uri) == null) {
        cContainer: IDOMCandidate[];
        cContainer.add(candidate);
        exactMatch.put(uri, cContainer);
      } else {
        cContainer: IDOMCandidate[] = exactMatch.get(uri);
        if (cContainer != null) {
          cContainer.add(candidate);
          exactMatch.put(uri, cContainer);
        }
      }
    }

    public remove(uri: String, candidate: IDOMCandidate): void {
      if (candidate == null) {
        return;
      }
      if (scoreboard.get(uri) == null) {
        return;
      } else {
        scoreboard.get(uri).remove(candidate);
      }
    }

    public add(uri: String, candidate: IDOMCandidate): void {
      if (candidate == null) {
        return;
      }
      if (scoreboard.get(uri) == null) {
        cContainer: IDOMCandidate[];
        cContainer.add(candidate);
        scoreboard.put(uri, cContainer);
      } else {
        cContainer: IDOMCandidate[] = scoreboard.get(uri);
        if (cContainer != null) {
          cContainer.add(candidate);
          scoreboard.put(uri, cContainer);
        }
      }
    }

    public add(uri: String, list: IDOMCandidate[]): void {
      if (scoreboard.get(uri) == null) {
        cContainer: IDOMCandidate[];
        cContainer.addAll(list);
        scoreboard.put(uri, cContainer);
      } else {
        cContainer: IDOMCandidate[] = scoreboard.get(uri);
        if (cContainer != null) {
          cContainer.addAll(list);
          scoreboard.put(uri, cContainer);
        }
      }
    }

    public add(subject: IRDFNode, element: IDOMElement): void {
      if (scoreboard.get(subject.toString()) == null) {
        cContainer: IDOMCandidate[];
        cContainer.add(new DOMCandidate(element, subject));
        scoreboard.put(subject.toString(), cContainer);
      } else {
        cContainer: IDOMCandidate[] = scoreboard.get(subject.toString());
        if (cContainer != null) {
          cContainer.add(new DOMCandidate(element, subject));
          scoreboard.put(subject.toString(), cContainer);
        }
      }
    }

    public add(subject: IRDFNode, list: IDOMElement[]): void {
      if (scoreboard.get(subject.toString()) == null) {
        cContainer IDOMCandidate[];
        for (element: IDOMElement in list) {
          cContainer.add(new DOMCandidate(element, subject));
        }
        scoreboard.put(subject.toString(), cContainer);
      } else {
        cContainer: IDOMCandidate[] = scoreboard.get(subject.toString());
        for (element: IDOMElement in list) {
          cContainer.add(new DOMCandidate(element, subject));
        }
        scoreboard.put(subject.toString(), cContainer);
      }
    }

    public get(uri: String): IDOMCandidate[] {
      return scoreboard.get(uri);
    }

    public put(uri: String, list: IDOMCandidate[]): void {
      scoreboard.put(uri, list);
    }

    public process(statements: IRDFStatement[]): void {
      for (stmt: IRDFStatement in statements) {
        predicate: Predicate = predicateFactory.create(stmt.getPredicate());
        predicate.analyze(stmt, this);
      }
    }

    /**
     * Determine the list of candidates that are scored by the classification
     * algorithm. So each candidate will can be evaluated by their score.
     *
     * @return a list of scored candidates
     */
    public getBest(): IDOMCandidate[] {
      scoredList: IDOMCandidate[];
      for (subject: String in scoreboard.keySet()) {
        for (candidate: IDOMCandidate in scoreboard.get(subject)) {
          if (candidate.hasMatchedFeatures()) {
            scoredList.add(candidate);
          }
        }
      }
      for (subject: String in exactMatch.keySet()) {
        for (IDOMCandidate candidate : exactMatch.get(subject)) {
          if (candidate.hasMatchedFeatures()) {
            scoredList.add(candidate);
          }
        }
      }
      return scoredList;
    }
  }
}
