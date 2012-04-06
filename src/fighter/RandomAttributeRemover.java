package fighter;

import java.util.List;
import java.util.Random;

import attributes.Attribute;

public class RandomAttributeRemover implements AttributeRemover {

	Random myRandom = new Random();
	
	public void removeAttribute(List<Attribute> attributes) {
		if (!attributes.isEmpty()) {
			int attributeToRemove = myRandom.nextInt(attributes.size());
			attributes.remove(attributeToRemove);
		}
	}

}
