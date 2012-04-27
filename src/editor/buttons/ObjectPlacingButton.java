package editor.buttons;


import com.golden.gamedev.gui.TButton;
import editor.EditorController;
import editor.Framework;
/**
 * Button that has a framework that can create new sprites to be placed on the screen.
 * @author Sean
 *
 */
public class ObjectPlacingButton extends TButton
{
    private boolean pressed;
    private EditorController myController;
    private Framework myFramework;

    /**
     * 
     * @param name of button
     * @param x location of button
     * @param y location of button
     * @param width of the button
     * @param height of the button
     * @param f: framework associated the button
     * @param controller: the level editor controller
     */
    public ObjectPlacingButton (String name,
                    int x,
                    int y,
                    int width,
                    int height,
                    Framework f, EditorController controller)
    {
        super(name, x, y, width, height);
        myFramework = f;
        myController = controller;
    }
    /**
     * 
     * @return returns true if the button has been clicked
     */
    public boolean getClicked ()
    {
        boolean t = pressed;
        pressed = false;
        return t;
    }
    /**
     *  Performs action when button is clicked
     */
    public void doAction ()
    {
        pressed = true;
        myController.setFramework(myFramework);
    }
    
    
    /**
     *  gets the framework associated with the button
     * @return framework associated with the button
     */
    public Framework getFramework()
    {
        return myFramework;
    }

}
