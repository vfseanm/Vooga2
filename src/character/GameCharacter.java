package character;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bonusobjects.PowerUp;

import sprite.AnimatedGameSprite;
import attributes.Attribute;
import attributes.Updateable;

@SuppressWarnings("serial")
public abstract class GameCharacter extends AnimatedGameSprite {

	protected List<Attribute> 		myAttributes;
	
	public GameCharacter(double x, double y,
			List<String> images) {
		super(x, y, images);
		myAttributes = new ArrayList<Attribute>();
	}
	
	protected GameCharacter(){
	    myAttributes=new ArrayList<Attribute>();
	};

	
	public boolean hasAttributeByName(String name) {
		for (Attribute attribute : myAttributes) {
			if (attribute.getClass().getName().equalsIgnoreCase(name))
				return true;
		}
		return false;
	}

	
	public Attribute getAttributeByName(String name) {
		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equalsIgnoreCase(name))
				return attribute;
		}
		return null;
	}

	
	public List<Attribute> getAttributes() {
		return Collections.unmodifiableList(myAttributes);
	}

	
	public void addAttribute(Attribute attribute) {
		myAttributes.add(attribute);
		attribute.setGameCharacter(this);
	}

	
	public void addPowerUp(PowerUp bonus) {
    	for (Attribute toAdd: bonus.getAttributesToOffer()) {
    		addAttribute(toAdd);
    	}
    }
	
	
	public void clearAttributes() {
		myAttributes.clear();
	}

	
	public void removeAttributeByName(String name) {
		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equalsIgnoreCase(name));
			myAttributes.remove(attribute);
		}
	}

	
	private void accessAttributeMethod(String methodStart, String name,
			Object... o) {

		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equals(name)) {
				Class<?> c = attribute.getClass();
				for (Method m : c.getMethods()) {

					if (!m.getName().startsWith(methodStart))
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

	
	public void performAttributeActions(long elapsedTime){
		for (Attribute attribute : myAttributes)
        {
		    
            if (attribute.getClass().getInterfaces().length != 0 &&
                attribute.getClass().getInterfaces()[0].equals(Updateable.class))
            {
                ((Updateable) attribute).update(elapsedTime);
            }
        }
	}
	
	
	public void updateAttribute(String name, Object... o) {
		accessAttributeMethod("modify", name, o);
	}

	
	public void restoreOriginalAttribute(String name) {
		accessAttributeMethod("restore", name);
	}

	
	public void invertAttribute(String name) {
		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equalsIgnoreCase(name)
					&& attribute.getClass().getInterfaces()[0]
							.equals(Updateable.class)) {
				((Updateable) attribute).invert();
			}
		}
	}

	
	public void allowAttribute(String name, boolean activity) {
		for (Attribute attribute : myAttributes) {
			if (attribute.getName().equalsIgnoreCase(name)) {
				attribute.setActivity(activity);
			}
		}
	}
	
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(getName() + "\n");
		for (Attribute attribute : getAttributes()) {
			toReturn.append(attribute.toString());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}
	
	public abstract String getName();

}
