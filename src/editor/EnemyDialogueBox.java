package editor;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class EnemyDialogueBox extends ButtonDialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    
    AttributeSelectionPanel attributePanel;


    /*private HashMap<JCheckBox, Class> attributeMap;
    
    private HashMap<JCheckBox, List<Object>> attributeInstanceMap;
*/

    @SuppressWarnings("rawtypes")
    public EnemyDialogueBox(EditorController m)
    {
        super(m);
        
    }


    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
       
        JLabel label1 = new JLabel("Enemy Name");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Enemy";
        		
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        panel.add(attributePanel);

        return panel;
    }


    
  
    
    public Framework getFramework()
    {
        
        ArrayList<AttributeCreator> attributes = attributePanel.getSelectedAttributes();
        BufferedImage[] s = new BufferedImage[1];
        s[0] = myImage;
        ArrayList<String> imagePaths = new ArrayList<String>();
        imagePaths.add(myImagePath);
        EnemyFramework framework = new EnemyFramework(s, imagePaths, attributes);
        System.out.println("framework's attributes" + attributes);
        return framework;
    }



}