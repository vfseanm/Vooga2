package editor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


import attributes.Attribute;

import com.golden.gamedev.gui.TButton;



public class Button extends TButton
{
    private boolean pressed;
    private BufferedImage[] myImages;
    private String imageName;
    private String myType;
    private ArrayList<Attribute> myAttributes;
    private EditorView myView;
    private Framework myFramework;


    public Button (String name,
                    int x,
                    int y,
                    int width,
                    int height,
                    Framework f, EditorView view)
    {
        super(name, x, y, width, height);
        
        myFramework = f;
        
        myAttributes = new ArrayList<Attribute>();
        myView = view;
    }

public ArrayList<Attribute> getAttributes(){
    return myAttributes;
}
    public boolean getClicked ()
    {
        System.out.println("clicked?" + pressed);
        boolean t = pressed;
        pressed = false;
        return t;
    }


    public BufferedImage[] getImage ()
    {
        return myImages;
    }


    public void doAction ()
    {
        pressed = true;
        myView.setFramework(myFramework);
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
