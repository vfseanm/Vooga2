package editor.dialogues;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

import editor.EditorController;
import editor.Framework;
import editor.ReflectionUtil;
import platforms.platformtypes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public class PlatformDialogueBox extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    private JTextField myName;
    private JTextField myGroup;

    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> classMap;

    public PlatformDialogueBox(EditorController m) {
        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);

    }

    @SuppressWarnings("rawtypes")
    public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {
        classMap = new HashMap<JCheckBox, Class>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
        for (Class c : ReflectionUtil.getInstancesOf("platforms",
                DecoratedPlatform.class)) {

            JLabel label1 = new JLabel(getClassName(c));
            panel.add(label1);
            JCheckBox box = new JCheckBox();
            panel.add(box);
            classMap.put(box, c);
        }

        JLabel label1 = new JLabel("Platform Name:");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName);

        JLabel groupLabel = new JLabel("Collision Group:");
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

    public Framework getFramework() throws RuntimeException {
        if(myImagePaths==null)
        {
            throw new RuntimeException();
        }
        AbstractPlatform prototype = new SimplePlatform(0, 0, myImagePaths);
        List<String> classNames = new ArrayList<String>();
        for (JCheckBox box : classMap.keySet()) {
            if (box.isSelected()) 
            {
                classNames.add(classMap.get(box).toString());
            }

        }
        prototype = (AbstractPlatform) ReflectionUtil.wrapObject(classNames, prototype);
        prototype.setGroup(myGroup.getText());
        return new Framework(myName.getText(), "platform", prototype);

    }

    public DialogueBox clone() {
        return new PlatformDialogueBox(editorController);
    }

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