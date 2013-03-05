///<reference path="./ICommonAnalysisStructure.d.ts" />

module wpsr.analysis.cas {
  export public class CASSimple implements ICommonAnalysisStructure {
    
    private sofa;
    private candidates;

    public setSofa(sofa) {
      this.sofa = sofa;
    }

    public resetSofa() {
      this.sofa = null;
    }

    public getSofa() {
      return this.sofa;
    }

    public setCandidates(candidates) {
      this.candidates = candidates;
    }

    public resetCandidates() {
      this.candidates = null;
    }

    public getCandidates() {
      return this.candidates;
    }

    public getCandidate(index) {
      return this.candidates[index];
    }

    public addCandidate(candidate) {
      this.candidates.push(candidate);
    }
  }
}
