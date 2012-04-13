package characters;

import java.awt.image.BufferedImage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sprite.AnimatedGameSprite;

import attributes.Attribute;
import attributes.Updateable;

@SuppressWarnings("serial")
public class GameCharacter extends AnimatedGameSprite {
	
	
	protected String					myName;
	protected List<Attribute>			myAttributes; 
	
	
	
	public GameCharacter(BufferedImage[] image, double x, double y, List<String> images) {
		super(image, x, y, images);
		myAttributes = new ArrayList<Attribute>();
	}

	
    public void updateCharacter(long elapsedTime) {
		for (Attribute attribute : myAttributes) {

			if (attribute.getClass().getInterfaces().length != 0
					&& attribute.getClass().getInterfaces()[0]
							.equals(Updateable.class)) {
				try {

					((Updateable) attribute).update(elapsedTime);
				} catch (ClassCastException e) {

					e.printStackTrace();
				}
			}
		}
	}
    
    
	public void updateAttribute(String name, Object... o) {

		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equals(name)) {
				Class<?> c = attribute.getClass();
				for (Method m : c.getMethods()) {
					if (!m.getName().startsWith("modify"))
						continue;
					if (m.getGenericParameterTypes().length != o.length)
						continue;
					for (int i = 0; i < m.getGenericParameterTypes().length; i++) {
						Class<?> t = m.getParameterTypes()[i];
						if (!t.equals(o[i])) {

							continue;
						}

					}

					try {
						m.invoke(attribute, o);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}
   
	
	
    public boolean hasAttribute(String name) {
        for(Attribute attribute: myAttributes) {
            if (attribute.getClass().getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    
    
    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(myAttributes);
    }
    
    
    
    public void addAttribute (Attribute attribute) {
        myAttributes.add(attribute);
    }

    
    
    public void removeAttribute(String name) {
        for (Attribute attribute: myAttributes) {
            if (attribute.getName().equalsIgnoreCase(name));
                myAttributes.remove(attribute);
        }
        
    }
    
    public void clearAttributes(){
        myAttributes.clear();
    }
    
    
    public String getName() {
    	return myName;
    }
	
    
    
	public String toString () {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(getName()+"\n");
        for (Attribute attribute : myAttributes) {
            toReturn.append(attribute.toString());
            toReturn.append("\n");
        }
        return toReturn.toString();
    }
}
