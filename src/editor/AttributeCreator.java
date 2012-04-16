package editor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import attributes.Attribute;

public class AttributeCreator {
    
    private Constructor myConstructor;
    private Object[] myArguments;
    
    public AttributeCreator(Constructor c, Object[] args)
    {
        myConstructor = c;
        myArguments = args;
    }
    
    public Attribute createAttribute()
    {
        try {

            return (Attribute) myConstructor.newInstance(myArguments);
            
        } catch (IllegalArgumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }
    public Class getCreatingClass()
    {
        return myConstructor.getDeclaringClass();
    }

}
