package character;

import java.util.List;
import attributes.Attribute;
import sprite.AnimatedGameSprite;

@SuppressWarnings("serial")
public abstract class GameCharacter extends AnimatedGameSprite {
	
	protected String myName;

	protected abstract void updateAttribute(String name, Object... o); 
	
	protected abstract void resetAttribute(String name, Object... o);
	
	protected abstract void accessAttributeMethod(String methodStart,String name, Object ... o);
    
    protected abstract boolean hasAttribute(String name);
    
    protected abstract List<Attribute> getAttributes();
    
    protected abstract void addAttribute (Attribute attribute);

    protected abstract void removeAttribute(String name);
    
    protected String getName() {
    	return myName;
    }
	
	public String toString () {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(getName()+"\n");
        for (Attribute attribute : getAttributes()) {
            toReturn.append(attribute.toString());
            toReturn.append("\n");
        }
        return toReturn.toString();
    }
}
