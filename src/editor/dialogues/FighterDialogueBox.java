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

import bonusobjects.Carryable;


import editor.AttributeCreator;
import editor.AttributeSelectionPanel;
import editor.EditorController;
import fighter.Fighter;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class FighterDialogueBox extends DialogueBox{

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    AttributeSelectionPanel attributePanel;
    AttributeSelectionPanel carryablePanel;

    private String myType;

    @SuppressWarnings("rawtypes")
    public FighterDialogueBox(EditorController m)
    {
       
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }
    
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
        JLabel title2 = new JLabel("Carryable attributes for the Fighter to Have:");
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
        goButton.addActionListener(new GoAction());
        subPanel.add(goButton);
        

        panel.add(subPanel, BorderLayout.PAGE_END);

        return panel;

    }

    @Override
    public DialogueBox clone() {
        return new FighterDialogueBox(myController);
    }

    @Override
    protected void BoxCompletedAction() {
        ArrayList<Attribute> attributes = attributePanel.getSelectedAttributes();
        
        ArrayList<Attribute> carryableAttributes = carryablePanel.getSelectedAttributes();

        ArrayList<String> imagePaths = new ArrayList<String>();
        Fighter fighter = Fighter.getInstance();
        fighter.setLocation(50, 50);
        fighter.setImageNames(myImagePaths);
        
        for(Attribute attribute: attributes)
        {
            System.out.println("adding attribute to fighter");
            fighter.addAttribute(attribute);
        }
            fighter.addCarryableAttributes(carryableAttributes);
        myController.setFighter(fighter);
        setVisible(false);
        
    }





}
