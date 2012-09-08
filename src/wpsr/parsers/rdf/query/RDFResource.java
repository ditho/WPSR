package wpsr.parsers.rdf.query;

import java.util.ArrayList;
import java.util.Iterator;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RDFResource extends RDFNode implements IRDFResource {

    private Resource resource;

    public RDFResource(Resource resource) {
	super(resource);
	this.resource = resource;
    }

    public RDFResource(IRDFResource resource) {
	super(resource);
	this.resource = (Resource) resource.extractRawObject();
    }

    @Override
    public Object extractRawObject() {
	return resource;
    }

    @Override
    public IRDFResource abort() {
	return new RDFResource(resource.abort());
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, boolean arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, char arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, double arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, float arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, long arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, Object arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, IRDFNode arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, String arg1,
	    RDFDatatype arg2) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1, arg2));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, String arg1, String arg2) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1, arg2));
    }

    @Override
    public IRDFResource addProperty(IRDFProperty arg0, String arg1) {
	return new RDFResource(resource.addProperty(
		(Property) arg0.extractRawObject(), arg1));
    }

    @Override
    public IRDFResource begin() {
	return new RDFResource(resource.begin());
    }

    @Override
    public IRDFResource commit() {
	return new RDFResource(resource.commit());
    }

    @Override
    public String getLocalName() {
	return resource.getLocalName();
    }

    @Override
    public String getNameSpace() {
	return resource.getNameSpace();
    }

    @Override
    public IRDFStatement getProperty(IRDFProperty arg0) {
	return new RDFStatement(resource.getProperty((Property) arg0));
    }

    @Override
    public String getURI() {
	return resource.getURI();
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, boolean arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, char arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, double arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, float arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, long arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, Object arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, IRDFNode arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, String arg1, String arg2) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0, String arg1) {
	return resource.hasProperty((Property) arg0.extractRawObject(), arg1);
    }

    @Override
    public boolean hasProperty(IRDFProperty arg0) {
	return resource.hasProperty((Property) arg0.extractRawObject());
    }

    @Override
    public boolean hasURI(String arg0) {
	return resource.hasURI(arg0);
    }

    @Override
    public Iterator<IRDFStatement> listProperties() {
	final ArrayList<IRDFStatement> statements = new ArrayList<IRDFStatement>();
	final StmtIterator stmtiter = resource.listProperties();
	while (stmtiter.hasNext()) {
	    final Statement stmt = stmtiter.nextStatement();
	    statements.add(new RDFStatement(stmt));
	}
	return statements.iterator();
    }

    @Override
    public Iterator<IRDFStatement> listProperties(IRDFProperty arg0) {
	final ArrayList<IRDFStatement> statements = new ArrayList<IRDFStatement>();
	final StmtIterator stmtiter = resource.listProperties((Property) arg0
		.extractRawObject());
	while (stmtiter.hasNext()) {
	    final Statement stmt = stmtiter.nextStatement();
	    statements.add(new RDFStatement(stmt));
	}
	return statements.iterator();
    }

    @Override
    public IRDFResource removeAll(IRDFProperty arg0) {
	return new RDFResource(resource.removeAll((Property) arg0
		.extractRawObject()));
    }

    @Override
    public IRDFResource removeProperties() {
	return new RDFResource(resource.removeProperties());
    }
}