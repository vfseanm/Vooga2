package editor;

import java.io.File;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;

import editor.DialogueBox.ImageAction;
import editor.EnemyDialogueBox.GoAction;
import enemies.Enemy;

import sprite.AnimatedGameSprite;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class EditEnemyDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;

    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> attributeMap;
   // private List<Attribute> myOldAttributes;
    
    private HashMap<JCheckBox, AttributeCreator> attributeInstanceMap;
    private Enemy mySprite;
    private int myX;
    private int myY;
   // private EnemyFramework myFramework;
    private AttributeSelectionPanel attributePanel;

    

    
    @SuppressWarnings("rawtypes")
    public EditEnemyDialogue(EditorController m, Enemy sprite, int x, int y)
    {
        super(m);
        
        myX = x;
        myY = y;
        //setLayout(new BorderLayout());
        //add(makeInputPanel(), BorderLayout.NORTH);
        
        mySprite = sprite;
        myImages = new ArrayList<BufferedImage>();
       
        
        attributeMap = new HashMap<JCheckBox, Class>();
      //  myOldAttributes = new ArrayList<Attribute>();
       // myOldAttributes.addAll(mySprite.getAttributes());
        
        attributeInstanceMap = new HashMap<JCheckBox, AttributeCreator>();
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
        
    }
    
//    public EditEnemyDialogue(EditorController m, Framework framework)
//    {
//        super(m);
//        myFramework = (EnemyFramework) framework;
//        myImage = myFramework.getImages()[0];
//        
//        attributeMap = new HashMap<JCheckBox, Class>();
//        myOldAttributes = new ArrayList<Attribute>();
//        myOldAttributes.addAll(myFramework.getAttributes());
//        
//        attributeInstanceMap = new HashMap<JCheckBox, AttributeCreator>();
//        myModel = m;
//        reflection = new Reflection();
//        setLayout(new BorderLayout());
//        
//    }
//    
    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
        List<Class> classList = new ArrayList<Class>();
        for(Attribute s: mySprite.getAttributes())
        {
            classList.add(s.getClass());
        }
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch, classList);
               
        JLabel label1 = new JLabel("Enemy Name:");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName, BorderLayout.SOUTH);
        
        JLabel groupLabel = new JLabel("Group:");
        panel.add(groupLabel);

      /*  myGroup = new JTextField(10);

        panel.add(myGroup, BorderLayout.SOUTH);*/

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Enemy";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        panel.add(attributePanel);

        return panel;
       /* Reflection reflection = new Reflection();
        List<Class> list = reflection.getInstancesOf("enemies.movement", Attribute.class);
        list.addAll(reflection.getInstancesOf("attributes", Attribute.class));
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
                if(mySprite.hasAttributeByName(c.getName()))
                {
                    box.setSelected(true);
                }
//                else if(mySprite==null)
//                {
//                    for (Attribute a: myFramework.getAttributes())
//                    {
//                        if(a.getClass().equals(c))
//                        {
//                            System.out.println("working");
//                            box.setSelected(true);
//                        }
//                    }
//                }
                
                panel.add(box);
                box.addActionListener(new CheckBoxListener(box, c));
                attributeMap.put(box, c);
            }
        }*/
/*
        JLabel label1 = new JLabel("Enemy Name");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Save Enemy";
        //if(myFramework!=null)
            //buttonPhrase = "Change Enemies";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;*/
    }

    private class GoAction implements ActionListener {
        
        public void actionPerformed(ActionEvent e)
        {
           /* ArrayList<AttributeCreator> attributes = new ArrayList<AttributeCreator>();
            ArrayList<Attribute> oldAttributes = new ArrayList<Attribute>();
            for (JCheckBox box : attributeMap.keySet())
            {
                if (box.isSelected())
                {
                    boolean inOldList = false;
                    for(Attribute a: myOldAttributes)
                    {
                        System.out.println("checking old attribute:" + a);
                        if(attributeMap.get(box).equals(a.getClass())) // if you previously had an instance of this class
                        {
                            if (!attributeInstanceMap.keySet().contains(box)) // if you haven't put in a new instance of this class
                            {
                                System.out.println("adding a previous attribute:" + a);
                                oldAttributes.add(a);
                                inOldList = true;
                            }
                        }
                    }
                    if(!inOldList)
                    {
                    System.out.println("this attribute is selected:" + box);
                    System.out.println(attributeInstanceMap.get(box));
                    attributes.add( attributeInstanceMap.get(box));
                    }
                }
                    
            }*/
            
            ArrayList<Attribute> attributes = new ArrayList<Attribute>();
            
            for(AttributeCreator a: attributePanel.getSelectedAttributes())
            {
                if(a.getArguments()!=null)
                {
                    if(a.getArguments()[0]==null)
                    {
                        
                        for(Attribute currentAttribute: mySprite.getAttributes())
                        {
                            
                            if(currentAttribute.getClass().equals(a.getCreatingClass()))
                            {
                                attributes.add(currentAttribute);
                                break;
                            }
                        }
                        continue;
                    }
                }
                attributes.add(a.createAttribute());
                
            }
            
            
            

            
             BufferedImage[] s = new BufferedImage[myImages.size()];
             if(myImages.size()==0)
             {
                 s = mySprite.getImages();
             }
             else
             {
                for (int x = 0; x<s.length; x++)
                {
                    s[x] = myImages.get(x);
                }
             }
           
            /*ArrayList<String> imagePaths = new ArrayList<String>();
            imagePaths.add(myImagePaths);*/
            //EnemyFramework framework = new EnemyFramework(s, imagePaths, attributes);
            //if(mySprite!=null)
            //{
                myX = (int) mySprite.getOldX();
                myY = (int) mySprite.getOldY();
            //}
            Enemy enemy = new Enemy(s, myX,
                    myY,
                    myImagePaths);
            for(Attribute a: attributes)
            {
                
                
                enemy.addAttribute(a);
            }/*  
            for (Attribute oldAttribute: oldAttributes)
            {
                enemy.addAttribute(oldAttribute);
            }*/
            if(mySprite!=null)
                myController.replaceSprite(mySprite, enemy);
           /* List<Object> parameters = new ArrayList<Object>();
            parameters.add(s);
            parameters.add(myImagePaths);
            parameters.add(attributes);
            //if(myFramework!=null)
                myFramework.updateSprites(parameters);
           */ 
            setVisible(false);
        }
    }
    





}