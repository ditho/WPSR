package wpsr.factory;

import java.util.HashMap;
import wpsr.parsers.dom.DOMProcessor;
import wpsr.parsers.rdf.predicates.Predicate;
import wpsr.parsers.rdf.query.IRDFProperty;

public class PredicateFactory extends ObjectFactory {

    private static final String resource = "wpsr.resources.predicates";

    public PredicateFactory() {
        super(resource);
    }

    public Predicate create(IRDFProperty clazz, DOMProcessor proc) {
        final HashMap<Class<?>, Object> param = new HashMap<Class<?>, Object>();
        param.put(DOMProcessor.class, proc);
        String clazzName = clazz.toString().replace('#', '@');
        return (Predicate) getClassInstance(clazzName, param);
    }
}
