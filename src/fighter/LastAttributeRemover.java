package fighter;

import java.util.List;
import attributes.Attribute;

public class LastAttributeRemover implements AttributeRemover {

	public void removeAttribute(List<Attribute> attributes) {
		if (!attributes.isEmpty()) {
			attributes.remove(attributes.size()-1);
		}
	}

}
