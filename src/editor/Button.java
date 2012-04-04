package editor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sprite.Behavior;

import com.golden.gamedev.gui.TButton;


public class Button extends TButton
{
    private boolean pressed;
    private BufferedImage myImage;
    private String imageName;
    private String myType;
    private ArrayList<Behavior> myBehaviors;


    public Button (String name,
                    int x,
                    int y,
                    int width,
                    int height,
                    BufferedImage i,
                    String imName,
                    String type, ArrayList<Behavior> b)
    {
        super(name, x, y, width, height);
        myBehaviors = new ArrayList<Behavior>();
        myImage = i;
        imageName = imName;
        myType = type;
    }

public ArrayList<Behavior> getBehaviors(){
    return myBehaviors;
}
    public boolean getClicked ()
    {
        boolean t = pressed;
        pressed = false;
        return t;
    }


    public BufferedImage getImage ()
    {
        return myImage;
    }


    public void doAction ()
    {
        
        pressed = true;
    }


    public String getType ()
    {
        return myType;
    }


    public String getImageName ()
    {
        return imageName;
    }

}
