//TODO: import org.apache.uima.jcas.JCas;
//TODO: import org.apache.uima.jcas.JCasRegistry;
//TODO: import org.apache.uima.jcas.cas.TOP_Type;
//TODO: import org.apache.uima.jcas.tcas.Annotation;

module wpsr.engines.types {
  export class RDFGeneric extends Annotation {
    static typeIndexID: number = JCasRegistry.register("RDFGeneric");
    static type: number = typeIndexID;
    private role: string = null;
    private score: number = 0;
    constructor();
    constructor(cas: JCas);
    constructor(addr: number, type: TOP_Type);
    constructor(cas: JCas, begin: number, end: number);
    constructor(cas: JCas, begin: number, end: number, score: number, role: string) {
      // TODO: implement all constructors
      super(cas, begin, end);
      //super(addr, type);
      this.score = score;
      this.role = role;
      this.readObject();
    }
    private readObject(): void {
      // do nothing
    }
    public getScore(): number {
      return this.score;
    }
    public getRole(): string {
      return this.role;
    }
    public getTypeIndexID(): number {
      return typeIndexID;
    }
  }
}
