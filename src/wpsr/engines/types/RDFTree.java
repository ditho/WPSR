package wpsr.engines.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 *
 * @author tb006
 */
public class RDFTree extends RDFGeneric {

    public final static int typeIndexID = JCasRegistry.register(RDFTree.class);
    public final static int type = typeIndexID;

    @Override
    public int getTypeIndexID() {
	return typeIndexID;
    }

    protected RDFTree() {
    }

    public RDFTree(int addr, TOP_Type type) {
	super(addr, type);
	readObject();
    }

    public RDFTree(JCas jcas) {
	super(jcas);
	readObject();
    }

    public RDFTree(JCas jcas, int begin, int end) {
	super(jcas, begin, end);
	readObject();
    }

    public RDFTree(JCas jcas, int begin, int end, double score, String role) {
        super(jcas, begin, end, score, role);
        readObject();
    }

    private void readObject() {
    }
}
