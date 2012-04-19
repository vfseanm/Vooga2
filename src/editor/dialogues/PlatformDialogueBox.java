
package editor.dialogues;

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

import editor.EditorController;
import editor.Reflection;
import editor.frameworks.Framework;
import editor.frameworks.PlatformFramework;

import platforms.platformtypes.*;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class PlatformDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    private JTextField myName;
    private JTextField myGroup;



    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> classMap;
    
    

    @SuppressWarnings("rawtypes")
    public PlatformDialogueBox(EditorController m)
    {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
        
    }
    
    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        classMap = new HashMap<JCheckBox, Class>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600,800));
        // ArrayList<Class> list = reflection.getBehaviors();
        Reflection reflection = new Reflection();
        for (Class c : reflection.getInstancesOf("platforms", DecoratedPlatform.class))
        {

            
                JLabel label1 = new JLabel(c.getName());
                panel.add(label1);
                JCheckBox box = new JCheckBox();
                panel.add(box);
                classMap.put(box, c);
        }

        JLabel label1 = new JLabel("Platform Name");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName);
        
        JLabel groupLabel = new JLabel("Group:");
        panel.add(groupLabel);

        myGroup = new JTextField(10);
        panel.add(myGroup, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Platform";
        
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }

    public Framework getFramework()
    {
/*        
       List<Class> platformTypes = new ArrayList<Class>();
       
        for (JCheckBox box : classMap.keySet())
        {
            if (box.isSelected())
            {
                platformTypes.add(classMap.get(box));
            }
                
        }*/
        
        AbstractPlatform prototype = new SimplePlatform(0,0,myImagePaths);
        Object[] list = new Object[1];
        list[0] = prototype;
        for(JCheckBox box: classMap.keySet())
        {
            if(box.isSelected())
            {
                Class wrappingClass = classMap.get(box);
                Constructor constructor=  wrappingClass.getConstructors()[0];
                try {
                    prototype = (DecoratedPlatform) constructor.newInstance(list);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                list[0] = prototype;
            }

        }
        prototype.setGroup(myGroup.getText());
        
        
/*        BufferedImage[] s = new BufferedImage[myImages.size()];
        for (int x = 0; x<s.length; x++)
        {
            s[x] = myImages.get(x);
        }*/
        
        //PlatformFramework framework = new PlatformFramework(myImagePaths, platformTypes, myGroup.getText());
        return new Framework("platform", prototype);
        
    }
    
    class GoAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            Framework framework = getFramework();
            System.out.println("framework "+framework);
            myController.addButton(myName.getText(), framework);
            setVisible(false);
        }
    }
   


}