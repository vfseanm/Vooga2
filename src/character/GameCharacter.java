package character;

import java.awt.image.BufferedImage;
import java.util.List;

import sprite.AnimatedGameSprite;
import attributes.Attribute;

@SuppressWarnings("serial")
public abstract class GameCharacter extends AnimatedGameSprite {
	
	 public GameCharacter (BufferedImage[] im, double x, double y, List<String> images) {
		super(im, x, y, images);
	 }


	public abstract void updateAttribute(String name, Object... o); 
	
	public abstract void restoreOriginalAttribute(String name, Object... o);
	
	public abstract void accessAttributeMethod(String methodStart,String name, Object ... o);
    
    public abstract boolean hasAttribute(String name);
    
    public abstract List<Attribute> getAttributes();
    
    public abstract void addAttribute (Attribute attribute);

    public abstract void removeAttribute(String name);
    
    public abstract String getName();
	
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
        toReturn.append(getName()+"\n");
        for (Attribute attribute : getAttributes()) {
            toReturn.append(attribute.toString());
            toReturn.append("\n");
        }
        return toReturn.toString();
	}
}
