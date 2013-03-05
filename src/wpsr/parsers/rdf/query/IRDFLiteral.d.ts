///<reference path="./IRDFNode.d.ts" />

module wpsr.parsers.rdf.query {
  export interface IRDFLiteral extends IRDFNode {
    getBoolean(): bool;
    getNumber(): number;
    getString(): string;
    getValue(): Object;
    getDatatypeURI(): string;
    getLanguage(): string;
    getLexicalForm(): string;
    getWellFormed(): bool;
    isWellFormedXML(): bool;
    sameValueAs(arg0: IRDFLiteral): bool;
  }
}    
