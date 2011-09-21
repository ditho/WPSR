package wpsr.parsers.rdf.query;

import com.hp.hpl.jena.rdf.model.Literal;

public class RDFLiteral extends RDFNode implements IRDFLiteral {
    private Literal literal = null;

    public RDFLiteral(Literal literal) {
	super(literal);
	this.literal = literal;
    }

    public RDFLiteral(wpsr.parsers.rdf.query.IRDFLiteral literal) {
	super(literal);
	this.literal = (Literal) literal.extractRawObject();
    }

    @Override
    public Object extractRawObject() {
	return literal;
    }

    @Override
    public boolean getBoolean() {
	return literal.getBoolean();
    }

    @Override
    public byte getByte() {
	return literal.getByte();
    }

    @Override
    public char getChar() {
	return literal.getChar();
    }

    @Override
    public String getDatatypeURI() {
	return literal.getDatatypeURI();
    }

    @Override
    public double getDouble() {
	return literal.getDouble();
    }

    @Override
    public float getFloat() {
	return literal.getFloat();
    }

    @Override
    public int getInt() {
	return literal.getInt();
    }

    @Override
    public String getLanguage() {
	return literal.getLanguage();
    }

    @Override
    public String getLexicalForm() {
	return literal.getLexicalForm();
    }

    @Override
    public long getLong() {
	return literal.getLong();
    }

    @Override
    public short getShort() {
	return literal.getShort();
    }

    @Override
    public String getString() {
	return literal.getString();
    }

    @Override
    public Object getValue() {
	return literal.getValue();
    }

    @Override
    public boolean getWellFormed() {
        return literal.isWellFormedXML();
    }

    @Override
    public boolean isWellFormedXML() {
	return literal.isWellFormedXML();
    }

    @Override
    public boolean sameValueAs(wpsr.parsers.rdf.query.IRDFLiteral arg0) {
	return literal.sameValueAs((Literal) arg0.extractRawObject());
    }

    @Override
    public String toString() {
	return literal.toString();
    }
}