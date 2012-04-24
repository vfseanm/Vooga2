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
import editor.input.DialogueController;


@SuppressWarnings("serial")
public abstract class DialogueBox extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    protected EditorController myController;
    protected List<String> myImagePaths;
    protected DialogueController controller;
    
    
    public DialogueBox(EditorController m)
    {
        controller = new DialogueController(this);
        myImagePaths = new ArrayList<String>();
        myController = m;        
        
    }
    
    public abstract DialogueBox clone();
    
    public static String getText(Class c)
    {
        String[] classPath = c.getName().split("\\."); 
        return classPath[classPath.length-1];
    }

    
    public void setClick(int x, int y)
    {
        controller.setXY(x, y);
    }
    public void setRightClickSprite(AnimatedGameSprite sprite)
    {
        controller.setRightClickSprite(sprite);
    }
    public void setLeftClickSprite(AnimatedGameSprite sprite)
    {
        controller.setLeftClickSprite(sprite);
    }
    
    
    public abstract JComponent makeSelectionPanel() throws ClassNotFoundException, IOException;

    public void getImage()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        File file = null;
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fc.getSelectedFile();
        }
        try
        {
            myImagePaths.add(file.getCanonicalPath());
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }


    }

    protected JComponent makeInputPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());

        try
        {
            panel.add(makeSelectionPanel(), BorderLayout.NORTH);
            
        } catch (Exception e)
        {
            System.out.println("Problem with Reflection!");
            e.printStackTrace();
        }
        return panel;
    }
    
    protected abstract void BoxCompletedAction();

    protected class ImageAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            getImage();
            
        }
    }
    
class GoAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            BoxCompletedAction();
        }
    }
    
}