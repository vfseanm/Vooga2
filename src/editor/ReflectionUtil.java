package editor;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



public class ReflectionUtil {

    @SuppressWarnings("rawtypes")
    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
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
    private static List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException
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
                try
                {
                    c = Class.forName(packageName + '.'
                            + fileName.substring(0, fileName.length() - 6));
                } catch (ExceptionInInitializerError e)
                {

                    c = Class.forName(
                            packageName
                                    + '.'
                                    + fileName.substring(0,
                                            fileName.length() - 6), false,
                            Thread.currentThread().getContextClassLoader());
                }
                classes.add(c);
            }
        }
        return classes;
    }

    @SuppressWarnings("rawtypes")
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
   public static ArrayList<Class> getInstancesOf(String packageName, Class superClass)
            throws ClassNotFoundException, IOException
    {
        ArrayList<Class> behaviors = new ArrayList<Class>();
        List<Class> list = getClasses(packageName);

        for (Class c : list)
        {
            Class myClass = c;
            while (myClass.getSuperclass() != null)
            {

                if (myClass.getSuperclass().equals(superClass))
                {
                    behaviors.add(c);
                    break;

                }
                myClass = myClass.getSuperclass();

            }

        }
        return behaviors;
    }

    
    
    
    @SuppressWarnings("rawtypes")
	public static Object wrapObject(List<String> classNames, Object simpleObject)
    {
        Object wrappedObject = simpleObject;
        Object[] list = new Object[1];
        list[0] = wrappedObject;
        for(String wrappingClass: classNames)
        {     
                try {
                    Class attributeClass = Class.forName(wrappingClass.substring(6));
                    Constructor constructor=  attributeClass.getConstructors()[0];
                    wrappedObject = constructor.newInstance(list);
                } catch (IllegalArgumentException e) {
                  
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    
                    e.printStackTrace();
                } catch (ClassNotFoundException e)
                {
                   
                    e.printStackTrace();
                }
                list[0] = wrappedObject ;
                  
        }
        return wrappedObject;
    }

    public ReflectionUtil()
    {
    };

}
