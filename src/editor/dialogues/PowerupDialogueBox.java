package editor.dialogues;

import java.io.IOException;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import bonusobjects.BonusObject;
import editor.EditorController;
import editor.Framework;
import attributes.Attribute;
/**
 * Dialogue box to specify information about how new powerup framework can be created
 * @author Becky and Sean
 *
 */
@SuppressWarnings("serial")
public class PowerupDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    private AttributeSelectionPanel powerupAttributePanel;
    private AttributeSelectionPanel attributesToGivePanel;
    private JTextField myName;
    private JTextField myGroup;
    /**
     * 
     * @param m level editor controller
     */
    public PowerupDialogueBox(EditorController m) {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);

    }
    /**
     * selection panel on which user can specify information about the powerup
     */
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {
        ArrayList<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("attributes.enemyattributes");
        packagesToSearch.add("attributes.sharedattributes");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(800, 375));
        powerupAttributePanel = new AttributeSelectionPanel(packagesToSearch,
                dialogueController);
        packagesToSearch.clear();
        
        packagesToSearch.add("attributes.fighterattributes");
        packagesToSearch.add("attributes.sharedattributes");
        JPanel panel2 = new JPanel();
        JLabel title1 = new JLabel("Attributes for the Power-Up to have:");
        panel2.add(title1);
        panel2.add(powerupAttributePanel);
        panel.add(panel2, BorderLayout.PAGE_START);
        JLabel title2 = new JLabel("Attributes for the Power-Up to Give:");
        attributesToGivePanel = new AttributeSelectionPanel(packagesToSearch,
                dialogueController);
        JPanel panel3 = new JPanel();
        panel3.add(title2);
        panel3.add(attributesToGivePanel);
        panel.add(panel3, BorderLayout.CENTER);

        JPanel subPanel = new JPanel();
        subPanel.setPreferredSize(new Dimension(600, 150));

        JLabel label1 = new JLabel("Power-Up Name");
        subPanel.add(label1);

        myName = new JTextField(10);

        subPanel.add(myName);

        JLabel groupLabel = new JLabel("Collision Group:");
        subPanel.add(groupLabel);

        myGroup = new JTextField(10);
        subPanel.add(myGroup, BorderLayout.SOUTH);

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        subPanel.add(imageButton);

        JButton goButton = new JButton("Create Power-Up");
        goButton.addActionListener(new GoAction());
        subPanel.add(goButton);

        panel.add(subPanel, BorderLayout.PAGE_END);

        return panel;
    }

    /**
     * creates the framework from the information specified on the selection panel
     * @return the framework created
     * @throws RuntimeException
     */
    public Framework getFramework() throws RuntimeException {
        if(myImagePaths==null)
        {
            throw new RuntimeException();
        }
        BonusObject prototype = new BonusObject(0, 0, myImagePaths);
        for (Attribute attribute : powerupAttributePanel
                .getSelectedAttributes()) {
            prototype.addAttribute(attribute);
        }
        for (Attribute attributeToGive : attributesToGivePanel
                .getSelectedAttributes()) {
            prototype.addAttributeToOffer(attributeToGive);
        }
        if(myGroup.getText().length()!=0)
        {
            prototype.setGroup(myGroup.getText());
        }
        Framework framework = new Framework(myName.getText(), "Power-Up",
                prototype);
        return framework;

    }
    /**
     * clones the powerup dialogue box
     */
    @Override
    public DialogueBox clone() {
        return new PowerupDialogueBox(editorController);
    }
    /**
     * creates new framework and button in the editor
     */
    @Override
    protected void BoxCompletedAction() {
        try{
            Framework framework = getFramework();
            editorController.addFrameworkAndButton(myName.getText(), framework);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "You must select an image" );
                return;
            }
            setVisible(false);

    }

}