package wpsr.parsers.rdf.structure;

import java.util.ArrayList;
import java.util.List;

public class RDFTree<T> {

    private RDFTreeNode<T> rootElement;

    public RDFTree() {
	super();
    }

    public RDFTreeNode<T> getRootElement() {
	return this.rootElement;
    }

    public void setRootElement(RDFTreeNode<T> rootElement) {
	this.rootElement = rootElement;
    }

    /**
     * Returns the Tree<T> as a List of Node<T> objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * 
     * @return a List<Node<T>>.
     */
    public List<RDFTreeNode<T>> toList() {
	final List<RDFTreeNode<T>> list = new ArrayList<RDFTreeNode<T>>();
	walk(rootElement, list);
	return list;
    }

    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * 
     * @return the String representation of the Tree.
     */
    @Override
    public String toString() {
	return toList().toString();
    }

    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference
     * * as it recurses down the tree.
     * 
     * @param element
     *            the starting element.
     * @param list
     *            the output of the walk.
     */
    private void walk(RDFTreeNode<T> element, List<RDFTreeNode<T>> list) {
	list.add(element);
	for (final RDFTreeNode<T> data : element.getChildren()) {
	    walk(data, list);
	}
    }
}
