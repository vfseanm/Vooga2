package enemies.movement;

import java.awt.Point;
import java.util.List;
import editor.editorConstructor;
import attributes.Attribute;
import attributes.Updateable;


@SuppressWarnings("serial")
public class PathFollowingMovement extends Attribute
    implements Updateable, Cloneable
{
    private List<Point> myPath;
    private int index;
    private int increment = 1;

    @editorConstructor(parameterNames = {"list of points"})
    public PathFollowingMovement (List<Point> path)
    {
        super(path);
        myPath = path;
        index = 0;
    }


    public void update (long elaspedTime)
    {
        if (isActive)
        {
            if (index < myPath.size() && index >= 0)
            {
                myGameCharacter.setLocation(myPath.get(index).getX(),
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
       
        return new PathFollowingMovement(myPath);
    }

}
