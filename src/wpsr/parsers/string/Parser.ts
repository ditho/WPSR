package wpsr.parsers.string;

module wpsr.parsers.string {
  export class Parser {
    /**
     * Get the value for your searched key.
     * 
     * @param str to parse the value
     * @param index begin of the value
     * @param esc end of the value
     * @return the value of the searched key
     */
    static parseValue(str: String, index: number, esc: String): String {
      value: String = "";
      try {
        // while the index is not out of bound positively
        // and the character is not an escape one.
        while (index < str.length() && str.charAt(index) != esc) {
	  // expand value with the next character and increase the index
          value += str.charAt(index++);
	}
      } catch (exception) {
        // do nothing
      }
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
}
