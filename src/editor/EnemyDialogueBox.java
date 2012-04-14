package editor;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Dimension;

import javax.swing.*;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class EnemyDialogueBox extends ButtonDialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> attributeMap;
    
    private HashMap<JCheckBox, List<Object>> attributeInstanceMap;


    @SuppressWarnings("rawtypes")
    public EnemyDialogueBox(EditorController m)
    {
        super(m);
        
    }


    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, List<Object>>();
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

        String buttonPhrase = "Create Enemy";
        		
        JButton goButton = new JButton(buttonPhrase);
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
            if(box.isSelected())
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
             
                   // Attribute att = (Attribute) constructor.newInstance(argList);
                    List<Object> attribute = new ArrayList<Object>();
                    attribute.add(constructor);
                    attribute.add(argList);
                    attributeInstanceMap.put(box, attribute);
                }
                
            }
    }
    
    public Framework getFramework()
    {
        ArrayList<List<Object>> attributes = new ArrayList<List<Object>>();
        for (JCheckBox box : attributeMap.keySet())
        {
            if (box.isSelected())
            {
                attributes.add( attributeInstanceMap.get(box));
            }
                
        }
        BufferedImage[] s = new BufferedImage[1];
        s[0] = myImage;
        ArrayList<String> imagePaths = new ArrayList<String>();
        imagePaths.add(myImagePath);
        EnemyFramework framework = new EnemyFramework(s, imagePaths, attributes);
        System.out.println("framework's attributes" + attributes);
        return framework;
    }



}