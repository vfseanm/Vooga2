package editor.buttons;


import com.golden.gamedev.gui.TButton;

import editor.EditorView;
import editor.file.LevelWriter;

/**
 * Button that saves level file when pressed
 * @author Becky
 *
 */
public class SaveButton extends TButton
{

    EditorView myGame;
    LevelWriter myWriter;

    /**
     * 
     * @param name name of button
     * @param x x location of button
     * @param y y location of button
     * @param width width of button
     * @param height height of button
     * @param view level editor view
     * @param writer
     */
    public SaveButton (String name,
                       int x,
                       int y,
                       int width,
                       int height,
                       EditorView view,
                       LevelWriter writer)
    {
        
        super(name, x, y, width, height);
        myGame = view;
        myWriter = writer;
    }
    /**
     * saving file when button is pressed using the writer
     */
    public void doAction ()
    {
        myGame.saveFile(myWriter);
    }

}
