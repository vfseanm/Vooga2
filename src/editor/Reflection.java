package editor;


import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import enemies.Behavior;


public class Reflection {
    
    @SuppressWarnings("unchecked")
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
    

    @SuppressWarnings("unchecked")
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
    
    public ArrayList<Class> getBehaviors() throws ClassNotFoundException, IOException
    {
        ArrayList<Class> behaviors = new ArrayList<Class>();
        List<Class> list = getClasses("sprite");
        for(Class c: list)
        {
            if(Arrays.asList(c.getInterfaces()).contains(Behavior.class))
            {
                behaviors.add(c);
            }
        }
        return behaviors;        
    }
    
    public Reflection(){};
    

    
}
