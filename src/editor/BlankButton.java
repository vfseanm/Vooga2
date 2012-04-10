package editor;


import java.awt.image.BufferedImage;

import com.golden.gamedev.gui.TButton;


public class BlankButton extends TButton
{
    private boolean pressed;
    private BufferedImage myImage;
    private String imageName;
    private String myType;
    private EditorView myView;

    public BlankButton(String name,
                    int x,
                    int y,
                    int width,
                    int height, EditorView view, String type)
    {
        super(name, x, y, width, height);
        myView = view;
        myType = type;
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
        if(myType.contentEquals("enemy"))
            myView.addEnemy();
        else if (myType.contentEquals("platform"))
            myView.addPlatform();
           
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
