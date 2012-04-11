package fighter.attributes.attributeremover;

import java.util.TreeMap;

import attributes.Attribute;

import fighter.attributes.*;

public class AllAttributesRemover implements AttributeRemover {

	public void removeAttribute(TreeMap<String, Attribute> attributes) {
		attributes.clear();
	}

}
