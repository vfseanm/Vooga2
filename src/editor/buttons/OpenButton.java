package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.file.LevelLoader;

/**
 * Button used to open level files
 * @author Becky
 *
 */
public class OpenButton extends TButton
{

    EditorView myGame;
    LevelLoader myLoader;

    /**
     * 
     * @param name String name on button
     * @param x x location of button
     * @param y  y location of button
     * @param width of button
     * @param height of button
     * @param s level editor view
     * @param loader Loader used to open file
     */
    public OpenButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       EditorView s,
                       LevelLoader loader)
    {
        super(name, x, y, width, height);
        myGame = s;
        myLoader = loader;
    }

    /**
     *  Opens file when button is pressed
     */
    public void doAction ()
    {
        myGame.openFile(myLoader);

    }

}
