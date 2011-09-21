package wpsr.parsers.rdf.query;

import java.util.Iterator;

import com.hp.hpl.jena.datatypes.RDFDatatype;

public interface IRDFResource extends IRDFNode {

    public abstract IRDFResource abort();

    public abstract IRDFResource addProperty(IRDFProperty arg0, boolean arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, char arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, double arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, float arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, long arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, Object arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, IRDFNode arg1);

    public abstract IRDFResource addProperty(IRDFProperty arg0, String arg1,
	    RDFDatatype arg2);

    public abstract IRDFResource addProperty(IRDFProperty arg0, String arg1,
	    String arg2);

    public abstract IRDFResource addProperty(IRDFProperty arg0, String arg1);

    public abstract IRDFResource begin();

    public abstract IRDFResource commit();

    public abstract String getLocalName();

    public abstract String getNameSpace();

    public abstract IRDFStatement getProperty(IRDFProperty arg0);

    public abstract String getURI();

    public abstract boolean hasProperty(IRDFProperty arg0, boolean arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, char arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, double arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, float arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, long arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, Object arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, IRDFNode arg1);

    public abstract boolean hasProperty(IRDFProperty arg0, String arg1,
	    String arg2);

    public abstract boolean hasProperty(IRDFProperty arg0, String arg1);

    public abstract boolean hasProperty(IRDFProperty arg0);

    public abstract boolean hasURI(String arg0);

    public abstract Iterator<IRDFStatement> listProperties();

    public abstract Iterator<IRDFStatement> listProperties(IRDFProperty arg0);

    public abstract IRDFResource removeAll(IRDFProperty arg0);

    public abstract IRDFResource removeProperties();
}