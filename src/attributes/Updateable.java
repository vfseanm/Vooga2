package attributes;

/**
 * This interface is used to distinguish whether or not a given attribute
 * changes during frequent calls of update from the GTGE game class
 * @author Alex
 *
 */
public interface Updateable
{
    public void update(long elaspedTime);

}
