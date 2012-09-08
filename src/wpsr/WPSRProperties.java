package wpsr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Loads and manages the global settings of <code>wpsr</code>. Contains also
 * methods to set <code>wpsr</code> propertie values and save them to the
 * propertie file.
 */
public class WPSRProperties {

    /**
     * The Log4J logger.
     */
    static Logger logger = Logger.getLogger(WPSRProperties.class);

    /**
     * The relative path from where to load the wpsr configuration file.
     */
    private static final String WPSR_CONFIG = "wpsr/resources/wpsr.properties";

    /**
     * The relative path where to store the user configurations.
     */
    private static final String USER_CONFIG = "config/user.properties";

    /**
     * The home directory of the current user. If the user home could not be
     * detected during the initialization process of this class the application
     * exits with <code>System.exit(0)</code>!.
     */
    private static String userHome = null;

    /**
     * The wpsr home directory.
     */
    private static String wpsrHome = null;

    /**
     * The <code>wpsr</code> global properties that will be read from <code>
     * wpsrConfig</code> .
     */
    private static Properties wpsrProps = null;

    /**
     * The <code>wpsr</code> user properties that will be stored to <code>
     * userConfig</code> .
     */
    private static Properties userProps = null;

    /**
     * Becomes true after the constructor has initialized the static fields.
     */
    private static boolean isInitialized = false;

    /**
     * The path of the web elements description files.
     */
    private static String rdfElementsPath = null;

    /**
     * Constructor is not used because all static.
     */
    private WPSRProperties() {
    }

    /**
     * used a static block to load the wpsr configurations. This will be stored
     * in all static variable of this class.
     */
    static {
	// detects the users home diretory
	try {
	    userHome = System.getProperty("user.home");
	    if (userHome == null) {
		throw new NullPointerException("no user home directory");
	    }
	    if (userHome.endsWith(File.separator) == false) {
		userHome += File.separator;
	    }
	    wpsrHome = userHome + ".wpsr" + File.separator;
	} catch (final SecurityException e) {
	    // TODO: logger entry is not correct
	    logger.error("Could not detect your user home directory.\n Exit application!");
	    e.printStackTrace();
	    System.exit(0);
	}
	// loads the wpsr configurations, this configurations will always loaded
	// from wpsr directory respectivly from the wpsr.jar
	wpsrProps = new Properties();
	InputStream in = WPSRProperties.class.getClassLoader()
		.getResourceAsStream(WPSR_CONFIG);

	if (in == null) {
	    logger.error("Could not find the wpsr properties file");
	} else {
	    try {
		wpsrProps.load(in);
		in.close();
	    } catch (final Exception e) {
		logger.error("Could not load the wpsr properties from config file.");
		e.printStackTrace();
	    }
	}
	rdfElementsPath = wpsrProps.getProperty("rdf.elements.path");
	// loads the user configurations. This properties will loaded from the
	// properties file within the users home directory if it was already
	// created
	// otherwise it will be created.
	userProps = new Properties();
	// if the user config was already created, load settings from there
	try {
	    in = new FileInputStream(wpsrHome + File.separator + USER_CONFIG);
	    try {
		userProps.load(in);
		in.close();
	    } catch (final Exception e) {
		logger.error("wpsrProperties: Error while reading the settings from user"
			+ "config file at"
			+ wpsrHome
			+ File.separator
			+ USER_CONFIG + ".");
		e.printStackTrace();
	    }
	}
	// no user config yet, create a empty one
	catch (final FileNotFoundException e) {
	    storeUserConfig();
	}
	isInitialized = true;
    }

    /**
     * Determinate if this class was alredy initialized by calling the
     * constructor or not.
     * 
     * @return true if the constructor was already called and the static methods
     *         can be used to retrieve wpsr properties, false if the class is
     *         not initialized yet and the constructor has to be called
     */
    public static boolean isInitialized() {
	return isInitialized;
    }

    /**
     * Saves the user configurations to the users home directory.
     */
    public static void storeUserConfig() {
	try {
	    final File config = new File(wpsrHome + USER_CONFIG);
	    final File parent = config.getParentFile();
	    if (parent != null) {
		parent.mkdirs();
	    }
	    final FileOutputStream out = new FileOutputStream(config);

	    if (userProps != null) {
		userProps.store(out, null);
	    }
	} catch (final Exception e) {
	    final String message = "Could not save the user properties to "
		    + wpsrHome + USER_CONFIG;
	    logger.error(message);
	    e.printStackTrace();
	}
    }

    /**
     * Gets the users home diretory. If the user home could not be detected
     * during the initialization process of this class the application exits
     * with <code>System.exit(0)</code>!.
     * 
     * @return the users home directory
     * 
     * @uml.property name="userHome"
     */
    public static String getUserHome() {
	return userHome;
    }

    /**
     * Gets the wpsr home diretory.
     * 
     * @return the wpsr home directory
     */
    public static String getWPSRHome() {
	return wpsrHome;
    }

    /**
     * Gets the RDF elements descriptor diretory.
     * 
     * @return the RDF elements descriptor directory
     */
    public static String getRdfElementsPath() {
	return rdfElementsPath;
    }
}