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

import carryables.Carryable;
import editor.ButtonDialogueBox.GoAction;
import editor.DialogueBox.ImageAction;
import fighter.Fighter;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class FighterDialogueBox extends DialogueBox{





    


    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

/* @SuppressWarnings("rawtypes")
private HashMap<JCheckBox, Class> checkBoxAttributeMap;
private HashMap<JCheckBox, Class> checkBoxCarryableMap;
private HashMap<JCheckBox, AttributeCreator> attributeInstanceMap;
private HashMap<JCheckBox, AttributeCreator> carryableAttributeMap;*/
    AttributeSelectionPanel attributePanel;
    AttributeSelectionPanel carryablePanel;

    private String myType;

    @SuppressWarnings("rawtypes")
    public FighterDialogueBox(EditorController m)
    {
       
        super(m);
    }
    
    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {


        ArrayList<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("attributes");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(800,325));
        attributePanel = new AttributeSelectionPanel(packagesToSearch);
        JPanel panel2 = new JPanel();
        JLabel title1 = new JLabel("Attributes for the Fighter to have:");
        panel2.add(title1);
        panel2.add(attributePanel);
        panel.add(panel2, BorderLayout.PAGE_START);
        JLabel title2 = new JLabel("Carryable attributes for the Power-Up to Give:");
        carryablePanel = new AttributeSelectionPanel(packagesToSearch);
        JPanel panel3 = new JPanel();
        panel3.add(title2);
        panel3.add(carryablePanel);
        panel.add(panel3, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(600, 100));

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        subPanel.add(imageButton);
                
        JButton goButton = new JButton("Create Fighter");
        goButton.addActionListener(new FighterAction());
        subPanel.add(goButton);
        

        panel.add(subPanel, BorderLayout.PAGE_END);

        return panel;

    }

    private class FighterAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<AttributeCreator> attributes = attributePanel.getSelectedAttributes();
            ArrayList<AttributeCreator> carryableAttributes = carryablePanel.getSelectedAttributes();

            BufferedImage[] s = new BufferedImage[myImages.size()];
            for (int x = 0; x<s.length; x++)
            {
                s[x] = myImages.get(x);
            }
            ArrayList<String> imagePaths = new ArrayList<String>();
            Fighter fighter = new Fighter(s, 50, 50, myImagePaths);
            
            for(AttributeCreator attribute: attributes)
            {
            fighter.addAttribute(attribute.createAttribute());
            }
            for(AttributeCreator attribute: carryableAttributes)
            {
            //fighter.addCarryableAttribute(attribute.createAttribute());
            }
            myController.addSprite(new Fighter(s, 50, 50, myImagePaths));
            setVisible(false);
        }
    }





}