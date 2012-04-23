package editor.dialogues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import editor.AttributeSelectionPanel;
import editor.EditorController;
import enemies.Enemy;

import attributes.Attribute;

@SuppressWarnings("serial")
public class EditEnemyDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private JTextField myName;
    private JTextField myGroup;

    private Enemy mySprite;
    private int myX;
    private int myY;
    private AttributeSelectionPanel attributePanel;

    public EditEnemyDialogue(EditorController m, Enemy sprite, int x, int y)
    {
        super(m);

        myX = x;
        myY = y;
        mySprite = sprite;

        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);

    }

    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
        List<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("enemies.movement");
        packagesToSearch.add("attributes");
        attributePanel = new AttributeSelectionPanel(packagesToSearch,
                mySprite.getAttributes(), controller);

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

    @Override
    public DialogueBox clone()
    {
        return new EditEnemyDialogue(myController, mySprite, myX, myY);
    }

    @Override
    protected void BoxCompletedAction()
    {
        ArrayList<Attribute> attributes = attributePanel
                .getSelectedAttributes();
        myX = (int) mySprite.getOldX();
        myY = (int) mySprite.getOldY();

        Enemy enemy = new Enemy(myX, myY, myImagePaths);
        System.out.println("old group name: " + mySprite.getGroup());
        enemy.setGroup(mySprite.getGroup());
        System.out.println("group name: " + enemy.getGroup());
        for (Attribute a : attributes)
        {
           enemy.addAttribute(a);
        }

        if (!myGroup.getText().equals(""))
        {
            mySprite.setGroup(myGroup.getText());
        }
        if (mySprite != null)
            myController.replaceSprite(mySprite, enemy);
       

        setVisible(false);

    }

}