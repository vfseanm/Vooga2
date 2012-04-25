package editor.dialogues;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import editor.InputListener;
import editor.Reflection;
import editor.editorConstructor;
import editor.input.CustomInputManager;
import editor.input.DialogueController;

import attributes.Attribute;

public class AttributeSelectionPanel extends JPanel {
    private HashMap<JCheckBox, Class> attributeMap;
    private HashMap<JCheckBox, Attribute> attributeInstanceMap;
    private List<String> packageNames;
    private List<Attribute> originallyCheckedOff;
    private DialogueController myController;
   

    public AttributeSelectionPanel(List<String> packagesToSearch, List<Attribute> checkedOff, DialogueController controller)
    {
        myController = controller;
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        packageNames = packagesToSearch;
        originallyCheckedOff = checkedOff;
        this.add(makePanel());
    }
    
    public AttributeSelectionPanel(List<String> packagesToSearch, DialogueController controller)
    {
        myController = controller;
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        packageNames = packagesToSearch;
        originallyCheckedOff = new ArrayList<Attribute>();
        this.add(makePanel());
    }
    
    
    public JPanel makePanel()
    {

        Reflection reflection = new Reflection();
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 100));

        try
        {
            List<Class> listOfClasses = new ArrayList<Class>();
            for(String s: packageNames)
            {
                listOfClasses.addAll(reflection.getInstancesOf(s, Attribute.class));
            }

            for (Class c : listOfClasses)
            {
                boolean isAnnotated = false;
                for (Constructor constructor : c.getConstructors())
                {
                    if (constructor
                            .isAnnotationPresent(editorConstructor.class))
                    {
                        isAnnotated = true;
                    }
                }
                if (isAnnotated)
                {
                    JLabel label1 = new JLabel(DialogueBox.getClassName(c));
                    panel.add(label1);
                    JCheckBox box = new JCheckBox();
                    panel.add(box);
                    box.addActionListener(new CheckBoxListener(box, c));
                    attributeMap.put(box, c);
                    for(Attribute a: originallyCheckedOff)
                    {
                       if(a.getClass().equals(c))
                       {
                            box.setSelected(true);
                            attributeInstanceMap.put(box, a);                        
                       }
                    }
                }
            }
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return panel;
    }

    public class CheckBoxListener implements ActionListener, InputListener {
        Class associatedClass;
        JCheckBox box;
        CustomInputManager input;

        public CheckBoxListener(JCheckBox b, Class c)
        {
            associatedClass = c;
            box = b;
        }
        public void setObject(Object attribute)
        {
            Attribute a = (Attribute) attribute;
            attributeInstanceMap.put(box, a);
        }

        public void actionPerformed(ActionEvent e)
        {
            
            if (box.isSelected())
            {
                myController.promptForInput(associatedClass, this);
            }
        }
    }
    
    public  ArrayList<Attribute> getSelectedAttributes()
    {
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        for (JCheckBox box : attributeMap.keySet())
        {
            if (box.isSelected())
            {
                attributes.add( attributeInstanceMap.get(box));
            }
                
        }
        return attributes;
    }
    
    public Constructor getAnnotatedConstructor(Class c)
    {
        Constructor[] constructors = c.getConstructors();
        Constructor constructor = null;
        for (Constructor co : constructors)
        {
            if (co.isAnnotationPresent(editorConstructor.class))
            {
                constructor = co;
            }
        }
        if (constructor == null)
        {
            throw new RuntimeException();
        }
        return constructor;
    }
    
    

}
