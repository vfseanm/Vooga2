package editor;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import platforms.platformtypes.*;

import sprite.AnimatedGameSprite;

import attributes.Attribute;

import com.golden.gamedev.object.Sprite;

import editor.ButtonDialogueBox.GoAction;
import editor.dialogues.DialogueBox;
import editor.dialogues.DialogueBox.ImageAction;
import enemies.Enemy;

public class EditPlatformDialogue extends DialogueBox{
    
    private HashMap<JCheckBox, Class> classMap;
    private AnimatedGameSprite mySprite;
    private int myX;
    private int myY;
    private BufferedImage myImage;
    private String myImagePath;
    public EditPlatformDialogue(EditorController m, AbstractPlatform sprite, int x, int y)
    {
        super(m);
        mySprite = sprite;
        myX = x;
        myY = y;
        myImage = mySprite.getImage();
        classMap = new HashMap<JCheckBox, Class>();
        
    }

    @Override
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        classMap = new HashMap<JCheckBox, Class>();
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
    
 private class GoAction implements ActionListener {
       
        
        public void actionPerformed(ActionEvent e)
        {
            
            BufferedImage[] s = new BufferedImage[1];
            s[0] = myImage;
            ArrayList<String> imagePaths = new ArrayList<String>();
            imagePaths.add(myImagePath);
            //EnemyFramework framework = new EnemyFramework(s, imagePaths, attributes);
            if(mySprite!=null)
            {
                myX = (int) mySprite.getOldX();
                myY = (int) mySprite.getOldY();
            }
            Enemy enemy = new Enemy(s, myX,
                    myY,
                    imagePaths);
            for(AttributeCreator a: attributes)
            {
                Attribute attribute = a.createAttribute();
                enemy.addAttribute(attribute);
            }  
            for (Attribute oldAttribute: oldAttributes)
            {
                enemy.addAttribute(oldAttribute);
            }
            if(mySprite!=null)
                myModel.replaceSprite(mySprite, enemy);
            List<Object> parameters = new ArrayList<Object>();
            parameters.add(s);
            parameters.add(imagePaths);
            parameters.add(attributes);
            if(myFramework!=null)
                myFramework.updateSprites(parameters);
            
            setVisible(false);
        }
    }

}
