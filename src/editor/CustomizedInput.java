package editor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import attributes.Attribute;

public class CustomizedInput {

    private Class myClass;
    public CustomizedInput(Class c)
    {
        myClass = c;
    }
    
    public Object run()
    {
            Constructor constructor = getAnnotatedConstructor(myClass);
            Annotation a = constructor.getAnnotation(editorConstructor.class);
            String[] paramNames = ((editorConstructor) a).parameterNames();
            Class[] paramTypes = constructor.getParameterTypes();
            Object[] argList = null;
            // System.out.println(paramNames.length);
            // System.out.println("got here");
            if (!paramNames[0].equals(""))
            {
                argList = new Object[paramNames.length];
                
                for (int i = 0; i < paramNames.length; i++)
                {
                    if(!(paramTypes[i].equals(int.class) || paramTypes[i].equals(int.class) || paramTypes[i].equals(String.class) || paramTypes[i].equals(double.class) ||paramTypes[i].toString().equals("boolean") ))
                    {
                        JOptionPane.showMessageDialog(paramTypes[i].getPrompt());
                    }
                    String selectedValue = JOptionPane
                            .showInputDialog("What would you like the "
                                    + paramNames[i] + " to be?");

                    if (paramTypes[i].equals(int.class))
                    {
                        argList[i] = Integer.parseInt(selectedValue);
                    }
                    if (paramTypes[i].equals(String.class))
                    {
                        argList[i] = selectedValue;
                    }
                    if (paramTypes[i].equals(double.class))
                    {
                        argList[i] = Double.parseDouble(selectedValue);
                    }
                    if (paramTypes[i].toString().equals("boolean"))
                    {
                        argList[i] = Boolean.parseBoolean(selectedValue);
                    }
                }
            }
            Object object = null;
            try {
                object = constructor.newInstance(argList);
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return object;
    }
    public Constructor getAnnotatedConstructor(Class c)
    {
        Constructor[] constructors = c.getConstructors();
        Constructor constructor = null;
        for (Constructor co : constructors)
        {
            if (co.isAnnotationPresent(editorConstructor.class))
            {
                constructor = co;
            }
        }
        if (constructor == null)
        {
            throw new RuntimeException();
        }
        return constructor;
    }
}
