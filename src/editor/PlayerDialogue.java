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

import bonusobjects.Carryable;


import fighter.Fighter;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class PlayerDialogue extends JPanel{



    private EditorController myController;
    protected BufferedImage myImage;
    protected String myImagePath;
    
    protected JTextField myName;
    protected Reflection reflection;

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    @SuppressWarnings("rawtypes")
    
    private HashMap<JCheckBox, Class> checkBoxAttributeMap;
    private HashMap<JCheckBox, Class> checkBoxCarryableMap;
    private HashMap<JCheckBox, Attribute> attributeInstanceMap;
    private HashMap<JCheckBox, Attribute> carryableAttributeMap;

    private String myType;

    @SuppressWarnings("rawtypes")
    public PlayerDialogue(EditorController m)
    {
        myController = m;
        reflection =  new Reflection();
        setLayout(new BorderLayout());
        checkBoxAttributeMap = new HashMap<JCheckBox, Class>();
        checkBoxCarryableMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        carryableAttributeMap = new HashMap<JCheckBox, Attribute>();
        add(makeInputPanel(), BorderLayout.NORTH);
        
    }
    
    private JComponent makeInputPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());

        try
        {
            panel.add(makeSelectionPanel(), BorderLayout.NORTH);
            
        } catch (Exception e)
        {
            System.out.println("Problem with Reflection!");
            e.printStackTrace();
        }
        return panel;
    }

   

    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
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

        return panel;
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
                        e1.printStackTrace();
                    } catch (InstantiationException e1)
                    {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1)
                    {
                        e1.printStackTrace();
                    } catch (InvocationTargetException e1)
                    {
                        e1.printStackTrace();
                    }
                    /*List<Object> attribute = new ArrayList<Object>();
                    attribute.add(constructor);
                    attribute.add(argList);*/
                    
                
            }
    }
    
    private class GoAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Attribute> attributes = new ArrayList<Attribute>();
            ArrayList<Attribute> carryableAttributes = new ArrayList<Attribute>();
            for (JCheckBox box : checkBoxAttributeMap.keySet())
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
            }
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
        //System.out.println(myImagePath);
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



    protected class ImageAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {

            BufferedImage f = getImage();
            myImage = f;

        }
    }


}