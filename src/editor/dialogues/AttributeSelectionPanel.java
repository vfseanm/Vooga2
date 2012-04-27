package editor.dialogues;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import editor.InputListener;
import editor.ReflectionUtil;
import editor.editorConstructor;
import editor.input.CustomInputManager;
import editor.input.DialogueController;

import attributes.Attribute;
/**
 * Panel component to be used in dialogue boxes that allows user to select attributes,
 * specify their components. The list of attributes created can then be retrieved.
 * @author Becky and Sean
 *
 */
public class AttributeSelectionPanel extends JPanel {
 
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private HashMap<JCheckBox, Class> attributeMap;
    private HashMap<JCheckBox, Attribute> attributeInstanceMap;
    private List<String> packageNames;
    private List<Attribute> originallyCheckedOff;
    private DialogueController myController;
   
    /**
     * used to construct AttributeSelectionPanel if some Attributes should be checked off
     * @param packagesToSearch list of the packages to search in for attributes
     * @param checkedOff list of attributes that should already be checked off in the panel
     * @param controller the DialogueController associated with the panel
     */
    @SuppressWarnings("rawtypes")
	public AttributeSelectionPanel(List<String> packagesToSearch, List<Attribute> checkedOff, DialogueController controller)
    {
        myController = controller;
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        packageNames = packagesToSearch;
        originallyCheckedOff = checkedOff;
        this.add(makePanel());
    }
    /**
     * used to construct AttributeSelectionPanel if attributes should not be checked off
     * @param packagesToSearch list of the packages to search in for attributes
     * @param controller the DialogueController associated with the panel
     */
    @SuppressWarnings("rawtypes")
	public AttributeSelectionPanel(List<String> packagesToSearch, DialogueController controller)
    {
        myController = controller;
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        packageNames = packagesToSearch;
        originallyCheckedOff = new ArrayList<Attribute>();
        this.add(makePanel());
    }
    
    /**
     * creates appropriate attribute checkboxes on a panel
     * @return panel with attribute checkboxes
     */
    @SuppressWarnings("rawtypes")
	public JPanel makePanel()
    {
        attributeMap = new HashMap<JCheckBox, Class>();
        attributeInstanceMap = new HashMap<JCheckBox, Attribute>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 100));

        try
        {
            List<Class> listOfClasses = new ArrayList<Class>();
            for(String packageName: packageNames)
            {
                listOfClasses.addAll(ReflectionUtil.getInstancesOf(packageName, Attribute.class));
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
                    JCheckBox box = new JCheckBox();
                    panel.add(box);
                    box.addActionListener(new CheckBoxListener(box, c));
                    attributeMap.put(box, c);
                    JLabel label1 = new JLabel(DialogueBox.getClassName(c));
                    panel.add(label1);
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
    /**
     * Used to respond when a box is checked and prompt for input
     * @author Becky 
     *
     */
    public class CheckBoxListener implements ActionListener, InputListener {
        @SuppressWarnings("rawtypes")
		Class associatedClass;
        JCheckBox box;
        CustomInputManager input;
        /**
         * 
         * @param b JCheckBox box that is being listened to
         * @param c class of attribute associated with the box
         */
        @SuppressWarnings("rawtypes")
		public CheckBoxListener(JCheckBox b, Class c)
        {
            associatedClass = c;
            box = b;
        }
        /**
         * Adds the newly created attribute the instance map
         */
        public void setObject(Object attribute)
        {
            Attribute a = (Attribute) attribute;
            attributeInstanceMap.put(box, a);
        }
        /**
         * prompts the user for input when the box is checked
         */
        public void actionPerformed(ActionEvent e)
        {
            
            if (box.isSelected())
            {
                myController.promptForInput(associatedClass, this);
            }
        }
    }
    /**
     * gets the attributes that have been created from the boxes that are checked off
     * @return the list of attribute instances that have been checked off
     */
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
    /**
     * uses reflection to get the constructor that is annotated appropriately
     * @param c class to look for constructor
     * @return the constructor that has been annotated with @editorConstructor
     */
    @SuppressWarnings("rawtypes")
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
