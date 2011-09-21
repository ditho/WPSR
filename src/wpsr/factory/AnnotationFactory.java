package wpsr.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import wpsr.engines.types.RDFGeneric;
import wpsr.parsers.dom.IDOMCandidate;

/**
 *
 * @author tb006
 */
public class AnnotationFactory {

    /**
     * Log4J logger.
     */
    static Logger logger = Logger.getLogger(AnnotationFactory.class);

    /**
     * creates an Annotation
     *
     * @param type - the type of annotation to create
     * @param aJCas -  the CAS to register in the annotation
     * @param begin - pointer to the end of annotation in the artefact
     * @param end - pointer to the end of annotation in the artefact
     * @return
     */
    public static void create(String type, JCas aJCas, IDOMCandidate candidate) {
        Class[] classParam = new Class[]{JCas.class, int.class, int.class, double.class, String.class};
        try {
            Class clazz = Class.forName("wpsr.engines.types." + type);
            Constructor<RDFGeneric> con = clazz.getConstructor(classParam);
            RDFGeneric anno = con.newInstance(aJCas,
                    candidate.getBegin(),
                    candidate.getEnd(),
                    candidate.getScore(),
                    candidate.getRole());
            anno.addToIndexes();
        } catch (InstantiationException ex) {
            logger.error(ex);
        } catch (IllegalAccessException ex) {
            logger.error(ex);
        } catch (IllegalArgumentException ex) {
            logger.error(ex);
        } catch (InvocationTargetException ex) {
            logger.error(ex);
        } catch (NoSuchMethodException ex) {
            logger.error(ex);
        } catch (SecurityException ex) {
            logger.error(ex);
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        }
    }
}
