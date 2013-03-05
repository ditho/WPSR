///<reference path="./IRDFLiteral.d.ts" />
///<reference path="./IRDFNode.d.ts" />
///<reference path="./IRDFProperty.d.ts" />
///<reference path="./IRDFResource.d.ts" />

module wpsr.parsers.rdf.query {
 export interface IRDFStatement {
    extractRawObject(): Object;
    changeObject(arg0: bool): IRDFStatement;
    changeObject(arg0: string): IRDFStatement;
    changeObject(arg0: number): IRDFStatement;
    changeObject(arg0: Object): IRDFStatement;
    changeObject(arg0: IRDFNode): IRDFStatement;
    changeObject(arg0: string, arg1: bool): IRDFStatement;
    changeObject(arg0: string, arg1: string): IRDFStatement;
    changeObject(arg0: string, arg1: string, arg2: bool): IRDFStatement;
    getBoolean(): bool;
    getString(): string;
    getNumber(): number;
    getObject(): IRDFNode;
    getLanguage(): string;
    getLiteral(): IRDFLiteral;
    getPredicate(): IRDFProperty;
    getResource(): IRDFResource;
    getSubject(): IRDFResource;
    getWellFormed(): bool;
    hasWellFormedXML(): bool;
    isReified(): bool;
    getProperty(arg0: IRDFProperty): IRDFStatement;
    getStatementProperty(arg0: IRDFProperty): IRDFStatement;
    remove(): IRDFStatement;
  }
}    
