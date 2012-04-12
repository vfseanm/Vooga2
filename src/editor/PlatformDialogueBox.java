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

import platforms.AbstractPlatform;
import platforms.DecoratedPlatform;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class PlatformDialogueBox extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;

    private Reflection reflection;
    private EditorController myModel;
    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> classMap;
    
    private BufferedImage myImage;
    private String myImagePath;
    private String myType;

    @SuppressWarnings("rawtypes")
    public PlatformDialogueBox(EditorController m, String type)
    {
        myType = type;
        classMap = new HashMap<JCheckBox, Class>();
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
        // ArrayList<Class> list = reflection.getBehaviors();
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

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Platform";
        
                
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }

    private class GoAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            List<Class> platformTypes = new ArrayList<Class>();
            for (JCheckBox box : classMap.keySet())
            {
                if (box.isSelected())
                {
                    platformTypes.add(classMap.get(box));
                }
                    
            }
            BufferedImage[] s = new BufferedImage[1];
            s[0] = myImage;
            List<String> imagePaths = new ArrayList<String>();
            imagePaths.add(myImagePath);
            
            PlatformFramework framework = new PlatformFramework(s, imagePaths, platformTypes);
            myModel.addButton(myName.getText(), framework, myType);
            setVisible(false);
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