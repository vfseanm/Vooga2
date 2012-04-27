package editor.dialogues;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

import sidescrolling.ConcreteSidescroller;
import sidescrolling.DecoratedSidescroller;
import sidescrolling.Sidescroller;
import sidescrolling.border.BorderSidescroller;
import sidescrolling.forced.ForcedSidescroller;
import sidescrolling.shift.ShiftSidescroller;
import sprite.AnimatedGameSprite;

import editor.EditorController;
import editor.ReflectionUtil;

@SuppressWarnings("serial")
public class GameDialogue extends DialogueBox {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";
    private HashMap<JCheckBox, Class> classMap;

    public GameDialogue(EditorController m) {

        super(m);
        setLayout(new BorderLayout());
        add(makeInputPanel(), BorderLayout.NORTH);

    }

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

    public DialogueBox clone() {
        return new GameDialogue(editorController);
    }

    protected void BoxCompletedAction() {
        BufferedImage image;
        try {
            BaseLoader loader = new BaseLoader(new BaseIO(AnimatedGameSprite.class), Color.PINK);
            image = loader.getImage(myImagePaths.get(myImagePaths.size()-1));
            System.out.println(myImagePaths.get(0));
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
