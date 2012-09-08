package wpsr.consumer;

import wpsr.engines.types.RDFGeneric;

/**
 *
 * @author tb006
 */
public class CandidateConsumer {

    private String xml;
    private String role;
    private double score;
    private Object expression;

    public CandidateConsumer(RDFGeneric annotation) {
        this.xml = annotation.getCoveredText();
        this.role = annotation.getRole();
        this.score = annotation.getScore();
    }

    /**
     * @return the coveredText from the subject of analysis
     */
    public String getXMLString() {
        return xml;
    }

    /**
     * @return the WAI-ARIA role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the xpathStmt
     */
    public Object getXPathExpression() {
        return expression;
    }

    /**
     * @param expression the xpathStmt to set
     */
    public void setXPathExpression(Object expression) {
        this.expression = expression;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }
}
