package editor.dialogues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

import sidescrolling.ConcreteSidescroller;
import sidescrolling.Sidescroller;
import sidescrolling.border.BorderSidescroller;
import sidescrolling.forced.ForcedSidescroller;
import sidescrolling.shift.ShiftSidescroller;
import sprite.AnimatedGameSprite;

import editor.EditorController;
import editor.ReflectionUtil;
/**
 * Dialogue box used to specify information needed to configure game (background and sidescrolling)
 * @author Becky and Sean
 *
 */
@SuppressWarnings("serial")
public class GameDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    @SuppressWarnings("rawtypes")
	private HashMap<JCheckBox, Class> classMap;
    /**
     * 
     * @param m level editor controller used to make dialogue box
     */
    public GameDialogue(EditorController m) {

        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);

    }
    /**
     * creates panel on which user will choose information to specify about the game
     */
    @SuppressWarnings("rawtypes")
	public JComponent makeSelectionPanel() throws ClassNotFoundException,
            IOException {

        classMap = new HashMap<JCheckBox, Class>();
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 800));
        
        List<Class> sidescrollers = new ArrayList<Class>();
        sidescrollers.addAll(ReflectionUtil.getInstancesOf("sidescrolling.border",
                BorderSidescroller.class));
        sidescrollers.addAll(ReflectionUtil.getInstancesOf("sidescrolling.forced",
                ForcedSidescroller.class));
        sidescrollers.addAll(ReflectionUtil.getInstancesOf("sidescrolling.shift",
                ShiftSidescroller.class));
        
        for (Class c : sidescrollers) {
            JLabel label1 = new JLabel(getClassName(c));
            panel.add(label1);
            JCheckBox box = new JCheckBox();
            panel.add(box);
            classMap.put(box, c);
        }

        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
        panel.add(imageButton);

        String buttonPhrase = "Configure Game";

        JButton goButton = new JButton(buttonPhrase);
        goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }
    /**
     * clones the game dialogue box
     */
    public DialogueBox clone() {
        return new GameDialogue(editorController);
    }
    /**
     * action to be performed when user chooses to configure the game
     */
    protected void BoxCompletedAction() {
        BufferedImage image;
        try {
            BaseLoader loader = new BaseLoader(new BaseIO(AnimatedGameSprite.class), Color.PINK);
            image = loader.getImage(myImagePaths.get(myImagePaths.size()-1));
           
            editorController.setBackground(image, myImagePaths.get(0));
        } catch (Exception exc) {
            System.out.println("There has been a problem importing your image");
        }
        List<String> classNames = new ArrayList<String>();
        Sidescroller prototype = new ConcreteSidescroller();
        for (JCheckBox box : classMap.keySet()) {
            if (box.isSelected()) 
            {
                classNames.add(classMap.get(box).toString());
            }

        }
        prototype = (Sidescroller) ReflectionUtil.wrapObject(classNames, prototype);
        editorController.setSidescrolling(prototype);

        setVisible(false);
    }

}
