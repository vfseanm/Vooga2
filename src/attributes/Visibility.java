package attributes;

/**
 * Experimenting with building a layer ontop of the GTGE methods to consolidate
 * all functions into attributes
 * Here I'm thinking cloaked or ghosts
 * @author Alex
 */
public class Visibility extends Attribute
{
    boolean isVisible;


    public Visibility (boolean visible)
    {
        isVisible = visible;
    }


    @Override
    public String getName ()
    {
        return "Visibility";
    }


    public void modifyVisibility (boolean visible)
    {
        isVisible = visible;
        myEnemy.setActive(isVisible);
    }
    
    public String toString(){
        return "Attribute Visibility is currently" + isVisible;
    }

}
