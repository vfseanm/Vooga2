package editor.dialogues;

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

import editor.EditorController;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class GameDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    public GameDialogue(EditorController m)
    {
        
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
       
    }


    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
        
       

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Configure Game";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }

    private class GoAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            //System.out.println(myImages.get(0));
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
 



}