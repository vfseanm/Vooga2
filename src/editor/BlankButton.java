package editor;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;


import com.golden.gamedev.gui.TButton;


public class BlankButton extends TButton
{
    private boolean pressed;
    private BufferedImage myImage;
    private String imageName;
    private String myType;
    private SetGame myView;

    public BlankButton(String name,
                    int x,
                    int y,
                    int width,
                    int height, SetGame view)
    {
        super(name, x, y, width, height);
        myView = view;
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
        myView.addEnemy();
           
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
