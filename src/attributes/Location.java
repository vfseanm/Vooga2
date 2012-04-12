package attributes;

/**
 * Experimenting with building a layer ontop of the GTGE methods to consolidate
 * all functions into attributes
 * Failed Experiment
 * @author Alex
 */

public class Location extends Attribute
{

    @Override
    public String getName ()
    {

        return "Location";
    }


    /**
     * Takes in x and y as parameters x first y second relocates enemy associate
     * with attribute
     * 
     * @param x
     * @param y
     */
    public void modifyLocation (double x, double y)
    {
        myEnemy.setX(x);
        myEnemy.setY(y);
    }


    @Override
    public String toString ()
    {
        return "Attribute Location X Coordinate is " + myEnemy.getX() +
               " and Y Coordinate is " + myEnemy.getY();
    }

}
