package sprite;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import javax.imageio.ImageIO;

import collisions.CustomActionPerformer;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.sprite.AdvanceSprite;


@SuppressWarnings("serial")
public class AnimatedGameSprite extends AdvanceSprite implements Serializable, Cloneable
{

    String myType;
    List<String> myImageNames;
    private String myGroup;


    public AnimatedGameSprite (double x,
                               double y,
                               List<String> imageNames)
    {

        super(getImagesFromNames(imageNames), x, y);
        //setImages(im);
        myType = this.getClass().toString();
        myImageNames = imageNames;
    }


    private static BufferedImage[] getImagesFromNames(List<String> imageNames)
    {
        BufferedImage[] images = new BufferedImage[imageNames.size()];
        BaseLoader loader = new BaseLoader(new BaseIO(AnimatedGameSprite.class), Color.PINK);
        for(int i=0; i<images.length; i++)
        {
            images[i] = loader.getImage(imageNames.get(i));
            /*try
            {
                File f = new File(imageNames.get(i));
                System.out.println(f);
                images[i] = ImageIO.read(f);
            } catch (IOException e)
            {
                System.out.println("There has been a problem importing your image");
            }*/
            
        }
        return images;
    }


    /**
     * Java is implictly calling superclass constructor
     */
    protected AnimatedGameSprite ()
    {

    }


    public String getType ()
    {
        return myType;
    }


    public List<String> getImageNames ()
    {
        return myImageNames;
    }
    
    public void setImageNames(List<String> names)
    {
        myImageNames = names;
    }
    
    public void setGroup(String group){
        myGroup=group;
    }
    
    public String getGroup(){
        return myGroup;
    }
    
    public Object clone()
    {
        
        return new AnimatedGameSprite(this.getX(), this.getY(),myImageNames);
    }
    
	protected void customAction (AnimatedGameSprite sprite1, AnimatedGameSprite sprite2, int collisionType, CustomActionPerformer act){
		if (act!=null){
			act.customAction(sprite1, sprite2, collisionType);
		}
	}
	
	public void action (AnimatedGameSprite otherSprite, int collisionType, CustomActionPerformer act){
		
	}

}
