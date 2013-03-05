package wpsr.engines.annotators;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.joone.helpers.factory.JooneTools;
import org.joone.net.NeuralNet;
import wpsr.engines.types.RDFGeneric;
import wpsr.readers.INNListReader;

public class WPSRInformationEvaluator extends JCasAnnotator_ImplBase {

    static Logger logger = Logger.getLogger(WPSRInformationEvaluator.class);
    private double[][] input;
    private double[][] output;
    private INNListReader positiveList = null;
    private INNListReader negativeList = null;

    @Override
    public void initialize(UimaContext aContext)
            throws ResourceInitializationException {
        super.initialize(aContext);
        try {
            readEvaluation();
        } catch (final ResourceAccessException ex) {
            logger.error(ex);
        }
    }

    /**
     * Evaluating scores retrieved from WPSRInformationCollector (currently
     * SearchBox is supported only)
     *
     * @param aJCas
     */
    @Override
    public void process(JCas aJCas) {
        /*final ArrayList<Double> data = new ArrayList<Double>();
        final AnnotationIndex<Annotation> annoIndex = aJCas.getAnnotationIndex(RDFGeneric.type);
        final FSIterator<Annotation> annoIterator = annoIndex.iterator();
        while (annoIterator.hasNext()) {
            RDFGeneric anno = (RDFGeneric) annoIterator.next();
            data.add(anno.getScores().getScores());
        }
        // TODO: limit raw type argument
        buildEvaluationMatrix(positiveList.getEvaluation(), negativeList.getEvaluation());
        NeuralNet nnet = JooneTools.create_standard(new int[]{input[0].length, output[0].length}, JooneTools.LOGISTIC);
        //double rmse = JooneTools.train(nnet, input, output, 5000, 0.01, 0, null, false);
        final double results[] = new double[data.size()];
        for (double result : data) {
            results[data.indexOf(result)] = result;
        }
        JooneTools.interrogate(nnet, results);*/
    }

    /**
     * Reads evaluation data from files located in wpsr/resources/nnlists
     *
     * TODO: this is not generic
     *
     * @throws ResourceInitializationException
     * @throws ResourceAccessException
     */
    private void readEvaluation() throws ResourceInitializationException,
            ResourceAccessException {
        if (positiveList == null) {
            positiveList = (INNListReader) getContext().getResourceObject(
                    "NNList_Search_Positive");
        }
        if (negativeList == null) {
            negativeList = (INNListReader) getContext().getResourceObject(
                    "NNList_Search_Negative");
        }
    }

    /**
     * builds evaluation matrix necessary as input for JooneTools
     * 
     * @param positive
     *            data from "positive" list
     * @param negative
     *            data from "negative" list
     */
    private void buildEvaluationMatrix(double[][] positive, double[][] negative) {
        // fill output with 1.0 (positive) and 0.0 (negative)
        output = new double[positive.length + negative.length][1];
        for (int i = 0; i <= positive.length - 1; i++) {
            output[i][0] = 1.0;
        }
        for (int i = positive.length; i <= positive.length + negative.length - 1; i++) {
            output[i][0] = 0.0;
        }
        // build input matrix
        input = new double[positive.length + negative.length][1];
        for (int i = 0; i <= positive.length - 1; i++) {
            input[i] = positive[i];
        }
        for (int i = positive.length; i <= positive.length + negative.length - 1; i++) {
            input[i] = negative[i - positive.length];
        }
    }
}