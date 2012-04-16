package editor;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import attributes.Attribute;


import java.util.HashMap;

@SuppressWarnings("serial")
public class EnemyDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    
    private AttributeSelectionPanel attributePanel;
    private JTextField myName;
    private JTextField myGroup;


    /*private HashMap<JCheckBox, Class> attributeMap;
    
    private HashMap<JCheckBox, List<Object>> attributeInstanceMap;
*/

    @SuppressWarnings("rawtypes")
    public EnemyDialogueBox(EditorController m)
    {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
        
    }


    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
       
        JLabel label1 = new JLabel("Enemy Name:");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName, BorderLayout.SOUTH);
        
        JLabel groupLabel = new JLabel("Group:");
        panel.add(groupLabel);

        myGroup = new JTextField(10);

        panel.add(myGroup, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Create Enemy";
        		
        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);
        panel.add(attributePanel);

        return panel;
    }

    public Framework getFramework()
    {
        
        ArrayList<AttributeCreator> attributes = attributePanel.getSelectedAttributes();
        BufferedImage[] s = new BufferedImage[myImages.size()];
        for (int x = 0; x<s.length; x++)
        {
            s[x] = myImages.get(x);
        }
        EnemyFramework framework = new EnemyFramework(s, myImagePaths, attributes, myGroup.getText());
        System.out.println("framework's attributes" + attributes);
        return framework;
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



