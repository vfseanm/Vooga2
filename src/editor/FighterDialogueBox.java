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

/*    @SuppressWarnings("rawtypes")
    
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

        /*JLabel label1 = new JLabel("Power-Up Name");
        subPanel.add(label1);

        myName = new JTextField(10);

        subPanel.add(myName);
*/
        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        subPanel.add(imageButton);
                
        JButton goButton = new JButton("Create Fighter");
        goButton.addActionListener(new FighterAction());
        subPanel.add(goButton);
        

        panel.add(subPanel, BorderLayout.PAGE_END);

        return panel;
        /*checkBoxAttributeMap = new HashMap<JCheckBox, Class>();
        checkBoxCarryableMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        carryableAttributeMap = new HashMap<JCheckBox, Attribute>();*/
        
        /*JPanel panel = new JPanel();
        JLabel label = new JLabel("Attribute Choices: ");
        panel.add(label);
        
        panel.setPreferredSize(new Dimension(600,800));
        List<Class> list = reflection.getInstancesOf("attributes", Attribute.class);
        for (Class c : list)
        {
            boolean isAnnotated = false;
            for(Constructor constructor : c.getConstructors())
            {
                if(constructor.isAnnotationPresent(editorConstructor.class))
                {
                    isAnnotated = true;
                }
            }
            if(isAnnotated)
            {
                JLabel label1 = new JLabel(c.getName());
                panel.add(label1);
                JCheckBox box = new JCheckBox();
                panel.add(box);
                box.addActionListener(new CheckBoxListener(box, c));
                checkBoxAttributeMap.put(box, c);
            }
        }
        
        JLabel label2 = new JLabel("Carryable Choices: ");
        panel.add(label2);
        

        for (Class c : list)
        {
            boolean isAnnotated = false;
            for(Constructor constructor : c.getConstructors())
            {
                if(constructor.isAnnotationPresent(editorConstructor.class))
                {
                    isAnnotated = true;
                }
            }
            if(isAnnotated)
            {
                JLabel label1 = new JLabel(c.getName());
                panel.add(label1);
                JCheckBox box2 = new JCheckBox();
                panel.add(box2);
                box2.addActionListener(new CheckBoxListener(box2, c));
                checkBoxCarryableMap. put(box2, c);
            }
        }

        
        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        JButton goButton = new JButton("Create Player");
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;*/
    }

   /* 
    private class CheckBoxListener implements ActionListener {
        Class associatedClass;
        JCheckBox box;
        
        public CheckBoxListener(JCheckBox b, Class c)
        {
            associatedClass= c;
            box = b;
        }
        public void actionPerformed(ActionEvent e)
        {
            Constructor[] constructors = associatedClass.getConstructors();
            Constructor constructor = null;
            for(Constructor c: constructors)
            {
                if(c.isAnnotationPresent(editorConstructor.class))
                {
                    constructor = c;
                }
            }
            if(constructor == null)
            {
                throw new RuntimeException();
            }
            
            Annotation a = constructor.getAnnotation(editorConstructor.class);
            String[] paramNames = ((editorConstructor) a).parameterNames();
            Class[] paramTypes =constructor.getParameterTypes();
            Object[] argList = null;
            //System.out.println(paramNames.length);
            //System.out.println("got here");
            if(!paramNames[0].equals(""))
            {
                argList = new Object[paramNames.length];
                for(int i=0; i< paramNames.length; i++)
                {
                    String selectedValue = JOptionPane
                        .showInputDialog("What would you like the "+ paramNames[i]+ " to be?");
                    

                    if(paramTypes[i].equals(int.class))
                    {
                        argList[i]=Integer.parseInt(selectedValue);
                    }
                    if(paramTypes[i].equals(String.class))
                    {
                        argList[i] = selectedValue;
                    }
                    if(paramTypes[i].equals(double.class))
                    {
                        argList[i] = Double.parseDouble(selectedValue);
                    }
                    if(paramTypes[i].toString().equals("boolean"))
                    {
                        argList[i] = Boolean.parseBoolean(selectedValue);
                    }
                }
            }
             
                    
                    try
                    {
                        Attribute att;
                        att = (Attribute) constructor.newInstance(argList);
                        if(checkBoxAttributeMap.keySet().contains(box))
                        {
                            attributeInstanceMap.put(box, att);
                        }
                        if(checkBoxCarryableMap.keySet().contains(box))
                        {
                            carryableAttributeMap.put(box, att);
                        }
                    } catch (IllegalArgumentException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (InstantiationException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (InvocationTargetException e1)
                    {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    List<Object> attribute = new ArrayList<Object>();
                    attribute.add(constructor);
                    attribute.add(argList);
                    
                
            }
    }
*/    
    private class FighterAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<AttributeCreator> attributes = attributePanel.getSelectedAttributes();
            ArrayList<AttributeCreator> carryableAttributes = carryablePanel.getSelectedAttributes();
            /*for (JCheckBox box : checkBoxAttributeMap.keySet())
            {
                if (box.isSelected())
                {
                    attributes.add( attributeInstanceMap.get(box));
                }
            }
            for(JCheckBox box: checkBoxCarryableMap.keySet())
            {
                if(box.isSelected())
                {
                    carryableAttributes.add(carryableAttributeMap.get(box));
                }
            }*/
            BufferedImage[] s = new BufferedImage[1];
            s[0] = myImage;
            ArrayList<String> imagePaths = new ArrayList<String>();
            imagePaths.add(myImagePath);
            //ArrayList<> carryables = new ArrayList<Carryable>();
            //PlayerFramework framework = new PlayerFramework(s, imagePaths, attributes, carryables);
            myController.addSprite(new Fighter(s, 0, 0, imagePaths));
            setVisible(false);
        }
    }
   
/*class FighterAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("get name "+ myName.getText());
            Fighter fighter = null; // this should create a new fighter based on input
            myController.setFighter(fighter); 
            setVisible(false);
        }
    }*/



}
