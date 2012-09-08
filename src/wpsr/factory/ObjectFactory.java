package wpsr.factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import org.apache.log4j.Logger;

public class ObjectFactory {

    static Logger logger = Logger.getLogger(ObjectFactory.class);
    
    private final HashMap<String, String> entryList = new HashMap<String, String>();
    private final HashMap<String, Object> instanceCache = new HashMap<String, Object>();

    public ObjectFactory(String resource) {
	// load keys and classnames from ressource
	ResourceBundle res = null;
	String className;
	try {
	    // res=ResourceBundle.getBundle("vldb.ksws04.db.connection.resources.database");
	    res = ResourceBundle.getBundle(resource);
	} catch (final Exception e) {
	    logger.error(e.getMessage(), e);
	}
	for (final String type : res.keySet()) {
	    className = res.getString(type);
	    // insert type and classname into hashmap
	    if (!entryList.containsKey(type.toLowerCase())) {
		entryList.put(type.toLowerCase(), className);
	    }
	}
    }

    public String[] getTypes() {
	final String[] result = new String[entryList.size()];
	int i = 0;
	for (final String key : entryList.keySet()) {
	    result[i] = key;
	    i++;
	}
	return result;
    }

    public boolean addEntry(String type, String className) {
	// check whether type exists in hashmap
	// note that addConnection is only temporal, at next start you need to
	// add the converter again
	if (!entryList.containsKey(type.toLowerCase())) {
	    // if not put new type in
	    entryList.put(type.toLowerCase(), className);
	    return true;
	}
	return false;
    }

    public Object getClassInstance(String classname,
	    HashMap<Class<?>, Object> parameters) {
	if (entryList.containsKey(classname.toLowerCase())) {
	    Object Interface;
	    final String className = entryList.get(classname.toLowerCase());
	    // check whether an instance for given type is already created
	    if (!instanceCache.containsKey(classname.toLowerCase())) {
		// not yet cached->create instance and add to cache
		try {
		    if (parameters != null && parameters.size() > 0) {
			final Set<Class<?>> cs = parameters.keySet();
			final Class<?>[] classes = cs.toArray(new Class[cs.size()]);
			final Object[] arguments = parameters.values().toArray();
			final Constructor<?> co = Class.forName(className).getConstructor(classes);
			Interface = co.newInstance(arguments);
		    } else {
			Interface = Class.forName(className).newInstance();
		    }
		    instanceCache.put(classname.toLowerCase(), Interface);
		} catch (final Exception e) {
		    logger.error(e.getMessage(), e);
		    return null;
		}
	    } else {
		// is already cached then give cached instance back
		Interface = instanceCache.get(classname.toLowerCase());
	    }
	    return Interface;
	}
	return null;
    }

    public Object getClassInstance(String classname) {
	return getClassInstance(classname, null);
    }
}