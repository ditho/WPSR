package wpsr.engines.types;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

public class RDFIdent_Type extends RDFGeneric_Type {

    public final static int typeIndexID = RDFIdent.typeIndexID;
    public final static boolean featOkTst = JCasRegistry.getFeatOkTst("wpsr.engines.types.RDFIdent");

    protected FSGenerator getFSGenerator() {
	return fsGenerator;
    }

    private final FSGenerator fsGenerator = new FSGenerator() {
	public FeatureStructure createFS(int addr, CASImpl cas) {
	    if (RDFIdent_Type.this.useExistingInstance) {
		// Return eq fs instance if already created
		FeatureStructure fs = RDFIdent_Type.this.jcas
			.getJfsFromCaddr(addr);
		if (null == fs) {
		    fs = new RDFIdent(addr, RDFIdent_Type.this);
		    RDFIdent_Type.this.jcas.putJfsFromCaddr(addr, fs);
		    return fs;
		}
		return fs;
	    } else
		return new RDFIdent(addr, RDFIdent_Type.this);
	}
    };

    public RDFIdent_Type(JCas jcas, Type casType) {
	super(jcas, casType);
	casImpl.getFSClassRegistry().addGeneratorForType(
		(TypeImpl) this.casType, getFSGenerator());
    }
}