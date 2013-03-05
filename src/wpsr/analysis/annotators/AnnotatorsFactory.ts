///<reference path="./IAnnotator.d.ts" />
///<reference path="./Collector.ts" />
///<reference path="./Evaluator.ts" />

module wpsr.analysis.annotators {
  export public class AnnotatorsFactory {

    private static annotators = new Array(
      new Collector(),
      new Evaluator()
    );

    public static create(): IAnnotator[] {
      return annotators;
    }
  }
}
