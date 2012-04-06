package editor;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;
import enemies.Behavior;

import java.util.HashMap;



@SuppressWarnings("serial")
public class DialogueBox extends JPanel {
    
    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    private JTextField myName;

    private Reflection reflection;
    private EditorModel myModel;
    @SuppressWarnings("rawtypes")
    private HashMap<JCheckBox, Class> behaviorMap;
    private BufferedImage myImage;
    private String myImagePath;


    
    @SuppressWarnings("rawtypes")
    public DialogueBox(EditorModel m) {
        behaviorMap = new HashMap<JCheckBox, Class>();
        myModel = m;
        reflection = new Reflection();
        setLayout(new BorderLayout());
        
        add(makeInputPanel(), BorderLayout.NORTH);
    }


    public BufferedImage getImage() {
        JFileChooser fc = new JFileChooser();
        File file = null;
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }
        myImagePath = null;
        try {
            myImagePath = file.getCanonicalPath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(myImagePath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(myImagePath));
        } catch (IOException e) {
            System.out.println("There has been a problem importing your image");
            //throw something!
        }
        return img;

    }


    private JComponent makeInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        try{
        panel.add(makeDestinationPanel(), BorderLayout.NORTH);
        }
        catch(Exception e)
        {
            System.out.println("Problem with Reflection!");
            e.printStackTrace();
        }
        return panel;
    }


    @SuppressWarnings("rawtypes")
    private JComponent makeDestinationPanel() throws ClassNotFoundException, IOException {
        JPanel panel = new JPanel();
        //ArrayList<Class> list = reflection.getBehaviors();
        for (Class c: reflection.getBehaviors())
                {
            JLabel label1 = new JLabel(c.getName());
            panel.add(label1);
            JCheckBox box = new JCheckBox();
            panel.add(box);
            behaviorMap.put(box, c);
                }

        JLabel label1 = new JLabel("Enemy Name");
        panel.add(label1);

        myName = new JTextField(10);

        panel.add(myName);
        
        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ImageAction());
         panel.add(imageButton);
        
        
        JButton goButton = new JButton("Create Enemy");
       goButton.addActionListener(new GoAction());
        panel.add(goButton);

        return panel;
    }


    private class GoAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Behavior> behaviors = new ArrayList<Behavior>();
            for (JCheckBox box: behaviorMap.keySet())
            {
                if(box.isSelected())
                    try {
                        behaviors.add((Behavior) behaviorMap.get(box).newInstance());
                    } catch (InstantiationException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
            }
            myModel.addEnemy(myImage, myImagePath, myName.getText(), behaviors);
            setVisible(false);
            }
    }

    private class ImageAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            BufferedImage f = getImage();
            myImage = f;
            
        }
    }
    
     
}