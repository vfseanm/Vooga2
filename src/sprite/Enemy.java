package sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;


public class Enemy extends GameSprite
{
    protected ArrayList<Behavior> behaviors;
    public Enemy (BufferedImage im, double x, double y, String image)
    {
        super(im, x, y, image);
    }
public void addBehavior(Behavior b)
{
    behaviors.add(b);
}
}
