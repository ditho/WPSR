///<reference path="./../parsers/rdf/predicates/Predicate.ts" />

module wpsr.factory {
  export class PredicateFactory {
    public Predicate create(nameOfClass: String): Predicate {
      return new "wpsr.resources.predicates." + nameOfClass();
    }
  }
}
