package wpsr.engines.types;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

public class RDFSearch_Type extends RDFGeneric_Type {

    public final static int typeIndexID = RDFSearch.typeIndexID;
    public final static boolean featOkTst = JCasRegistry.getFeatOkTst("wpsr.engines.types.RDFSearch");

    @Override
    protected FSGenerator getFSGenerator() {
	return fsGenerator;
    }

    private final FSGenerator fsGenerator = new FSGenerator() {
	public FeatureStructure createFS(int addr, CASImpl cas) {
	    if (RDFSearch_Type.this.useExistingInstance) {
		// Return eq fs instance if already created
		FeatureStructure fs = RDFSearch_Type.this.jcas
			.getJfsFromCaddr(addr);
		if (null == fs) {
		    fs = new RDFSearch(addr, RDFSearch_Type.this);
		    RDFSearch_Type.this.jcas.putJfsFromCaddr(addr, fs);
		    return fs;
		}
		return fs;
	    } else
		return new RDFSearch(addr, RDFSearch_Type.this);
	}
    };

    public RDFSearch_Type(JCas jcas, Type casType) {
	super(jcas, casType);
	casImpl.getFSClassRegistry().addGeneratorForType(
		(TypeImpl) this.casType, getFSGenerator());
    }
}