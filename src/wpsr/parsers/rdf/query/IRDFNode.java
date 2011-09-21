package wpsr.parsers.rdf.query;

public interface IRDFNode {

    public abstract Object extractRawObject();

    public abstract IRDFNode as(Class<?> arg0);

    public abstract boolean canAs(Class<?> arg0);

    public abstract boolean isAnon();

    public abstract boolean isLiteral();

    public abstract boolean isResource();

    public abstract boolean isURIResource();
}