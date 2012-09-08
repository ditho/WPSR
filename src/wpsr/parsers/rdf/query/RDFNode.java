package wpsr.parsers.rdf.query;

public class RDFNode implements IRDFNode {

    private com.hp.hpl.jena.rdf.model.RDFNode node = null;

    public RDFNode(com.hp.hpl.jena.rdf.model.RDFNode node) {
	this.node = node;
    }

    public RDFNode(wpsr.parsers.rdf.query.IRDFNode node) {
	this.node = (com.hp.hpl.jena.rdf.model.RDFNode) node.extractRawObject();
    }

    @Override
    public Object extractRawObject() {
	return node;
    }

    @Override
    public IRDFNode as(Class<?> arg0) {
	return new RDFNode(node.as(arg0));
    }

    @Override
    public boolean canAs(Class<?> arg0) {
	return node.canAs(arg0);
    }

    @Override
    public boolean isAnon() {
	return node.isAnon();
    }

    @Override
    public boolean isLiteral() {
	return node.isLiteral();
    }

    @Override
    public boolean isResource() {
	return node.isResource();
    }

    @Override
    public boolean isURIResource() {
	return node.isURIResource();
    }

    @Override
    public String toString() {
	return node.toString();
    }
}