// TODO: impot java.util.Iterator;
// TODO: import com.hp.hpl.jena.datatypes.RDFDatatype;

///<reference path="./IRDFNode.d.ts" />
///<reference path="./IRDFResource.d.ts" />
///<reference path="./IRDFProperty.d.ts" />
///<reference path="./IRDFStatement.d.ts" />

module wpsr.parsers.rdf.query {
  export interface IRDFResource extends wpsr.parsers.rdf.query.IRDFNode {
    abort(): IRDFResource;
    begin(): IRDFResource;
    commit(): IRDFResource;
    getLocalName(): string;
    getNameSpace(): string;
    getURI(): string;
    hasURI(arg0: string): bool;
    getProperty(arg0: IRDFProperty): IRDFStatement;
    hasProperty(arg0: IRDFProperty): bool;
    addProperty(arg0: IRDFProperty, arg1: bool): IRDFResource;
    addProperty(arg0: IRDFProperty, arg1: string): IRDFResource;
    addProperty(arg0: IRDFProperty, arg1: number): IRDFResource;
    addProperty(arg0: IRDFProperty, arg1: Object): IRDFResource;
    addProperty(arg0: IRDFProperty, arg1: IRDFNode): IRDFResource;
    // TODO: arg2: RDFDatetype
    addProperty(arg0: IRDFProperty, arg1: string, arg2: any): IRDFResource;
    addProperty(arg0: IRDFProperty, arg1: string, arg2: string): IRDFResource;
    hasProperty(arg0: IRDFProperty, arg1: bool): bool;
    hasProperty(arg0: IRDFProperty, arg1: string): bool;
    hasProperty(arg0: IRDFProperty, arg1: number): bool;
    hasProperty(arg0: IRDFProperty, arg1: Object): bool;
    hasProperty(arg0: IRDFProperty, arg1: IRDFNode): bool;
    hasProperty(arg0: IRDFProperty, arg1: string, arg2: string): bool;
    // TODO: return Iterator<IRDFStatement>
    listProperties(): any;
    // TODO: return Iterator<IRDFStatement>
    listProperties(arg0: IRDFProperty): any;
    removeAll(arg0: IRDFProperty): IRDFResource;
    removeProperties(): IRDFResource;
  }
} 
