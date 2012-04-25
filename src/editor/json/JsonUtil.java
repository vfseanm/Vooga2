package editor.json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JsonUtil {
    
    public static Object getObjectFromJson(String className, String json)
    {
        Class specifiedClass;

        try
        {
            specifiedClass = Class.forName(className.substring(6));
            Class typeList[] = new Class[1];
            typeList[0] = String.class;
            Method method = specifiedClass.getMethod("fromJson", typeList);
            Object object = method.invoke(null, json);
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
}
