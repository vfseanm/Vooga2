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

import editor.AttributeCreator;
import editor.AttributeSelectionPanel;
import editor.EditorController;
import editor.frameworks.Framework;
import editor.frameworks.PowerupFramework;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class PowerupDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    
    private AttributeSelectionPanel powerupAttributePanel;
    private AttributeSelectionPanel attributesToGivePanel;
    private JTextField myName;
    private JTextField myGroup;

    @SuppressWarnings("rawtypes")
    public PowerupDialogueBox(EditorController m)
    {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
        
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
        powerupAttributePanel = new AttributeSelectionPanel(packagesToSearch, new ArrayList<Class>());
        JPanel panel2 = new JPanel();
        JLabel title1 = new JLabel("Attributes for the Power-Up to have:");
        panel2.add(title1);
        panel2.add(powerupAttributePanel);
        panel.add(panel2, BorderLayout.PAGE_START);
        JLabel title2 = new JLabel("Attributes for the Power-Up to Give:");
        attributesToGivePanel = new AttributeSelectionPanel(packagesToSearch, new ArrayList<Class>());
        JPanel panel3 = new JPanel();
        panel3.add(title2);
        panel3.add(attributesToGivePanel);
        panel.add(panel3, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(600, 100));

        JLabel label1 = new JLabel("Power-Up Name");
        subPanel.add(label1);

        myName = new JTextField(10);

        subPanel.add(myName);
        
        JLabel groupLabel = new JLabel("Group:");
        subPanel.add(groupLabel);

        myGroup = new JTextField(10);
        subPanel.add(myGroup, BorderLayout.SOUTH);


        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        subPanel.add(imageButton);
                
        JButton goButton = new JButton("Create Power-Up");
        goButton.addActionListener(new GoAction());
        subPanel.add(goButton);
        

        panel.add(subPanel, BorderLayout.PAGE_END);

        return panel;
    }
    
    
    public Framework getFramework() {

            ArrayList<AttributeCreator> attributes = powerupAttributePanel.getSelectedAttributes();
            ArrayList<AttributeCreator> attributesToGive = attributesToGivePanel.getSelectedAttributes();
            BufferedImage[] s = new BufferedImage[myImages.size()];
            for (int x = 0; x<s.length; x++)
            {
                s[x] = myImages.get(x);
            }
            ArrayList<String> imagePaths = new ArrayList<String>();
            PowerupFramework framework = new PowerupFramework(s, myImagePaths, attributes, attributesToGive, myGroup.getText());
            return framework;

        }
class GoAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            Framework framework = getFramework();
            System.out.println("framework "+framework);
            myController.addButton(myName.getText(), framework);
            setVisible(false);
        }
    }
    
    



}