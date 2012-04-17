package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.frameworks.Framework;



public class Button extends TButton
{
    private boolean pressed;

    private String myType;

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

        myView = view;
    }


    public boolean getClicked ()
    {
       // System.out.println("clicked?" + pressed);
        boolean t = pressed;
        pressed = false;
        return t;
    }



    public void doAction ()
    {
        pressed = true;
        myView.setFramework(myFramework);
    }
    public Framework getFramework()
    {
        return myFramework;
    }


    public String getType ()
    {
        return myType;
    }




}
