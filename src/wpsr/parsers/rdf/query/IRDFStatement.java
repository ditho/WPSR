package wpsr.parsers.rdf.query;

public interface IRDFStatement {

    public abstract Object extractRawObject();

    public abstract IRDFStatement changeObject(boolean arg0);

    public abstract IRDFStatement changeObject(char arg0);

    public abstract IRDFStatement changeObject(double arg0);

    public abstract IRDFStatement changeObject(float arg0);

    public abstract IRDFStatement changeObject(long arg0);

    public abstract IRDFStatement changeObject(Object arg0);

    public abstract IRDFStatement changeObject(IRDFNode arg0);

    public abstract IRDFStatement changeObject(String arg0, boolean arg1);

    public abstract IRDFStatement changeObject(String arg0, String arg1,
	    boolean arg2);

    public abstract IRDFStatement changeObject(String arg0, String arg1);

    public abstract IRDFStatement changeObject(String arg0);

    public abstract boolean getBoolean();

    public abstract byte getByte();

    public abstract char getChar();

    public abstract double getDouble();

    public abstract float getFloat();

    public abstract int getInt();

    public abstract String getLanguage();

    public abstract IRDFLiteral getLiteral();

    public abstract long getLong();

    public abstract IRDFNode getObject();

    public abstract wpsr.parsers.rdf.query.IRDFProperty getPredicate();

    public abstract IRDFResource getResource();

    public abstract short getShort();

    public abstract String getString();

    public abstract IRDFResource getSubject();

    public abstract boolean getWellFormed();

    public abstract boolean hasWellFormedXML();

    public abstract boolean isReified();

    public abstract IRDFStatement remove();

    public abstract IRDFStatement getProperty(IRDFProperty arg0);

    public abstract IRDFStatement getStatementProperty(IRDFProperty arg0);
}