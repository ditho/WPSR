package wpsr;

import java.io.IOException;
import org.apache.uima.jcas.tcas.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import wpsr.consumer.CandidateConsumer;
import wpsr.engines.types.RDFGeneric;

/**
 * Web Pattern Structure Recognizer (WPSR)
 * 
 * @author tb006
 *
 */
public class WPSR {

    /**
     * Log4J logger.
     */
    static Logger logger = Logger.getLogger(WPSR.class);
    
    /**
     * Get the Web Pattern Structure Recognizer (WPSR) candidates of the XHTML
     * document refers to the specific annotation type. The candidates will be
     * processed by the UIMA-Framework of Apache. <b>Note: This method work not
     * on the Document Object Model (DOM) by reference.</b> WPSR attach the
     * Framework and collect some Information to process them in the class
     * <tt>WPSRInformationCollector</tt>. Before we could analyze the document,
     * we have to get the <tt>AnalysisEngine</tt> of WPSR that trigger the
     * analysis in the UIMA-Framework and create a Common Anlysis System (CAS)
     * that manages the Artifacts so called the Subject of Analysis (Sofa) and
     * the WPSR type system to prepare the analysis for a specific annotation
     * type of the WPSR type system.<br /><br />
     *
     * If the process will be done by the <tt>WPSRInformationCollector</tt> the
     * analysis go to next level and the candidates will be evaluated by the
     * class <tt>WPSRInformationEvaluator</tt>. The candidates will be evaluated
     * by classification and a neural network.<br /><br />
     *
     * After that we added WAI-ARIA role-, state- and property attributes to the
     * remaining candidates and return them.
     *
     * @param doc - part of document represented in a String e.g. serialized XML
     * @param uri - identifier or part of the uniform resource identifier
     * @param type - type of annotation to be searched
     * 
     * @return an array of candidates associated with the given type
     */
    public static ArrayList<CandidateConsumer> process(String doc, String uri, String type) {
        // reading log4j properties from jar or resource and set up the results
        PropertyConfigurator.configure(WPSR.class.getResource("/wpsr/resources/log4j/log4j.properties"));
        //ArrayList<String> candidateList = new ArrayList<String>();
        ArrayList<CandidateConsumer> candidateList= new ArrayList<CandidateConsumer>();
        try {
            // reading the analysisEngine from jar or resource
            final XMLInputSource in = new XMLInputSource(WPSR.class.getResource("/wpsr/resources/analysis_engine/WPSRAnalysisEngineDescriptor.xml"));
            final ResourceSpecifier aSpecifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
            final AnalysisEngine analysisEngine = UIMAFramework.produceAnalysisEngine(aSpecifier);
            // set up information for the Common Analysis System (CAS): reduce
            // the analysis on the given AnnotationTypeName that points a
            // specific WPSRType from the WPSRTypeSystem (create a View before).
            final CAS aCas = analysisEngine.newCAS();
            aCas.setDocumentText(doc.trim());
            aCas.setDocumentLanguage(uri);
            final CAS aView = aCas.createView("RDFType");
            aView.setSofaDataString(type, "WPSRStrictType");
            // process the reduced CAS: collect and analyze the document of the CAS
            // in the WPSRInformationCollector and evaluate each candidate in the
            // WPSRInformationEvaluator
            analysisEngine.process(aCas);
            // analyze done: get the results from the CAS and add each candidate to
            // the candidate list.
            AnnotationIndex<Annotation> annoIndex = aCas.getJCas().getAnnotationIndex(RDFGeneric.type);
            final Iterator<Annotation> annoIterator = annoIndex.iterator();
            while (annoIterator.hasNext()) {
                final RDFGeneric anno = (RDFGeneric) annoIterator.next();
                logger.debug("candidate[text=" + anno.getCoveredText() + ",role=" + anno.getRole() + ",score=" + anno.getScore() + "]");
                candidateList.add(new CandidateConsumer(anno));
                //candidateList.add(anno.getCoveredText());
            }
            aCas.reset();
            // TODO: create the AnalysisEngine once per lifetime and if the
            // User Agent is closed then destroy the analysis Engine. (init,uninit)
            analysisEngine.destroy();
        } catch (final InvalidXMLException ex) {
            logger.error(ex);
        } catch (final ResourceInitializationException ex) {
            logger.error(ex);
        } catch (final IOException ex) {
            logger.error(ex);
        } catch (AnalysisEngineProcessException ex) {
            logger.warn(ex);
        } catch (CASException ex) {
            logger.error(ex);
        }
        return candidateList;
    }
}
