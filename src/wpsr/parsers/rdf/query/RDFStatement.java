package wpsr.parsers.rdf.query;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;

public class RDFStatement implements IRDFStatement {

    private Statement statement = null;

    public RDFStatement(Statement statement) {
	this.statement = statement;
    }

    public RDFStatement(wpsr.parsers.rdf.query.IRDFStatement statement) {
	this.statement = (Statement) statement.extractRawObject();
    }

    @Override
    public Object extractRawObject() {
	return statement;
    }

    @Override
    public IRDFStatement changeObject(boolean arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(char arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(double arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(float arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(long arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(Object arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(IRDFNode arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(String arg0, boolean arg1) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public IRDFStatement changeObject(String arg0, String arg1, boolean arg2) {
	return new RDFStatement(statement.changeObject(arg0, arg1, arg2));
    }

    @Override
    public IRDFStatement changeObject(String arg0, String arg1) {
	return new RDFStatement(statement.changeObject(arg0, arg1));
    }

    @Override
    public IRDFStatement changeObject(String arg0) {
	return new RDFStatement(statement.changeObject(arg0));
    }

    @Override
    public boolean getBoolean() {
	return statement.getBoolean();
    }

    @Override
    public byte getByte() {
	return statement.getByte();
    }

    @Override
    public char getChar() {
	return statement.getChar();
    }

    @Override
    public double getDouble() {
	return statement.getDouble();
    }

    @Override
    public float getFloat() {
	return statement.getFloat();
    }

    @Override
    public int getInt() {
	return statement.getInt();
    }

    @Override
    public String getLanguage() {
	return statement.getLanguage();
    }

    @Override
    public IRDFLiteral getLiteral() {
	return new RDFLiteral(statement.getLiteral());
    }

    @Override
    public long getLong() {
	return statement.getLong();
    }

    @Override
    public IRDFNode getObject() {
	return new RDFNode(statement.getObject());
    }

    @Override
    public wpsr.parsers.rdf.query.IRDFProperty getPredicate() {
	return new RDFProperty(statement.getPredicate());
    }

    @Override
    public IRDFResource getResource() {
	return new RDFResource(statement.getResource());
    }

    @Override
    public short getShort() {
	return statement.getShort();
    }

    @Override
    public String getString() {
	return statement.getString();
    }

    @Override
    public RDFResource getSubject() {
	return new RDFResource(statement.getSubject());
    }

    @Override
    public boolean getWellFormed() {
        return statement.hasWellFormedXML();
    }

    @Override
    public boolean hasWellFormedXML() {
	return statement.hasWellFormedXML();
    }

    @Override
    public boolean isReified() {
	return statement.isReified();
    }

    @Override
    public IRDFStatement remove() {
	return new RDFStatement(statement.remove());
    }

    @Override
    public String toString() {
	return statement.toString();
    }

    @Override
    public wpsr.parsers.rdf.query.IRDFStatement getProperty(IRDFProperty arg0) {
	return new RDFStatement(statement.getProperty((Property) arg0
		.extractRawObject()));
    }

    @Override
    public wpsr.parsers.rdf.query.IRDFStatement getStatementProperty(
	    IRDFProperty arg0) {
	return new RDFStatement(statement.getStatementProperty((Property) arg0
		.extractRawObject()));
    }
}