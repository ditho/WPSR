package wpsr.engines.annotators;

import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import wpsr.factory.AnnotationFactory;
import wpsr.parsers.dom.DOMCandidateManager;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.dom.IDOMCandidate;
import wpsr.parsers.html.IHTMLParser;
import wpsr.parsers.html.JHTMLParser;
import wpsr.parsers.rdf.IRDFFileParser;
import wpsr.parsers.rdf.RDFParserMap;
import wpsr.parsers.rdf.RDFProcessor;

public class WPSRInformationCollector extends JCasAnnotator_ImplBase {

    static Logger logger = Logger.getLogger(WPSRInformationCollector.class);
    private static IRDFFileParser rdfFileParser;

    @Override
    public void initialize(UimaContext aContext)
            throws ResourceInitializationException {
        super.initialize(aContext);
    }

    @Override
    public void process(JCas aJCas) {
        
        
        
        String annoType = null;
        try {
            annoType = aJCas.getView("RDFType").getDocumentText();
            if(RDFParserMap.hasRDFParser(annoType)) {
                rdfFileParser = RDFParserMap.getRDFParser(annoType);
            } else {
                rdfFileParser = (IRDFFileParser) getContext().getResourceObject(annoType);
                RDFParserMap.setRDFParser(annoType, rdfFileParser);
            }
        } catch (final CASException ex) {
            logger.error(ex);
        } catch (final ResourceAccessException ex) {
            logger.error(ex);
        }
        
        
        
        
        if (rdfFileParser == null || annoType == null) {
            return;
        }
        // TODO: this would not be needed anymore if we can get a DOM by
        // Mozilla Firefox this need to build a reference in the cas object
        // from the given DOM -> the DOMProcessor have to be replaced by the 
        // org.w3c.dom.Document
        IHTMLParser parser = new JHTMLParser(aJCas.getDocumentText());
        DOMProcessor processor = new DOMProcessor(parser.parseHTML());
        // process all Statements from the RDF/XML-Description and sort them
        // before. At first process the TypePredicate to choose the right HTML
        // Elements. After that process the Property- and the ElementValuePredicate
        // to classify the attributes and the value of the HTML Element. At last
        // process the ChildrenPredicate and CardinalityPredicate together to
        // check the structure of that candidates.
        DOMCandidateManager candidateManager = new DOMCandidateManager(processor);
        RDFProcessor rdfProcessor = new RDFProcessor();
        candidateManager.process(rdfProcessor.sortStatements(rdfFileParser));
        // Update the CAS object with the best candidates to inform any other
        // component that process the new CAS, like the WPSRInformationEvaluator.
        // To evaluate the processed score we add the score, the begin and the
        // end of candidate to a specific annotation type that points to a place
        // in the Subject of Analysis (Sofa).
        for (IDOMCandidate candidate : candidateManager.getBest()) {
            AnnotationFactory.create(annoType, aJCas, candidate);
        }
    }

    public static IRDFFileParser getRDFParser() {
        return WPSRInformationCollector.rdfFileParser;
    }
}