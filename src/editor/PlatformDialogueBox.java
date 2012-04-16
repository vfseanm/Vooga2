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

import platforms.AbstractPlatform;
import platforms.DecoratedPlatform;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class PlatformDialogueBox extends ButtonDialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";



    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> classMap;
    
    

    @SuppressWarnings("rawtypes")
    public PlatformDialogueBox(EditorController m)
    {
        super(m);
        
    }
    
    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        classMap = new HashMap<JCheckBox, Class>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
        // ArrayList<Class> list = reflection.getBehaviors();
        for (Class c : reflection.getInstancesOf("platforms", DecoratedPlatform.class))
        {

            
                JLabel label1 = new JLabel(c.getName());
                panel.add(label1);
                JCheckBox box = new JCheckBox();
                panel.add(box);
                classMap.put(box, c);
        }

        JLabel label1 = new JLabel("Platform Name");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Platform";
        
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }

    public Framework getFramework()
    {
        
       List<Class> platformTypes = new ArrayList<Class>();
        for (JCheckBox box : classMap.keySet())
        {
            if (box.isSelected())
            {
                platformTypes.add(classMap.get(box));
            }
                
        }
        BufferedImage[] s = new BufferedImage[1];
        s[0] = myImage;
        List<String> imagePaths = new ArrayList<String>();
        imagePaths.add(myImagePath);
        
        PlatformFramework framework = new PlatformFramework(s, imagePaths, platformTypes);
        return framework;
        
    }
   


}