package editor;


import java.awt.image.BufferedImage;

import com.golden.gamedev.gui.TButton;


public class PlayerButton extends TButton
{
    private boolean pressed;
    private BufferedImage myImage;
    private String imageName;
    private String myType;
    private EditorView myView;

    public PlayerButton(String name,
                    int x,
                    int y,
                    int width,
                    int height, EditorView view)
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
        System.out.println("There will be a player configuration box");
        //myView.addEnemy();
           
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
