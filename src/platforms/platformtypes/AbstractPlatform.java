package platforms.platformtypes;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import collisions.CollisionAction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import editor.Reflection;




import java.util.ResourceBundle;
import sprite.AnimatedGameSprite;

/**
 * This class provides abstract functionality for platform classes. It is the
 * super class for every type of platform.
 * 
 * @author yankeenjg
 * 
 */
public abstract class AbstractPlatform extends AnimatedGameSprite {

	private static final long serialVersionUID = 1483938382856783084L;
	transient protected ResourceBundle myPlatformResources = ResourceBundle
    .getBundle("platforms.PlatformResourceBundle");;
	/**
	 * Super constructor used for a simple platform
	 * 
	 * @param x
	 *            double that represents the x position of the platform in the
	 *            game
	 * @param y
	 *            double represents the y position of the platform in the game
	 * @param imageSources
	 *            list of filenames used to retrieve image files that will
	 *            represent the platform in the game
	 */

	protected AbstractPlatform(double x, double y, List<String> imageSources) {
		super(x, y, imageSources);
		/*myPlatformResources = ResourceBundle
        .getBundle("platforms.PlatformResourceBundle");*/
	}

	/**
	 * Super constructor used for decorated platforms
	 */
	protected AbstractPlatform() {
	    myPlatformResources = ResourceBundle
        .getBundle("platforms.PlatformResourceBundle");
	}

	/**
	 * Function that implements the behavior of each type of platform
	 * 
	 * @param speed
	 *            double that defines the movement speed of moving platforms
	 * @param distance
	 *            double that defines the distance that a moving platform should
	 *            move back and forth across
	 */
	protected abstract void doBehavior(double speed, double distance);
	
	public abstract Object clone();
	
    public Class<? extends CollisionAction> getActionClass (){
    	return PlatformAction.class;
    }

    @SuppressWarnings({ "unused", "rawtypes" })
	public String toJson()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        List<String> paramList = new ArrayList<String>();
        paramList.add(gson.toJson(this.getImageNames()));
        paramList.add(this.getGroup());
        paramList.add(this.getX() + "");
        paramList.add(this.getY() + "");
        if(!this.getClass().equals(SimplePlatform.class))
        {
            List<String> classNames = new ArrayList<String>();
            for(Class c: ((DecoratedPlatform) this).getClassesOfDecorators())
            {
                classNames.add(c.toString());
            }
            paramList.add(gson.toJson(classNames));
        }
        else
        {
            paramList.add(gson.toJson(new ArrayList<String>()));
        }
        return gson.toJson(paramList);
        
    }
    
    @SuppressWarnings({ "unused", "rawtypes" })
	public static AbstractPlatform fromJson(String json){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>()
        {}.getType();
        Type collectionType2 = new TypeToken<List<Class>>()
        {}.getType();

        List<String> paramList = gson.fromJson(json, collectionType);
        List<String> imageNames =
            gson.fromJson(paramList.get(0), collectionType);
        String groupName = paramList.get(1);
        double x = Double.parseDouble(paramList.get(2));
        double y = Double.parseDouble(paramList.get(3));
        List<String> classList = gson.fromJson(paramList.get(4), collectionType);
        AbstractPlatform platform = new SimplePlatform(x, y, imageNames);
        platform.setGroup(groupName);        
        return (AbstractPlatform) Reflection.wrapObject(classList, platform);
    }
}
