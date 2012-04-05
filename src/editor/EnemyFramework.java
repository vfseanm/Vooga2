package editor;

import java.awt.image.BufferedImage;

import java.util.ArrayList;

import sprite.Behavior;

public class EnemyFramework {

    protected ArrayList<Behavior> behaviors;
    private BufferedImage myImage;
    private String myName;
    
    public EnemyFramework (BufferedImage im, String name, ArrayList<Behavior> b )
    {
        behaviors = b;
        myImage = im;
        myName = name;
    }
public void addBehavior(Behavior b)
{
    behaviors.add(b);
}
}
