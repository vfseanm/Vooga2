package attributes.interfaces;

/**
 * This interface is used to ensure accurate side scrolling. Any Attribute
 * that could potentially impact the Fighter's movement should implement this
 * interface; the getMovement() methods return the horizontal and vertical 
 * components of the movement dictated by the Attribute that implements this
 * interface.
 * 
 * @author Tori
 */
public interface Movement {

	public abstract double getHorizMovement();
	
	public abstract double getVertMovement();
	
}
