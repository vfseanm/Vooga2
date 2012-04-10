package fighter.attributes.attributeremover;

import java.util.TreeMap;

import attributes.Attribute;
import fighter.attributes.*;

public interface AttributeRemover {

	public abstract void removeAttribute(TreeMap<String, Attribute> attributes);
}
