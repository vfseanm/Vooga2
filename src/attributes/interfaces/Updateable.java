package attributes.interfaces;

/**
 * This interface is used to distinguish whether or not a given attribute
 * changes during frequent calls of update from the GTGE game class
 * @author Alex
 *
 */
public interface Updateable
{
    /**
     * Attributes that interact with the update from GTGE's framework
     * @param elaspedTime the time between the update call
     */
    public void update(long elaspedTime);
    
    /**
     * allows attributes to be inverted
     */
    public void invert();

}
