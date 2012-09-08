package wpsr.engines.types;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/**
 *
 * @author tb006
 */
public class RDFTree_Type extends RDFGeneric_Type {
    public final static int typeIndexID = RDFTree.typeIndexID;
    public final static boolean featOkTst = JCasRegistry.getFeatOkTst("wpsr.engines.types.RDFTree");

    @Override
    protected FSGenerator getFSGenerator() {
	return fsGenerator;
    }

    private final FSGenerator fsGenerator = new FSGenerator() {
	public FeatureStructure createFS(int addr, CASImpl cas) {
	    if (RDFTree_Type.this.useExistingInstance) {
		// Return eq fs instance if already created
		FeatureStructure fs = RDFTree_Type.this.jcas
			.getJfsFromCaddr(addr);
		if (null == fs) {
		    fs = new RDFTree(addr, RDFTree_Type.this);
		    RDFTree_Type.this.jcas.putJfsFromCaddr(addr, fs);
		    return fs;
		}
		return fs;
	    } else
		return new RDFTree(addr, RDFTree_Type.this);
	}
    };

    public RDFTree_Type(JCas jcas, Type casType) {
	super(jcas, casType);
	casImpl.getFSClassRegistry().addGeneratorForType(
		(TypeImpl) this.casType, getFSGenerator());
    }
}
