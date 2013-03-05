package wpsr.engines.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

public class RDFNavigation extends RDFGeneric {

    public final static int typeIndexID = JCasRegistry.register(RDFNavigation.class);
    public final static int type = typeIndexID;

    @Override
    public int getTypeIndexID() {
        return typeIndexID;
    }

    protected RDFNavigation() {
    }

    public RDFNavigation(int addr, TOP_Type type) {
        super(addr, type);
        readObject();
    }

    public RDFNavigation(JCas jcas) {
        super(jcas);
        readObject();
    }

    public RDFNavigation(JCas jcas, int begin, int end) {
        super(jcas, begin, end);
        readObject();
    }

    public RDFNavigation(JCas jcas, int begin, int end, double score, String role) {
        super(jcas, begin, end, score, role);
        readObject();
    }

    private void readObject() {
    }
}
