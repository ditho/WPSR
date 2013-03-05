///<reference path="./analysis/sofa/ISubjectOfAnalysis.d.ts" />
///<reference path="./analysis/cas/CASFactory.ts" />
///<reference path="./analysis/cas/ICommonAnalysisStructure.d.ts" />
///<reference path="./analysis/Engine.ts" />

module wpsr {
  /**
   * This is the main class to bind the Web Pattern Structure
   * Recognizer (WPSR) on other programs.
   */
  export public class WPSR {
    /**
     * Get the Common Analysis Structure (CAS) for a particular
     * subject of analysis (sofa).
     * 
     * TODO: create a class for the subject of analysis
     *
     * @param sofa subject of analysis
     * @return ICommonAnalysisStructure 
     */
    public static process(sofa: ISubjectOfAnalysis): ICommonAnalysisStructure {
      cas = CASFactory.create(sofa);
      Engine.process(cas);
      return cas;
    }
  }
}
