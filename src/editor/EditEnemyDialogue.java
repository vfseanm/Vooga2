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

import enemies.Enemy;

import sprite.AnimatedGameSprite;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class EditEnemyDialogue extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;

    private Reflection reflection;
    private EditorController myModel;
    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> attributeMap;
    private List<Attribute> myOldAttributes;
    
    private HashMap<JCheckBox, List<Object>> attributeInstanceMap;
    private BufferedImage myImage;
    private String myImagePath;
    private String myType;
    private Enemy mySprite;
    private int myX;
    private int myY;
    
    @SuppressWarnings("rawtypes")
    public EditEnemyDialogue(EditorController m, Enemy sprite, int x, int y)
    {
        myX = x;
        myY = y;
        mySprite = sprite;
        myImage = mySprite.getImage();
        
        attributeMap = new HashMap<JCheckBox, Class>();
        myOldAttributes = new ArrayList<Attribute>();
        myOldAttributes.addAll(mySprite.getAttributes());
        
        attributeInstanceMap = new HashMap<JCheckBox, List<Object>>();
        myModel = m;
        reflection = new Reflection();
        setLayout(new BorderLayout());
        
        add(makeInputPanel(), BorderLayout.NORTH);
    }

    public BufferedImage getImage()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        File file = null;
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fc.getSelectedFile();
        }
        myImagePath = null;
        try
        {
            myImagePath = file.getCanonicalPath();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
        System.out.println(myImagePath);
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(myImagePath));
        } catch (IOException e)
        {
            System.out.println("There has been a problem importing your image");
            // throw something!
        }
        return img;

    }

    private JComponent makeInputPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());

        try
        {
            panel.add(makeDestinationPanel(), BorderLayout.NORTH);
        } catch (Exception e)
        {
            System.out.println("Problem with Reflection!");
            e.printStackTrace();
        }
        return panel;
    }

    @SuppressWarnings("rawtypes")
    private JComponent makeDestinationPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
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
                panel.add(box);
                box.addActionListener(new CheckBoxListener(box, c));
                attributeMap.put(box, c);
            }
        }

        JLabel label1 = new JLabel("Enemy Name");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Save Enemy";
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }

    private class GoAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<List<Object>> attributes = new ArrayList<List<Object>>();
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
                    
            }
            BufferedImage[] s = new BufferedImage[1];
            s[0] = myImage;
            ArrayList<String> imagePaths = new ArrayList<String>();
            imagePaths.add(myImagePath);
            //EnemyFramework framework = new EnemyFramework(s, imagePaths, attributes);
            
            Enemy enemy = new Enemy(s, myX,
                    myY - s[0].getHeight(),
                    imagePaths);
            for(List<Object> list: attributes)
            {
                System.out.println("list:" + list);
                Constructor c = (Constructor) list.get(0);
                Object[] parameterList = (Object[]) list.get(1);
                Attribute attribute = null;
                try {
                    attribute = (Attribute) c.newInstance(parameterList);
                } catch (IllegalArgumentException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                enemy.addAttribute(attribute);
            }  
            for (Attribute oldAttribute: oldAttributes)
            {
                enemy.addAttribute(oldAttribute);
            }
            myModel.replaceSprite(mySprite, enemy);
            
            setVisible(false);
        }
    }
    
    
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
            Object[] argList = null;
            if(!paramNames[0].equals(""))
            {
                argList = new Object[paramNames.length];
                for(int i=0; i< paramNames.length; i++)
                {
                    String selectedValue = JOptionPane
                        .showInputDialog("What would you like the "+ paramNames[i]+ " to be?");
                    argList[i]=Integer.parseInt(selectedValue);
                }
            }
                   // Attribute att = (Attribute) constructor.newInstance(argList);
                    List<Object> attribute = new ArrayList<Object>();
                    attribute.add(constructor);
                    attribute.add(argList);
                    System.out.println("ADDING AN ATTRIBUTE" + attribute);
                    attributeInstanceMap.put(box, attribute);
                
            }
    }

    private class ImageAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {

            BufferedImage f = getImage();
            myImage = f;

        }
    }

}