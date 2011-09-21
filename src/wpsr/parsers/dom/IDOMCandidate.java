package wpsr.parsers.dom;

import java.util.ArrayList;
import wpsr.parsers.rdf.query.IRDFNode;

public interface IDOMCandidate extends IDOMElement {

    /**
     * If you are searching a positive feature of the candidate maybe from the
     * RDF/XML-Description you have to call this method by success. A feature
     * can be contained in a Literal- or Complex-Statement of the RDF/XML-
     * Description, e.g. a PropertyPredicate like type and a value like text or
     * %search%.<br/><br/>
     * 
     * <b>Warning:</b> Do not forget to call the method <tt>featureSearched()</tt>
     * after/before searching a feature.<br/><br/>
     *
     * TODO: call featureSearched if this method will be called
     */
    public void matchedPosFeature();

    /**
     * If you are searching a negative feature of the candidate maybe from the
     * RDF/XML-Description you have to call this method by success. A feature
     * can be contained in a Literal- or Complex-Statement of the RDF/XML-
     * Description, e.g. a PropertyPredicate like type and a value like text or
     * %search%.<br/><br/>
     *
     * <b>Warning:</b> Do not forget to call the method <tt>featureSearched()</tt>
     * after/before searching a feature.<br/><br/>
     *
     * TODO: call featureSearched if this method will be called
     */
    public void matchedNegFeature();

    /**
     * if you are begin to search a feature or you have already searched them,
     * then call this method.
     *
     * TODO: call featureSearched only if there is no match
     */
    public void featureSearched();

    public Boolean hasMatchedFeatures();
    
    public double getScore();

    public IRDFNode getSubject();

    public String getRole();

    public void setRole(String role);

    public void addAncestor(String uri);

    public void addNotAncestor(String uri);

    public ArrayList<String> getAncestors();

    public ArrayList<String> getNotAncestors();

    public ArrayList<IDOMElement> getAncestorList();
}
