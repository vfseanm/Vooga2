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

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class GameDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;

    private Reflection reflection;
    private EditorController myModel;
    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> attributeMap;
    
    private HashMap<JCheckBox, List<Object>> attributeInstanceMap;
    private BufferedImage myImage;
    private String myImagePath;

    public GameDialogue(EditorController m)
    {
        
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, List<Object>>();
        myModel = m;
        reflection = new Reflection();
        setLayout(new BorderLayout());
        
        add(makeInputPanel(), BorderLayout.NORTH);
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
            myModel.setBackground(myImage, myImagePath);
            
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
           // System.out.println(paramNames.length);
            //System.out.println("got here");
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

    @Override
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Framework getFramework() {
        // TODO Auto-generated method stub
        return null;
    }

}