package fighter;

import java.util.TreeMap;
import fighter.attributes.*;

public class LastAttributeRemover implements AttributeRemover {

	public void removeAttribute(TreeMap<String, Attribute> attributes) {
		if (!attributes.isEmpty()) {
			attributes.remove(attributes.lastKey());
		}
	}

}
