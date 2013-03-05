///<reference path="./../../out/Candidate.d.ts" />

module wpsr.analysis.cas {
  export interface ICommonAnalysisStructure {
    setSofa(sofa: any);
    resetSofa();
    getSofa(): any;
    setCandidates(candidates: Candidate[]);
    resetCandidates();
    getCandidates(): Candidate[];
    getCandidate(index: number): Candidate;
    addCandidate(candidate: Candidate);
  }
}
