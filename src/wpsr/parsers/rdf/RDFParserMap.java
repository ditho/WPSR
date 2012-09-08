/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wpsr.parsers.rdf;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tb006
 */
public class RDFParserMap {
    
    public static Map<String, IRDFFileParser> map = new HashMap<String, IRDFFileParser>();
    
    public static IRDFFileParser getRDFParser(String annoType) {
        return map.get(annoType);
    }
    
    public static void setRDFParser(String annoType, IRDFFileParser parser) {
        //if(map == null)
            //map = new HashMap<String, IRDFFileParser>();
        map.put(annoType, parser);
    }
    
    public static boolean hasRDFParser(String annoType) {
        return map.containsKey(annoType);
    }
}