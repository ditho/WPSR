// TODO: import java.util.Iterator;

///<reference path="./../../../engines/annotators/WPSRInformationCollector.ts" />
///<reference path="./../../dom/DOMCandidateManager.ts" />
///<reference path="./../../dom/DOMProcessor.ts" />
///<reference path="./../../dom/IDOMCandidate.d.ts" />
///<reference path="./../../dom/IDOMElement.d.ts" />
///<reference path="./../RDFCanstants.ts" />
///<reference path="./../query/IRDFNode.d.ts" />
///<reference path="./../query/IRDFResource.d.ts" />
///<reference path="./../query/IRDFStatement.d.ts" />

module wpsr.parsers.rdf.predicates {
  export class ChildrenPredicate extends Predicate {

    private tmpintern: IDOMCandidate[];
    private static intern: IDOMCandidate[];
    //public static ArrayList<ArrayList<IDOMCandidate>> heap = new ArrayList(new ArrayList());
    public static heap: any[IDOMCandidate[]];

    // TODO: processor will not be needed anymore.
    public constructor(processor: DOMProcessor) {
      super(processor);
    }

    public analyse(stmt: IRDFStatement, candidate: IDOMCandidate, root: IRDFResource, manager: DOMCandidateManager): string {
      results: string[];
      object: IRDFNode = stmt.getObject();
      subject: IRDFResource = stmt.getSubject();
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
          // TODO: getStatements should not return an iterator
          statements: IRDFStatement[] = WPSRInformationCollector.getRDFParser().getStatements(object, null, null);
          for (statement in statements) {
            results.add(analyse(statement, candidate, root, manager));
          }
          // ALTERNATIVE
          if (results.contains("-2")) {
            if (results.contains("1")) {
	      // TODO: method add not available for javascript arrays
	      intern.add(selectCandidates());
              return "1";
            } else {
              return "0";
            }
          // BAG
          } else if (results.contains("-3")) {
            tmpcounter: number = 0;
            for (var i = 0; i <= results.size() - 1; i++) {
              if (Integer.parseInt(results.get(i)) > -1) {
                tmpcounter = tmpcounter + Integer.parseInt(results.get(i));
              }
            }
            if (tmpcounter == results.size() - 1) {
              intern.add(selectCandidates());
              return "1";
            } else {
              return "0";
            }
          }
        } catch (exception) {
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

    /**
     * Select all candidates
     */
    private selectCandidates(): IDOMCandidate {
      if (tmpintern.isEmpty()) {
        return null;
      }
      selectedCandidate: IDOMCandidate = tmpintern.get(0);
      for (var i = 0; i <= tmpintern.size() - 1; i++) {
        current: IDOMCandidate = tmpintern.get(i);
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

    public execute(stmt: IRDFStatement, candidate: IDOMCandidate, manager: DOMCandidateManager): string {
      candidateList: IDOMCandidate[] = manager.get(stmt.getObject().toString());
      if (candidateList == null || candidateList.isEmpty()) {
        return "0";
      }
      isAncestor: string = "0";
      for (c in candidateList) {
        // TODO: Do we need AncestorList?
        ancestorList: IDOMElement[] = c.getAncestorList();
        for (parent in ancestorList) {
          if (parent.getBegin() == candidate.getBegin()) {
            isAncestor = "1";
            tmpintern.add(c);
          }
        }
      }
      return isAncestor;
    }

    public analyze(stmt: IRDFStatement, root: IRDFResource, manager: DOMCandidateManager): void {
    }
  }
}
