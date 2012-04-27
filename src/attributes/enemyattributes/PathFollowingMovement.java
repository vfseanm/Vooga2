package attributes.enemyattributes;

import java.awt.Point;


import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.editorConstructor;
import editor.input.inputTypes.Line;
import editor.json.AttributeFactory;
import editor.json.JsonableAttribute;
import attributes.Attribute;
import attributes.interfaces.Updateable;


@SuppressWarnings("serial")
public class PathFollowingMovement extends Attribute
    implements Updateable, Cloneable, JsonableAttribute
{
    private ArrayList<Point> myPath;
    private int index;
    private int increment = 1;

    @editorConstructor(parameterNames = { "" })
    public PathFollowingMovement (Line path)
    {
        super(path);
        myPath = path.getLine();
        index = 0;
   
    }


    public void update (long elaspedTime)
    {
        if (isActive)
        {
            if (index < myPath.size() && index >= 0)
            {
                myAttributeUser.setLocation(myPath.get(index).getX(),
                                            myPath.get(index).getY());
            }
            else
            {
                invert();
            }
            index += increment;

        }

    }


    public void invert ()
    {
        increment = -increment;

    }


    public void modifyPathFollowingMovement (Point p)
    {
        myPath.add(p);
    }


    public void modifyPathFollowingMovement (Point p, int insertIndex)
    {
        myPath.add(insertIndex, p);
    }


    @Override
    public String getName ()
    {

        return "PathFollowingMovement";
    }


    public String toString ()
    {
        return "Attribute PathFollowingMovement my path is " + myPath;
    }


    @Override
    public Object clone ()
    {
       Line l = new Line();
       l.setLine(myPath);
       return new PathFollowingMovement(l);
    }
    
    public String toJson()
    {
        Gson gson = new Gson();
        return gson.toJson(myPath);
    }
    
    public PathFollowingMovement fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<ArrayList<Point>>(){}.getType();
        ArrayList<Point> path = gson.fromJson(json, collectionType);
        Line l = new Line();
        l.setLine(path);
        return new PathFollowingMovement(l);
    }
    
    
    private PathFollowingMovement(){};
    public static AttributeFactory<PathFollowingMovement> getFactory()
    {
        return new AttributeFactory<PathFollowingMovement>(new PathFollowingMovement());
    }
    

}