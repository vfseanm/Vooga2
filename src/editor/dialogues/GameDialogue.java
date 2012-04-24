package editor.dialogues;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.*;
import sidescrolling.ConcreteSidescroller;
import sidescrolling.DecoratedSidescroller;
import sidescrolling.Sidescroller;
import sidescrolling.border.BorderSidescroller;
import sidescrolling.forced.ForcedSidescroller;
import sidescrolling.shift.ShiftSidescroller;

import editor.EditorController;
import editor.Reflection;

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
        Reflection reflection = new Reflection();
        for (Class c : reflection.getInstancesOf("sidescrolling.border",
                BorderSidescroller.class)) {
            JLabel label1 = new JLabel(getClassName(c));
            panel.add(label1);
            JCheckBox box = new JCheckBox();
            panel.add(box);
            classMap.put(box, c);
        }
        for (Class c : reflection.getInstancesOf("sidescrolling.forced",
                ForcedSidescroller.class)) {
            JLabel label1 = new JLabel(getClassName(c));
            panel.add(label1);
            JCheckBox box = new JCheckBox();
            panel.add(box);
            classMap.put(box, c);
        }
        for (Class c : reflection.getInstancesOf("sidescrolling.shift",
                ShiftSidescroller.class)) {

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
            image = ImageIO.read(new File(
                    myImagePaths.get(myImagePaths.size() - 1)));
            System.out.println(myImagePaths.get(0));
            editorController.setBackground(image, myImagePaths.get(0));
        } catch (Exception exc) {
            System.out.println("There has been a problem importing your image");
        }

        Sidescroller prototype = new ConcreteSidescroller();
        Object[] list = new Object[1];
        list[0] = prototype;
        for (JCheckBox box : classMap.keySet()) {
            if (box.isSelected()) {
                Class wrappingClass = classMap.get(box);
                System.out.println("wrapping " + prototype + "with "
                        + "wrappingClass");
                Constructor constructor = wrappingClass.getConstructors()[0];
                try {
                    prototype = (DecoratedSidescroller) constructor
                            .newInstance(list);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                list[0] = prototype;
            }

        }
        editorController.setSidescrolling(prototype);

        setVisible(false);
    }

}
