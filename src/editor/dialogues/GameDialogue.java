package editor.dialogues;

import java.io.File;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;

import platforms.platformtypes.DecoratedPlatform;
import sidescrolling.DecoratedSidescroller;
import sidescrolling.border.BorderSidescroller;
import sidescrolling.forced.ForcedSidescroller;
import sidescrolling.shift.ShiftSidescroller;

import editor.EditorController;
import editor.Reflection;


@SuppressWarnings("serial")
public class GameDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    private HashMap<JCheckBox, Class> classMap;


    public GameDialogue(EditorController m)
    {
        
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
       
    }


    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        
        classMap = new HashMap<JCheckBox, Class>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
        // ArrayList<Class> list = reflection.getBehaviors();
        Reflection reflection = new Reflection();
        for (Class c : reflection.getInstancesOf("sidescrolling.border", BorderSidescroller.class))
        {
                JLabel label1 = new JLabel(getText(c));
                panel.add(label1);
                JCheckBox box = new JCheckBox();
                panel.add(box);
                classMap.put(box, c);
        }
        for (Class c : reflection.getInstancesOf("sidescrolling.forced", ForcedSidescroller.class))
        {
            JLabel label1 = new JLabel(getText(c));
                panel.add(label1);
                JCheckBox box = new JCheckBox();
                panel.add(box);
                classMap.put(box, c);
        }
        for (Class c : reflection.getInstancesOf("sidescrolling.shift", ShiftSidescroller.class))
        {
            
            JLabel label1 = new JLabel(getText(c));
                panel.add(label1);
                JCheckBox box = new JCheckBox();
                panel.add(box);
                classMap.put(box, c);
        }
        
       

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Configure Game";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }

    
    public DialogueBox clone() {
        return new GameDialogue(myController);
    }


    protected void BoxCompletedAction() {
        BufferedImage image;
        try
        {
            image = ImageIO.read(new File(myImagePaths.get(myImagePaths.size()-1)));
            System.out.println(myImagePaths.get(0));
            myController.setBackground(image, myImagePaths.get(0));
        } catch (IOException exc)
        {
            System.out.println("There has been a problem importing your image");
        }
      
        
        setVisible(false);
    }
        
   }
 