package wpsr.js.dom;

import org.w3c.dom.Node;

/**
 *
 * @author tb006
 */
public interface IMOZXPathResult {

    static short ANY_TYPE = 0;
    static short NUMBER_TYPE = 1;
    static short STRING_TYPE = 2;
    static short BOOLEAN_TYPE = 3;
    static short UNORDERED_NODE_ITERATOR_TYPE = 4;
    static short ORDERED_NODE_ITERATOR_TYPE = 5;
    static short UNORDERED_NODE_SNAPSHOT_TYPE = 6;
    static short ORDERED_NODE_SNAPSHOT_TYPE = 7;
    static short ANY_UNORDERED_NODE_TYPE = 8;
    static short FIRST_ORDERED_NODE_TYPE = 9;

    // properties
    Boolean getBooleanValue();
    Boolean getInvalidIteratorState();
    double getNumberValue();
    int getResultType();
    Node getSingleNodeValue();
    int getSnapshotLength();
    String getStringValue();

    // methods
    void iterateNext();
    Node snapshotItem(int index);
}
