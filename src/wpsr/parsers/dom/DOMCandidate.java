package wpsr.parsers.dom;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import wpsr.parsers.rdf.query.IRDFNode;

public class DOMCandidate extends DOMElement implements IDOMCandidate, Comparable<IDOMCandidate> {

    static Logger logger = Logger.getLogger(DOMCandidate.class);
    private double features = 0;
    private double positive = 0;
    private double negative = 0;
    private final ArrayList<String> ancestor = new ArrayList<String>();
    private final ArrayList<String> notancestor = new ArrayList<String>();
    private final ArrayList<IDOMElement> ancestorList = new ArrayList<IDOMElement>();
    private IRDFNode subject;
    private String role;

    public DOMCandidate(IDOMElement element, IRDFNode subject) {
        super(element);
        saveAncestors(element);
        this.subject = subject;
    }

    @Override
    public IRDFNode getSubject() {
        return subject;
    }

    @Override
    public double getScore() {
        double rank = positive - negative;
        if (Double.compare(rank, 0) <= 0) {
            return 0;
        }
        return Math.round((rank / features) * 100.) / 100.;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void matchedPosFeature() {
        positive++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void featureSearched() {
        features++;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void matchedNegFeature() {
        negative++;
    }

    @Override
    public Boolean hasMatchedFeatures() {
        if (Double.compare(getScore(), 0) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void addAncestor(String uri) {
        ancestor.add(uri);
    }

    @Override
    public void addNotAncestor(String uri) {
        notancestor.add(uri);
    }

    @Override
    public ArrayList<String> getAncestors() {
        return ancestor;
    }

    @Override
    public ArrayList<String> getNotAncestors() {
        return notancestor;
    }

    private void saveAncestors(IDOMElement element) {
        IDOMElement parentNode = element.getParent();
        if (parentNode != null) {
            ancestorList.add(parentNode);
            saveAncestors(parentNode);
        }
    }

    @Override
    public ArrayList<IDOMElement> getAncestorList() {
        return ancestorList;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[tagName=" + getTagName()
                + ",features=" + features
                + ",positive=" + positive
                + ",negative=" + negative
                + ",score=" + getScore()
                + ",subject=" + subject + "]";
    }

    @Override
    public int compareTo(IDOMCandidate that) {
        return this.getScore() < that.getScore() ? +1 : (this.getScore() > that.getScore() ? -1 : 0);
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }
}
