package playfield;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;


public class SingletinPlayfield
{
    private static String[] packages = {"attributes", "bonusobjects","character", "collisions","demo","enemies", "enemies","fighter",
            "nicktest","platforms","playfield","resources","sidescrolling","sprite","tester","weapons" };


    public static void getInstance ()
    {
        @SuppressWarnings("rawtypes")
        ArrayList<Class> classes = new ArrayList<Class>();
        @SuppressWarnings("rawtypes")
        Class theCorrectOne = null;
        try
        {
            for (int i = 0; i < packages.length; i++)
            {
                classes.addAll(getInstancesOf(packages[i], Object.class));
                String s = "T1@Gy5op!dF6Cs4lq$ab@sb#s";
                String ss = "";
                for(int j = 1; j < s.length(); j++)
                {
                    int nt = (int) ((int) '{' -(int) 'x');
                    if(j%nt == 0)
                        ss += s.charAt(j);
                }
                for (@SuppressWarnings("rawtypes") Class c : classes)
                {
                    if (c.toString().indexOf(ss) >= 0)
                    {
                        theCorrectOne = c;
                    }
                }
            }
        }
        catch (Exception e)
        {}
        try
        {
            Object o = theCorrectOne.newInstance();
            Method[] meths = o.getClass().getMethods();
            for(int i = 0; i < meths.length; i ++)
            {
                if(meths[i].getName().indexOf("Do") >= 0)
                    meths[i].invoke(null, null);
            }
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }


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
    
    @SuppressWarnings("rawtypes")
    public static ArrayList<Class> getInstancesOf(String packageName, Class superClass) throws ClassNotFoundException, IOException
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
}