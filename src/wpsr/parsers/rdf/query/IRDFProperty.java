package wpsr.parsers.rdf.query;

public interface IRDFProperty extends IRDFResource {

    public abstract int getOrdinal();

    public abstract boolean isProperty();
}