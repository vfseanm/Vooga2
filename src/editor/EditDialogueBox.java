package editor;

import java.io.File;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;

import editor.frameworks.Framework;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public abstract class EditDialogueBox extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    protected EditorController myController;
    protected ArrayList<BufferedImage> myImages;
    protected ArrayList<String> myImagePaths;
    
    protected JTextField myName;
    protected JTextField myGroup;
    protected Reflection reflection;
    protected AnimatedGameSprite mySprite;
    
    @SuppressWarnings("rawtypes")
    public EditDialogueBox(EditorController m, Sprite s)
    {
        myImages = new ArrayList<BufferedImage>();
        myImagePaths = new ArrayList<String>();
        myController = m;
        reflection =  new Reflection();
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
        
        //System.out.println("got here");
    }
    public EditDialogueBox(EditorController m, Framework, framework)
    {
        myImages = new ArrayList<BufferedImage>();
        myImagePaths = new ArrayList<String>();
        myController = m;
        reflection =  new Reflection();
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
        
        //System.out.println("got here");
    }
    public abstract JComponent makeSelectionPanel() throws ClassNotFoundException, IOException;

    public BufferedImage getImage()
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
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(myImagePaths.get(myImagePaths.size()-1)));
        } catch (IOException e)
        {
            System.out.println("There has been a problem importing your image");
        }
        return img;

    }

    private JComponent makeInputPanel()
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

    protected class ImageAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            BufferedImage f = getImage();
            myImages.add(f);

        }
    }
    
}