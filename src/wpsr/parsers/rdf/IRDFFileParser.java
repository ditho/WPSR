package wpsr.parsers.rdf;

import java.util.Iterator;

import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFProperty;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

public interface IRDFFileParser {

    /**
     * Returns all statements.
     * 
     * @return A statement iterator.
     */
    public abstract Iterator<IRDFStatement> getAllStatements();

    /**
     * getSpecificStatements
     * 
     * @param subject
     * @return
     */
    public abstract Iterator<IRDFStatement> getSpecificStatements(
	    IRDFResource subject, IRDFProperty predicate, IRDFNode object);

    public abstract Iterator<IRDFStatement> getStatements(
	    IRDFNode subject, IRDFProperty predicate, IRDFNode object);

}