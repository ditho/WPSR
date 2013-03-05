///<reference path="./CASSimple.ts" />
///<reference path="./ICommonAnalysisStructure.d.ts" />

module wpsr.analysis.cas {
  export public class CASFactory {
   /**
     * The Common Analysis Structure Factory creates a generic
     * structure to transport candidates of the analysis and
     * the subject of analysis between different annotators.
     *
     * In the future the factory could create different CAS objects
     * for a particular sofa annotation type, e.g. videos or images.
     *
     * @param sofa subject of analysis
     * @return ICommonAnalysisStructure
     */
    public static create(sofa) : ICommonAnalysisStructure {
      return new CASSimple(sofa);
    }
  }
}
