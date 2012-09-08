package wpsr.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

public class NNListReader implements SharedResourceObject, INNListReader {

    static Logger logger = Logger.getLogger(NNListReader.class);
    private double[][] evaluation;

    @Override
    public void load(DataResource aData) throws ResourceInitializationException {
	InputStream inStr = null;
	try {
	    logger.debug("reading[list=" + aData.getUrl() + "]");
	    // open input stream to data
	    inStr = aData.getInputStream();
	    final BufferedReader in = new BufferedReader(new InputStreamReader(
		    inStr));
	    String text = "";
	    final ArrayList<double[]> results = new ArrayList<double[]>();

	    while ((text = in.readLine()) != null) {
		final ArrayList<Double> line = new ArrayList<Double>();
		final StringTokenizer st = new StringTokenizer(text, ";");
		while (st.hasMoreTokens()) {
		    // get next token
		    final String field = st.nextToken();
		    final double dfield = Double.parseDouble(field);
		    line.add(dfield);
		}
		final double[] doubleLine = new double[line.size()];
		for (int i = 0; i <= doubleLine.length - 1; i++) {
		    doubleLine[i] = line.get(i).doubleValue();
		}
		results.add(doubleLine);
	    }
	    final double[][] doubleEvaluation = new double[results.size()][];
	    for (int i = 0; i <= doubleEvaluation.length - 1; i++) {
		doubleEvaluation[i] = results.get(i);
	    }
	    evaluation = doubleEvaluation;
	} catch (final IOException e) {
	    throw new ResourceInitializationException(e);
	} finally {
	    if (inStr != null) {
		try {
		    inStr.close();
		} catch (final IOException e) {
		}
	    }
	}
    }

    @Override
    public double[][] getEvaluation() {
	return evaluation;
    }
}