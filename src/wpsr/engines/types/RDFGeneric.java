package wpsr.engines.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

public class RDFGeneric extends Annotation {

    public final static int typeIndexID = JCasRegistry.register(RDFGeneric.class);
    public final static int type = typeIndexID;
    private String role = null;
    private double score = 0;

    @Override
    public int getTypeIndexID() {
	return typeIndexID;
    }

    protected RDFGeneric() {
    }

    public RDFGeneric(int addr, TOP_Type type) {
	super(addr, type);
	readObject();
    }

    public RDFGeneric(JCas jcas) {
	super(jcas);
	readObject();
    }

    public RDFGeneric(JCas jcas, int begin, int end) {
	super(jcas, begin, end);
	readObject();
    }

    public RDFGeneric(JCas jcas, int begin, int end, double score, String role) {
        super(jcas, begin, end);
        this.score = score;
        this.role = role;
    }

    private void readObject() {
    }

    public double getScore() {
        return score;
    }

    public String getRole() {
        return role;
    }
}