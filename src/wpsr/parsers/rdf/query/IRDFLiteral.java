package wpsr.parsers.rdf.query;

public interface IRDFLiteral extends IRDFNode {

    public abstract boolean getBoolean();

    public abstract byte getByte();

    public abstract char getChar();

    public abstract String getDatatypeURI();

    public abstract double getDouble();

    public abstract float getFloat();

    public abstract int getInt();

    public abstract String getLanguage();

    public abstract String getLexicalForm();

    public abstract long getLong();

    public abstract short getShort();

    public abstract String getString();

    public abstract Object getValue();

    public abstract boolean getWellFormed();

    public abstract boolean isWellFormedXML();

    public abstract boolean sameValueAs(IRDFLiteral arg0);
}