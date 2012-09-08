package wpsr.engines.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 *
 * @author tb006
 */
public class RDFTab extends RDFGeneric {

    public final static int typeIndexID = JCasRegistry.register(RDFTab.class);
    public final static int type = typeIndexID;

    @Override
    public int getTypeIndexID() {
	return typeIndexID;
    }

    protected RDFTab() {
    }

    public RDFTab(int addr, TOP_Type type) {
	super(addr, type);
	readObject();
    }

    public RDFTab(JCas jcas) {
	super(jcas);
	readObject();
    }

    public RDFTab(JCas jcas, int begin, int end) {
	super(jcas, begin, end);
	readObject();
    }

    public RDFTab(JCas jcas, int begin, int end, double score, String role) {
        super(jcas, begin, end, score, role);
        readObject();
    }

    private void readObject() {
    }
}
