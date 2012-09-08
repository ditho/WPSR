package wpsr.parsers.string;

import org.apache.log4j.Logger;

/**
 * Search pattern for your String efficiently. Most pairs of key, value you can
 * find with methods in this class. Just as well you can find Strings with
 * return type boolean and the index of this pattern. If you locate a pattern
 * you are able to find out the value for this pattern.
 * 
 * TODO: Implement algorithms to search a string efficiently.
 * 
 * @author tb006
 * 
 */
public class Parser {
    /**
     * The Log4J logger.
     */
    static Logger logger = Logger.getLogger(Parser.class);

    /**
     * Constructor is not used
     */
    private Parser() {
    }

    /**
     * Get the value for your searched key.
     * 
     * @param str
     *            to parse the value
     * @param index
     *            begin of the value
     * @param esc
     *            end of the value
     * @return the value of the searched key
     */
    public static String parseValue(String str, int index, char esc) {
	String value = "";
	try {
	    // while the index is not out of bound positively
	    // and the character is not an escape one.
	    while (index < str.length() && str.charAt(index) != esc) {
		// expand value with the next character and increase the index
		value += str.charAt(index++);
	    }
	} catch (final IndexOutOfBoundsException e) {
	    logger.error("[msg=" + e.getMessage() + ",str=" + str + ",index="
		    + index + ",esc=" + esc + "]", e);
	}
	logger.debug(value + esc);
	return value;
    }

    /**
     * Get the value for in the given String. This method handles more then one
     * escape character.
     * 
     * @param str
     *            to parse the value
     * @param index
     *            begin of the value
     * @param esc
     *            end of the value
     * @return the value of the searched key
     */
    public static String parseValue(String str, int index, String esc) {
	String value = "";
	try {
	    // while the index is not out of bound positively
	    // and the character is not an escape one.
	    while (index < str.length()
		    && !esc.contains(String.valueOf(str.charAt(index)))) {
		// expand value with the next character and increase the index
		value += str.charAt(index++);
	    }
	} catch (final IndexOutOfBoundsException e) {
	    logger.error("[msg=" + e.getMessage() + ",str=" + str + ",index="
		    + index + ",esc=" + esc + "]", e);
	}
	logger.debug(value + esc);
	return value;
    }
}