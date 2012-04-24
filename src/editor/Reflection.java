package editor;


import java.io.File;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import attributes.Attribute;


public class Reflection {
    
    @SuppressWarnings("rawtypes")
    private static List<Class> getClasses(String packageName) throws ClassNotFoundException, IOException 
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String pathName = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(pathName);
        List<File> directories = new ArrayList<File>();
        while (resources.hasMoreElements()) 
        {
            URL resource = resources.nextElement();
            String fileName = resource.getFile();
            String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
            directories.add(new File(fileNameDecoded));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : directories) 
        {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }
    

    @SuppressWarnings({ "rawtypes" })
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException 
    {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) 
        {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) 
        {
            String fileName = file.getName();
            if (file.isDirectory()) 
            {
                assert !fileName.contains(".");
                classes.addAll(findClasses(file, packageName + "." + fileName));
            } else if (fileName.endsWith(".class") && !fileName.contains("$")) 
            {
                Class c;
                try {
                    c = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
                } catch (ExceptionInInitializerError e) {

                    c = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6),
                            false, Thread.currentThread().getContextClassLoader());
                }
                classes.add(c);
            }
        }
        return classes;
    }
    
    public static Constructor getAnnotatedConstructor(Class c)
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
    
    @SuppressWarnings("rawtypes")
    public ArrayList<Class> getInstancesOf(String packageName, Class superClass) throws ClassNotFoundException, IOException
    {
        ArrayList<Class> behaviors = new ArrayList<Class>();
        List<Class> list = getClasses(packageName);
        
        for(Class c: list)
        {
            Class myClass = c;
            while(myClass.getSuperclass()!=null)
            {
            
                
                if(myClass.getSuperclass().equals(superClass))
                {
                    behaviors.add(c);
                    break;
                   
                }
                myClass= myClass.getSuperclass();
                    
            
            }
            
        }
        return behaviors;        
    }
    
    
    public static Object getObjectFromJson(String className, String json)
    {
        Class specifiedClass;

        try
        {
            specifiedClass = Class.forName(className.substring(6));
            Class typeList[] = new Class[1];
            typeList[0] = String.class;
            Method method = specifiedClass.getMethod("fromJson", typeList);
            Object object =  method.invoke(null, json);
            return object;
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

        
    }
    
    public Reflection(){};
    
    

    

    
}
