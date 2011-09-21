package wpsr.parsers.rdf;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import wpsr.parsers.rdf.query.IRDFNode;
import wpsr.parsers.rdf.query.IRDFProperty;
import wpsr.parsers.rdf.query.IRDFResource;
import wpsr.parsers.rdf.query.IRDFStatement;

public class RDFProcessor {

    static Logger logger = Logger.getLogger(RDFProcessor.class.getName());

    public ArrayList<IRDFStatement> sortStatements(IRDFFileParser rdfparser) {
        final Iterator<IRDFStatement> allStatements = rdfparser.getAllStatements();
        ArrayList<IRDFStatement> plainStatements = new ArrayList<IRDFStatement>();
        ArrayList<IRDFStatement> complexStatements = new ArrayList<IRDFStatement>();
        ArrayList<IRDFStatement> typeStatements = new ArrayList<IRDFStatement>();
        ArrayList<IRDFStatement> roleStatements = new ArrayList<IRDFStatement>();
        ArrayList<IRDFStatement> exactStatements = new ArrayList<IRDFStatement>();

        while (allStatements.hasNext()) {
            final IRDFStatement stmt = allStatements.next();
            final IRDFResource subject = stmt.getSubject();
            final IRDFProperty predicate = stmt.getPredicate();
            final IRDFNode object = stmt.getObject();
            logger.debug("[subject=" + stmt.getSubject()
                    + ",predicate=" + stmt.getPredicate()
                    + ",object=" + stmt.getObject() + "]");
            if (!subject.isURIResource()) {
                continue;
            }
            if (object.isLiteral()) {
                plainStatements.add(stmt);
            } else if (object.isURIResource()) {
                if (RDFConstants.WPSR_ROLE.contentEquals(predicate.toString())) {
                    roleStatements.add(stmt);
                    continue;
                }
                if (predicate.toString().contains(RDFConstants.EXACT_MATCH)) {
                    exactStatements.add(stmt);
                    continue;
                }
                if (!object.toString().equals(RDFConstants.ALT_URI)
                        || !object.toString().equals(
                        RDFConstants.BAG_URI)) {
                    typeStatements.add(stmt);
                }
            } else if (predicate.toString().equals(
                    RDFConstants.CHILDREN_TYPE_URI)) {
                complexStatements.add(stmt);
            } else {
                plainStatements.add(stmt);
            }
        }
        typeStatements.addAll(roleStatements);
        typeStatements.addAll(exactStatements);
        typeStatements.addAll(plainStatements);
        typeStatements.addAll(complexStatements);
        return typeStatements;
    }
}