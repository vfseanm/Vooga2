package fighter.attributes.attributeremover;

import java.util.TreeMap;
import fighter.attributes.*;;

public class FirstAttributeRemover implements AttributeRemover {

	public void removeAttribute(TreeMap<String, Attribute> attributes) {
		if (!attributes.isEmpty()) {
			attributes.remove(attributes.firstKey());
		}
	}
	
}
