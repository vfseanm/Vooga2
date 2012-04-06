package fighter;

import java.util.List;
import attributes.Attribute;

public class FirstAttributeRemover implements AttributeRemover {

	public void removeAttribute(List<Attribute> attributes) {
		if (!attributes.isEmpty()) {
			attributes.remove(0);
		}
	}
	
}
