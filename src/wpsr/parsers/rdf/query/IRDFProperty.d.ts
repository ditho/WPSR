///<reference path="./IRDFResource.d.ts" />

module wpsr.parsers.rdf.query {
  export interface IRDFProperty extends wpsr.parsers.rdf.query.IRDFResource {
    getOrdinal(): number;
    isProperty(): bool;
  }
}
