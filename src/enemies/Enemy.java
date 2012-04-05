package enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import sprite.GameSprite;


@SuppressWarnings("serial")
public class Enemy extends GameSprite
{
    private ArrayList<Attribute> myAttributes;


    public Enemy (BufferedImage im, double x, double y, String image)
    {
        super(im, x, y, image);
        myAttributes = new ArrayList<Attribute>();
    }


    public void addAttribute (Attribute attribute)
    {
        myAttributes.add(attribute);
    }

    //Type checking
    public void updateAttribute (String name, Object... o)
    {
        for (Attribute attribute : myAttributes)
        {
            if (attribute.getName().equals(name)) {
                //type checking
                attribute.update(billy,oranatan);
                attribute.update(billy);
            }
        }
        
    }

}
