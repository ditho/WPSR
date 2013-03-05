///<reference path="./IRDFNode.d.ts" />

module wpsr.parsers.rdf.query {
  class RDFNode implements IRDFNode {

    // TODO: get RDFNode from jena
    private com.hp.hpl.jena.rdf.model.RDFNode node = null;

    constructor(com.hp.hpl.jena.rdf.model.RDFNode node) {
      this.node = node;
    }

    constructor(wpsr.parsers.rdf.query.IRDFNode node) {
      this.node = (com.hp.hpl.jena.rdf.model.RDFNode) node.extractRawObject();
    }

    public extractRawObject(): Object {
      return node;
    }

    public transform(obj: Object): IRDFNode {
      return new RDFNode(node.as(obj));
    }

    public canTransform(obj: Object): bool {
      return node.canAs(obj);
    }

    public isAnon(): bool {
      return node.isAnon();
    }

    public isLiteral(): bool {
      return node.isLiteral();
    }

    public isResource(): bool {
      return node.isResource();
    }

    public isURIResource(): bool {
      return node.isURIResource();
    }

    public toString(): String {
      return node.toString();
    }
  }
}
