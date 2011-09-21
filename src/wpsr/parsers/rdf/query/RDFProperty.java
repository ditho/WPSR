package wpsr.parsers.rdf.query;

import com.hp.hpl.jena.rdf.model.Property;

public class RDFProperty extends RDFResource implements IRDFProperty {

    private Property property = null;

    public RDFProperty(Property property) {
	super(property);
	this.property = property;
    }

    public RDFProperty(IRDFProperty property) {
	super(property);
	this.property = (Property) property.extractRawObject();
    }

    @Override
    public Object extractRawObject() {
	return property;
    }

    @Override
    public int getOrdinal() {
	return property.getOrdinal();
    }

    @Override
    public boolean isProperty() {
	return property.isProperty();
    }
}