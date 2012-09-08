package wpsr.engines.types;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

public class RDFGeneric_Type extends Annotation_Type {
    final Feature casFeat_scores;
    final int casFeatCode_scores;

    public final static int typeIndexID = RDFGeneric.typeIndexID;
    public final static boolean featOkTst = JCasRegistry.getFeatOkTst("wpsr.engines.types.RDFGeneric");

    @Override
    protected FSGenerator getFSGenerator() {
	return fsGenerator;
    }

    private final FSGenerator fsGenerator = new FSGenerator() {
	public FeatureStructure createFS(int addr, CASImpl cas) {
	    if (RDFGeneric_Type.this.useExistingInstance) {
		// Return eq fs instance if already created
		FeatureStructure fs = RDFGeneric_Type.this.jcas
			.getJfsFromCaddr(addr);
		if (null == fs) {
		    fs = new RDFGeneric(addr,
			    RDFGeneric_Type.this);
		    RDFGeneric_Type.this.jcas.putJfsFromCaddr(addr,
			    fs);
		    return fs;
		}
		return fs;
	    } else {
		return new RDFGeneric(addr,
			RDFGeneric_Type.this);
	    }
	}
    };

    public RDFGeneric_Type(JCas jcas, Type casType) {
	super(jcas, casType);
	casImpl.getFSClassRegistry().addGeneratorForType(
		(TypeImpl) this.casType, getFSGenerator());
	casFeat_scores = jcas.getRequiredFeatureDE(casType, "scores",
		"wpsr.engines.types.RDFScore", featOkTst);
	casFeatCode_scores = null == casFeat_scores ? JCas.INVALID_FEATURE_CODE
		: ((FeatureImpl) casFeat_scores).getCode();
    }
}