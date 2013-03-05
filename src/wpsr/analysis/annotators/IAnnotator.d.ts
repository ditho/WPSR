///<reference path="./../cas/ICommonAnalysisStructure.d.ts" />

module wpsr.engines.annotators {
  export interface IAnnotator {
    process(cas: ICommonAnalysisStructure): void;
  }
}
