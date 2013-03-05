///<reference path="./query/IRDFNode.d.ts" />
///<reference path="./query/IRDFProperty.d.ts" />
///<reference path="./query/IRDFResource.d.ts" />
///<reference path="./query/IRDFStatement.d.ts" />

module wpsr.parsers.rdf {
  export interface IRDFFileParser {
    // TODO: iterator<IRDFStatement>
    getAllStatements(): any;
    // TODO: Iterator<IRDFStatement>
    getSpecificStatements(
		    subject: wpsr.parsers.rdf.query.IRDFResource, 
		    predicate: wpsr.parsers.rdf.query.IRDFProperty, 
		    object: wpsr.parsers.rdf.query.IRDFNode): any;
    // TODO: Iterator<IRDFStatement>
    getStatements(
		    subject: wpsr.parsers.rdf.query.IRDFNode, 
		    predicate: wpsr.parsers.rdf.query.IRDFProperty, 
		    object: wpsr.parsers.rdf.query.IRDFNode): any;
  }
}
