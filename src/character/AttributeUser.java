package character;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import bonusobjects.BonusObject;

import sprite.AnimatedGameSprite;
import attributes.Attribute;
import attributes.enemyattributes.Attack;
import attributes.interfaces.Input;
import attributes.interfaces.Updateable;

@SuppressWarnings("serial")
public abstract class AttributeUser extends AnimatedGameSprite {

	protected List<Attribute> 		myAttributes;
	
	public AttributeUser(double x, double y,
			List<String> images) {
		super(x, y, images);
		myAttributes = new ArrayList<Attribute>();
	}
	
	protected AttributeUser(){
	    myAttributes = new ArrayList<Attribute>();
	};

	

	


        /**
         * Secret reflection method for sean's uses
         * 
         * @author Alex
         * @param name
         * @return
         */
        public boolean hasAttributeByName (String name)
        {
            for (Attribute attribute : myAttributes)
            {
                if (attribute.getClass().getName().equalsIgnoreCase(name)) return true;
            }
            return false;
        }





        /**
         * Secret reflection method for sean's uses
         * 
         * @author Alex
         * @param name
         * @return
         */

        public List<Attribute> getAttributes ()
        {
            return Collections.unmodifiableList(myAttributes);
        }


        public void addAttribute (Attribute attribute)
        {
            myAttributes.add(attribute);
            attribute.setGameCharacter(this);
        }
        


        public void addAttributeList (ArrayList<Attribute> attributes)
        {
            for (Attribute attribute : attributes)
            {
                addAttribute(attribute);
            }
        }


        public void clearAttributes ()
        {
            myAttributes.clear();
        }


        public void removeAttributeByName (String name)
        {
            for (Attribute attribute : myAttributes)
            {
                if (attribute.getName().equalsIgnoreCase(name)) myAttributes.remove(attribute);
            }

        }


        protected void accessAttributeMethod (String methodStart,
                                            String name,
                                            Object ... o)
        {

            for (Attribute attribute : myAttributes)
            {
                if (attribute.getName().equals(name))
                {
                    Class<?> c = attribute.getClass();
                    for (Method m : c.getMethods())
                    {

                        if (!m.getName().startsWith(methodStart)) continue;
                        if (m.getGenericParameterTypes().length != o.length) continue;
                        for (int i = 0; i < m.getGenericParameterTypes().length; i++)
                        {
                            Class<?> t = m.getParameterTypes()[i];
                            if (!t.equals(o[i]))
                            {

                                continue;
                            }

                        }

                        try
                        {

                            m.invoke(attribute, o);
                        }
                        catch (IllegalArgumentException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }
            }

        }


        public void modifyAttribute (String name, Object ... o)
        {
            accessAttributeMethod("modify", name, o);
        }


        public void restoreOriginalAttribute (String name)
        {
            accessAttributeMethod("restore", name);
        }


        public void invertAttribute (String name)
        {
            for (Attribute attribute : myAttributes)
            {
                if (attribute.getName().equalsIgnoreCase(name))

                {
                    for (Class<?> myInterface : attribute.getClass()
                                                         .getInterfaces())
                    {
                        if (myInterface.equals(Updateable.class))
                        {
                            ((Updateable) attribute).invert();
                        }
                    }
                }
            }
        }


        public void allowAttribute (String name, boolean activity)
        {
            for (Attribute attribute : myAttributes)
            {
                if (attribute.getName().equalsIgnoreCase(name))
                {
                    attribute.setActivity(activity);
                }
            }
        }


        public void update (long elapsedTime)
        {

            
                performUpdateableAttributes(elapsedTime);

            

        }


        public void performUpdateableAttributes (long elapsedTime)
        {
            for (Attribute attribute : myAttributes)
            {

                if (attribute.getClass().getInterfaces().length != 0)
                {
                    for (Class<?> myInterface : attribute.getClass()
                                                         .getInterfaces())
                    {
                        
                        if (myInterface.equals(Updateable.class))
                        {
                            ((Updateable) attribute).update(elapsedTime);
                        }
                    }
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
