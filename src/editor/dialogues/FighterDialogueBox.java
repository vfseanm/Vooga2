package editor.dialogues;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import editor.EditorController;
import fighter.Fighter;
import attributes.Attribute;

/**
 * Dialogue box used to create the fighter in the level
 * @author Becky and Sean
 *
 */
@SuppressWarnings("serial")
public class FighterDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";

    AttributeSelectionPanel attributePanel;
    AttributeSelectionPanel carryablePanel;
    /**
     * 
     * @param m controller that the dialogue box is called by 
     */
    public FighterDialogueBox(EditorController m) {

        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);
    }
    /**
     * creates the selection panel for the user to specify the information needed to create the fighter
     */
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {

        ArrayList<String> packagesToSearch = new ArrayList<String>();
        packagesToSearch.add("attributes.fighterattributes");
        packagesToSearch.add("attributes.sharedattributes");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(800, 325));

        attributePanel = new AttributeSelectionPanel(packagesToSearch, Fighter
                .getInstance().getAttributes(), dialogueController);

        JPanel panel2 = new JPanel();
        JLabel title1 = new JLabel("Attributes for the Fighter to have:");
        panel2.add(title1);
        panel2.add(attributePanel);
        panel.add(panel2, BorderLayout.PAGE_START);
        JLabel title2 = new JLabel(
                "Carryable attributes for the Fighter to Have:");

        carryablePanel = new AttributeSelectionPanel(packagesToSearch, Fighter
                .getInstance().getCarryableAttributes(), dialogueController);

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

    /**
     * clones the fighter dialogue box
     */
    public DialogueBox clone() {
        return new FighterDialogueBox(editorController);
    }

    /**
     * creates the fighter from the information specified in the selection panel
     */
    protected void BoxCompletedAction() {
        ArrayList<Attribute> attributes = attributePanel
                .getSelectedAttributes();

        ArrayList<Attribute> carryableAttributes = carryablePanel
                .getSelectedAttributes();

        Fighter fighter = Fighter.getInstance();

        if (fighter.getX() == 0 && fighter.getY() == 0) {
            fighter.setLocation(100, 100);
        }
        try{
            
            if(fighter.getImageNames()==null)
            {
                checkErrors();
                fighter.setImageNamesandImages(myImagePaths);
            }
            else
            {
                if(myImagePaths.size()!=0)
                {
                    fighter.setImageNamesandImages(myImagePaths);
                }
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "You must select an image" );
            return;
        }
        fighter.clearAttributes();
        for (Attribute attribute : attributes) {
            fighter.addAttribute(attribute);
        }
        fighter.addCarryableAttributes(carryableAttributes);
        editorController.setFighter(fighter);
        setVisible(false);

    }
    /**
     * checks to ensure that files have been selected
     * @throws RuntimeException
     */
    private void checkErrors() throws RuntimeException
    {
       
        if(myImagePaths.size()==0)
        {
        throw new RuntimeException();
    }
}

}
