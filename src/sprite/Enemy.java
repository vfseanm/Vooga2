package sprite;

import java.awt.image.BufferedImage;


import java.util.ArrayList;
import sprite.Behavior;


@SuppressWarnings("serial")
public class Enemy extends GameSprite
{
    protected ArrayList<Behavior> behaviors;
    public Enemy (BufferedImage im, double x, double y, String image, ArrayList<Behavior> behavior)
    {
        super(im, x, y, image);
        behaviors = new ArrayList<Behavior>();
        behaviors.addAll(behavior);
    }
public void addBehavior(Behavior b)
{
    behaviors.add(b);
}
}
