package editor.dialogues;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import sprite.AnimatedGameSprite;

import editor.EditorController;
import editor.Framework;
import editor.input.DialogueController;
/**
 * Dialogue box that can be extended to create various types of sprites and configure the game
 * @author Sean
 *
 */
@SuppressWarnings("serial")
public abstract class DialogueBox extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    protected EditorController editorController;
    protected List<String> myImagePaths;
    protected DialogueController dialogueController;
    /**
     * 
     * @param m the editor controller 
     */
    public DialogueBox(EditorController m) {
        dialogueController = new DialogueController(this);
        myImagePaths = new ArrayList<String>();
        editorController = m;
    }
    /**
     * make the panel that allows you to select the things necessary to create the object 
     * @return the selection panel
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public abstract JComponent makeSelectionPanel()
            throws ClassNotFoundException, IOException;

    /**
     * action performed when the final button is clicked.
     */
    protected abstract void BoxCompletedAction();
    
    /**
     *  Clones the dialogue box 
     */
    public abstract DialogueBox clone();

    @SuppressWarnings("rawtypes")
	public static String getClassName(Class c) {
        String[] classPath = c.getName().split("\\.");
        return classPath[classPath.length - 1];
    }
    /**
     * sets the click placement in the dialogue controller
     * @param x x-value for where user has clicked
     * @param y y-value for where user has clicked
     */
    public void setClick(int x, int y) {
        dialogueController.setXY(x, y);
    }
    /**
     * sets the sprite that has been right clicked on
     * @param sprite the sprite that has been right clicked 
     */
    public void setRightClickSprite(AnimatedGameSprite sprite) {
        dialogueController.setRightClickSprite(sprite);
    }
    /**
     * sets the sprite that has been left clicked on
     * @param sprite the sprite that has been left clicked
     */
    public void setLeftClickSprite(AnimatedGameSprite sprite) {
        dialogueController.setLeftClickSprite(sprite);
    }
    /**
     * sets the framework of the sprite that has been left clicked on
     * @param f framework of the sprite that has been left clicked
     */
    public void setLeftClickFramework(Framework f) {
        dialogueController.setLeftClickFramework(f);
    }
    /**
     * sets the framework of the sprite that has been right clicked on
     * @param f framework of the sprite that has been right clicked on
     */
    public void setRightClickFramework(Framework f) {
        dialogueController.setRightClickFramework(f);
    }
    /**
     * opens file chooser and allows user to choose image. Then saves to image path list
     */
    public void getImage() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        File file = null;
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        myImagePaths.add("resources/"+file.getName());
      
   

    }
    /**
     * makes the input panel which will contain the selection panel of the concrete class
     * @return the input panel
     */
    protected JComponent makeInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        try {
            panel.add(makeSelectionPanel(), BorderLayout.NORTH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return panel;
    }
    /**
     * action associated with clicking choose image
     * @author Sean
     *
     */
    protected class ImageAction implements ActionListener {
        public ImageAction() {
        }
        /**
         * gets image when the select image button is pressed
         */
        public void actionPerformed(ActionEvent e) {
            getImage();

        }
    }
    /**
     * Action performed when go button is pressed
     * @author Sean
     *
     */
    protected class GoAction implements ActionListener {

        public GoAction() {
        }
        /**
         * calls the method in the concrete class that should be called when all information has been specified
         */
        public void actionPerformed(ActionEvent e) {
            BoxCompletedAction();
        }
    }

}