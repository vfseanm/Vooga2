package fighter;

import java.util.List;
import attributes.Attribute;

public class AllAttributesRemover implements AttributeRemover {

	public void removeAttribute(List<Attribute> attributes) {
		attributes.clear();
	}

}
