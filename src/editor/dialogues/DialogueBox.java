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

@SuppressWarnings("serial")
public abstract class DialogueBox extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    protected EditorController editorController;
    protected List<String> myImagePaths;
    protected DialogueController dialogueController;

    public DialogueBox(EditorController m) {
        dialogueController = new DialogueController(this);
        myImagePaths = new ArrayList<String>();
        editorController = m;
    }

    public abstract JComponent makeSelectionPanel()
            throws ClassNotFoundException, IOException;

    protected abstract void BoxCompletedAction();

    public abstract DialogueBox clone();

    public static String getClassName(Class c) {
        String[] classPath = c.getName().split("\\.");
        return classPath[classPath.length - 1];
    }

    public void setClick(int x, int y) {
        dialogueController.setXY(x, y);
    }

    public void setRightClickSprite(AnimatedGameSprite sprite) {
        dialogueController.setRightClickSprite(sprite);
    }

    public void setLeftClickSprite(AnimatedGameSprite sprite) {
        dialogueController.setLeftClickSprite(sprite);
    }

    public void setLeftClickFramework(Framework f) {
        dialogueController.setLeftClickFramework(f);
    }

    public void setRightClickFramework(Framework f) {
        dialogueController.setRightClickFramework(f);
    }

    public void getImage() {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        File file = null;
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        myImagePaths.add("resources/"+file.getName());
      
   

    }

    protected JComponent makeInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        try {
            panel.add(makeSelectionPanel(), BorderLayout.NORTH);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return panel;
    }

    protected class ImageAction implements ActionListener {
        public ImageAction() {
        }

        public void actionPerformed(ActionEvent e) {
            getImage();

        }
    }

    protected class GoAction implements ActionListener {

        public GoAction() {
        }

        public void actionPerformed(ActionEvent e) {
            BoxCompletedAction();
        }
    }

}