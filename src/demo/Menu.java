package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import editor.EditorController;
import editor.dialogues.AttributeSelectionPanel;
import editor.dialogues.DialogueBox;
import fighter.Fighter;
import attributes.Attribute;


@SuppressWarnings("serial")
public class Menu extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    AttributeSelectionPanel attributePanel;
    AttributeSelectionPanel carryablePanel;

    public Menu(EditorController m) {

        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }

    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {

        ArrayList<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("attributes");
        packagesToSearch.add("fighter.movement");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(800, 325));

        attributePanel = new AttributeSelectionPanel(packagesToSearch, Fighter.getInstance().getAttributes(), dialogueController);

        JPanel panel2 = new JPanel();
        JLabel title1 = new JLabel("Attributes for the Fighter to have:");
        panel2.add(title1);
        panel2.add(attributePanel);
        panel.add(panel2, BorderLayout.PAGE_START);
        JLabel title2 = new JLabel(
                "Carryable attributes for the Fighter to Have:");

        carryablePanel = new AttributeSelectionPanel(packagesToSearch, Fighter.getInstance().getCarryableAttributes(), dialogueController);

        JPanel panel3 = new JPanel();
        panel3.add(title2);
        panel3.add(carryablePanel);
        panel.add(panel3, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(600, 100));

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        subPanel.add(imageButton);

        JButton goButton = new JButton("Create Fighter");
        goButton.addActionListener(new GoAction());
        subPanel.add(goButton);

        panel.add(subPanel, BorderLayout.PAGE_END);

        return panel;

    }


    public DialogueBox clone() {
        return new Menu(editorController);
    }


    protected void BoxCompletedAction() {
        ArrayList<Attribute> attributes = attributePanel
                .getSelectedAttributes();

        ArrayList<Attribute> carryableAttributes = carryablePanel
                .getSelectedAttributes();

        Fighter fighter = Fighter.getInstance();

        if (fighter.getX() == 0 && fighter.getY() == 0) {
            fighter.setLocation(50, 50);
        }

        if(myImagePaths.size()>0)
        {
            fighter.setImageNamesandImages(myImagePaths);
        }
        fighter.clearAttributes();
        for (Attribute attribute : attributes) {
            fighter.addAttribute(attribute);
        }
        fighter.addCarryableAttributes(carryableAttributes);
        editorController.setFighter(fighter);
        setVisible(false);
    }

}
