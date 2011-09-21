package wpsr.parsers.rdf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFProperty;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;
import wpsr.parsers.rdf.query.RDFResource;
import wpsr.parsers.rdf.query.RDFStatement;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RDFFileParser implements SharedResourceObject, IRDFFileParser {

    /**
     * Log4J logger.
     */
    static Logger logger = Logger.getLogger(RDFFileParser.class);

    private final Model model = ModelFactory.createDefaultModel();

    private Model getModel() {
	return model;
    }

    private Object getRawObject(IRDFNode node) {
        if(node != null)
            return node.extractRawObject();
        return null;
    }

    @Override
    public void load(DataResource aData) throws ResourceInitializationException {
	InputStream in = null;
	try {
	    in = aData.getInputStream();
	    getModel().read(in, "");
	} catch (final IOException e) {
	    throw new ResourceInitializationException(e);
	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (final IOException e) {
                    logger.error(e);
		}
	    }
	}
    }

    @Override
    public Iterator<IRDFStatement> getAllStatements() {
        
	final ArrayList<IRDFStatement> statements = new ArrayList<IRDFStatement>();
	final StmtIterator stmtiter = getModel().listStatements();
	while (stmtiter.hasNext()) {
	    final Statement stmt = stmtiter.nextStatement();
	    statements.add(new RDFStatement(stmt));
	}
	return statements.iterator();
    }

    @Override
    public Iterator<IRDFStatement> getSpecificStatements(IRDFResource subject,
	    IRDFProperty predicate, IRDFNode object) {
        
	final ArrayList<IRDFStatement> statements = new ArrayList<IRDFStatement>();
        Resource rsubject = (Resource) getRawObject(subject);
	Property rpredicate = (Property) getRawObject(predicate);
	RDFNode robject = (RDFNode) getRawObject(object);

	final StmtIterator iterator = getModel().listStatements(rsubject,
		rpredicate, robject);
	while (iterator.hasNext()) {
	    final Statement stmt = iterator.nextStatement();
	    statements.add(new RDFStatement(stmt));
	}
	return statements.iterator();
    }

    @Override
    public Iterator<IRDFStatement> getStatements(IRDFNode subject,
	    IRDFProperty predicate, IRDFNode object) {
        
	final Resource resource = (Resource) subject.extractRawObject();
	final RDFResource rdfsubject = new RDFResource(resource);
	return getSpecificStatements(rdfsubject, predicate, object);
    }
}