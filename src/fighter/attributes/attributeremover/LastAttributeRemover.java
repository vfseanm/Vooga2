package fighter.attributes.attributeremover;

import java.util.TreeMap;

import attributes.Attribute;
import fighter.attributes.*;

public class LastAttributeRemover implements AttributeRemover {

	public void removeAttribute(TreeMap<String, Attribute> attributes) {
		if (!attributes.isEmpty()) {
			attributes.remove(attributes.lastKey());
		}
	}

}
