package editor.dialogues;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;

import editor.EditorController;


@SuppressWarnings("serial")
public abstract class DialogueBox extends JPanel {

    public static final Dimension SIZE = new Dimension(800, 600);
    public static final String BLANK = " ";


    protected EditorController myController;
    //protected ArrayList<BufferedImage> myImages;
    protected ArrayList<String> myImagePaths;
    
    
    public DialogueBox(EditorController m)
    {
        //myImages = new ArrayList<BufferedImage>();
        myImagePaths = new ArrayList<String>();
        myController = m;        
    }
    
    
    
    public abstract JComponent makeSelectionPanel() throws ClassNotFoundException, IOException;

    public void getImage()
    {
        JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        File file = null;
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fc.getSelectedFile();
        }
        try
        {
            myImagePaths.add(file.getCanonicalPath());
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
        /*BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(myImagePaths.get(myImagePaths.size()-1)));
        } catch (IOException e)
        {
            System.out.println("There has been a problem importing your image");
        }
        return img;*/

    }

    protected JComponent makeInputPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());

        try
        {
            panel.add(makeSelectionPanel(), BorderLayout.NORTH);
            
        } catch (Exception e)
        {
            System.out.println("Problem with Reflection!");
            e.printStackTrace();
        }
        return panel;
    }

    protected class ImageAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            getImage();
            

        }
    }
    
}