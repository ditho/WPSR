///<reference path="./../cas/ICommonAnalysisStructure.d.ts" />
///<reference path="./IAnnotator.d.ts" />

module wpsr.analysis.annotators {
  export public class Evaluator implements IAnnotator {

    private input: number[][];
    private output: number[][];
  //private INNListReader positiveList = null;
  //private INNListReader negativeList = null;

    public process(cas) {
        /*final ArrayList<Double> data = new ArrayList<Double>();
        final AnnotationIndex<Annotation> annoIndex = cas.getAnnotationIndex(RDFGeneric.type);
        final FSIterator<Annotation> annoIterator = annoIndex.iterator();
        while (annoIterator.hasNext()) {
            RDFGeneric anno = (RDFGeneric) annoIterator.next();
            data.add(anno.getScores().getScores());
        }
        buildEvaluationMatrix();
        NeuralNet nnet = JooneTools.create_standard(new int[]{input[0].length, output[0].length}, JooneTools.LOGISTIC);
        //double rmse = JooneTools.train(nnet, input, output, 5000, 0.01, 0, null, false);
        final double results[] = new double[data.size()];
        for (double result : data) {
            results[data.indexOf(result)] = result;
        }
        JooneTools.interrogate(nnet, results);*/
    }

    /**
     * builds evaluation matrix necessary as input for JooneTools
     */
    private buildEvaluationMatrix(): void {
      positive: number[][] = postiveList.getEvaluation();
      negative: number[][] = negativeList.getEvaluation();
      output[];
      max = positive.length - 1;
      for (i = 0; i <= max; i++) {
        output[i][0] = 1.0;
      }
      max = positive.length + negative.length -1;
      for (i = positive.length; i <= max; i++) {
        output[i][0] = 0.0;
      }
      input[];
      max = positive.length - 1;
      for (int i = 0; i <= max; i++) {
        input[i] = positive[i];
      }
      max = positive.length + negative.length - 1;
      for (i = positive.length; i <= max; i++) {
        input[i] = negative[i - positive.length];
      }
    }
  }
}
