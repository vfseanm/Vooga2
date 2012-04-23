package sprite;

import java.awt.Color;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
 import java.lang.reflect.Type;
import java.util.ArrayList;
 import java.util.List;

import javax.imageio.ImageIO;


import collisions.CollisionAction;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.sprite.AdvanceSprite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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
    
    public void setImageNamesandImages(List<String> imageNames)
    {
        myImageNames = imageNames;
        BufferedImage[] images = new BufferedImage[imageNames.size()];
        BaseLoader loader = new BaseLoader(new BaseIO(AnimatedGameSprite.class), Color.PINK);
        for(int i=0; i<images.length; i++)
        {
            images[i] = loader.getImage(imageNames.get(i));      
        }
        this.setImages(images);
        
    }
    
    public Class<? extends CollisionAction> getActionClass (){
    	return null; 
    }
    

	public String toJson()
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> paramList = new ArrayList<String>();
        paramList.add(gson.toJson(myImageNames));
        paramList.add(myGroup);
        paramList.add(this.getX()+"");
        paramList.add(this.getY()+"");
        return gson.toJson(paramList);
        
    }
    public static AnimatedGameSprite fromJson(String json)
    {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<String>>(){}.getType();
        List<String> paramList = gson.fromJson(json, collectionType);
        List<String> imageNames = gson.fromJson(paramList.get(0), collectionType);
        String groupName = paramList.get(1);
        double x = Double.parseDouble(paramList.get(2));
        double y = Double.parseDouble(paramList.get(3));
        AnimatedGameSprite sprite = new AnimatedGameSprite(x, y,imageNames );
        sprite.setGroup(groupName);
        return sprite;
        
        
    }
}
