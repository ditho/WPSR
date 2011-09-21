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
public class RDFTab_Type extends RDFGeneric_Type {

    public final static int typeIndexID = RDFTab.typeIndexID;
    public final static boolean featOkTst = JCasRegistry.getFeatOkTst("wpsr.engines.types.RDFTab");

    @Override
    protected FSGenerator getFSGenerator() {
	return fsGenerator;
    }

    private final FSGenerator fsGenerator = new FSGenerator() {
	public FeatureStructure createFS(int addr, CASImpl cas) {
	    if (RDFTab_Type.this.useExistingInstance) {
		// Return eq fs instance if already created
		FeatureStructure fs = RDFTab_Type.this.jcas
			.getJfsFromCaddr(addr);
		if (null == fs) {
		    fs = new RDFTab(addr, RDFTab_Type.this);
		    RDFTab_Type.this.jcas.putJfsFromCaddr(addr, fs);
		    return fs;
		}
		return fs;
	    } else
		return new RDFTab(addr, RDFTab_Type.this);
	}
    };

    public RDFTab_Type(JCas jcas, Type casType) {
	super(jcas, casType);
	casImpl.getFSClassRegistry().addGeneratorForType(
		(TypeImpl) this.casType, getFSGenerator());
    }
}
