module wpsr.parsers.rdf.query {
  export interface IRDFNode {
    extractRawObject(): Object;
    transform(obj: Object) : IRDFNode;
    canTransform(obj: Object): bool;
    isAnon(): bool;
    isLiteral(): bool;
    isResource(): bool;
    isURIResource(): bool;
  }
}
