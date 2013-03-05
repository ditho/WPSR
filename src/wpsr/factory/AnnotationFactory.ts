//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import org.apache.uima.jcas.JCas;
///<reference path="./../parsers/dom/IDOMCandidate.d.ts" />
///<reference path="./../engines/types/RDFGeneric.ts" />

module wpsr.factory {
  export class AnnotationFactory {
    /**
     * creates an Annotation
     *
     * @param type - the type of annotation to create
     * @param aJCas -  the CAS to register in the annotation
     * @param begin - pointer to the end of annotation in the artefact
     * @param end - pointer to the end of annotation in the artefact
     * @return
     */
    public static create(type: String, aJCas: JCas, candidate: IDOMCandidate): void {
      Class[] classParam = new Class[]{JCas.class, int.class, int.class, double.class, String.class};
      try {
        Class clazz = Class.forName("wpsr.engines.types." + type);
        // TODO: transform generic
        Constructor<RDFGeneric> con = clazz.getConstructor(classParam);
        anno: RDFGeneric = con.newInstance(aJCas,
                    candidate.getBegin(),
                    candidate.getEnd(),
                    candidate.getScore(),
                    candidate.getRole());
        anno.addToIndexes();
      } catch (exception) {
	// do nothing
      }
    }
  }
}
