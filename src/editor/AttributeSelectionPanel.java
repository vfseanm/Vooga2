package editor;

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

import editor.dialogues.DialogueBox;

import attributes.Attribute;

public class AttributeSelectionPanel extends JPanel {
    private HashMap<JCheckBox, Class> attributeMap;
    private HashMap<JCheckBox, Attribute> attributeInstanceMap;
    private List<String> packageNames;
    private List<Attribute> originallyCheckedOff;
   

    public AttributeSelectionPanel(List<String> packagesToSearch, List<Attribute> checkedOff)
    {
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        packageNames = packagesToSearch;
        originallyCheckedOff = checkedOff;
        this.add(makePanel());
    }
    
    public AttributeSelectionPanel(List<String> packagesToSearch)
    {
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
                    JLabel label1 = new JLabel(DialogueBox.getText(c));
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

    private class CheckBoxListener implements ActionListener {
        Class associatedClass;
        JCheckBox box;
        CustomizedInput input;
        

        public CheckBoxListener(JCheckBox b, Class c)
        {
            associatedClass = c;
            box = b;
            input = new CustomizedInput(associatedClass);
        }

        public void actionPerformed(ActionEvent e)
        {
            
            if (box.isSelected())
            {
                input.run();
            }
                Constructor constructor = getAnnotatedConstructor(associatedClass);
                Annotation a = constructor.getAnnotation(editorConstructor.class);
                String[] paramNames = ((editorConstructor) a).parameterNames();
                Class[] paramTypes = constructor.getParameterTypes();
                Object[] argList = null;
                // System.out.println(paramNames.length);
                // System.out.println("got here");
                if (!paramNames[0].equals(""))
                {
                    argList = new Object[paramNames.length];
                    for (int i = 0; i < paramNames.length; i++)
                    {
                        String selectedValue = JOptionPane
                                .showInputDialog("What would you like the "
                                        + paramNames[i] + " to be?");

                        if (paramTypes[i].equals(int.class))
                        {
                            argList[i] = Integer.parseInt(selectedValue);
                        }
                        if (paramTypes[i].equals(String.class))
                        {
                            argList[i] = selectedValue;
                        }
                        if (paramTypes[i].equals(double.class))
                        {
                            argList[i] = Double.parseDouble(selectedValue);
                        }
                        if (paramTypes[i].toString().equals("boolean"))
                        {
                            argList[i] = Boolean.parseBoolean(selectedValue);
                        }
                    }
                }
                Attribute att;
                
                try {

                    att= (Attribute) constructor.newInstance(argList);
                    attributeInstanceMap.put(box, att);
                    
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
