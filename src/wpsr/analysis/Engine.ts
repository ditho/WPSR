///<reference path="./annotators/AnnotatorsFactory.ts" />
///<reference path="./cas/ICommonAnalysisStructure.d.ts" />

module wpsr.analysis {
  export public class Engine {
    /**
     * Process the common analysis structure (cas) for different
     * annotators.
     * 
     * TODO: process each in an own Worker
     *
     * @param cas common analysis structure
     */
    public static process(cas: ICommonAnalysisStructure) {
      annotators = AnnotatorsFactory.create();
      for (annotator in annotators) {
        annotator.process(cas);
      }
    }
  }
}
