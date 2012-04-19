package sprite;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;

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
        for(int i=0; i<images.length; i++)
        {
            try
            {
                images[i] = ImageIO.read(new File(imageNames.get(imageNames.size()-1)));
            } catch (IOException e)
            {
                System.out.println("There has been a problem importing your image");
            }
            
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
    

}
