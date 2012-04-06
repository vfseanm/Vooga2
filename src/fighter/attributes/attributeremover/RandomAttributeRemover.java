package fighter.attributes.attributeremover;

import java.util.TreeMap;

import java.util.Random;

import fighter.attributes.*;

public class RandomAttributeRemover implements AttributeRemover {

	Random myRandom = new Random();
	
	public void removeAttribute(TreeMap<String, Attribute> attributes) {
		if (!attributes.isEmpty()) {
			int attributeToRemove = myRandom.nextInt(attributes.size());
			attributes.remove(attributeToRemove);
		}
	}

}
