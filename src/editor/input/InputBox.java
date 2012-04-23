package editor.input;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InputBox extends JPanel{
    CustomizedInput myManager;
    
    public InputBox(CustomizedInput inputManager, String message)
    {
        myManager = inputManager;
        
        setLayout(new BorderLayout());
        
        
        JPanel frame = new JPanel();
        JLabel label1 = new JLabel(message);
        label1.setHorizontalAlignment(10);
        frame.add(label1);
        
        JButton goButton = new JButton("OK");
        goButton.addActionListener(new GoAction());
        frame.add(goButton);
        
        add(frame, BorderLayout.NORTH);
        
    }
    
class GoAction implements ActionListener {       
        
        public void actionPerformed(ActionEvent e)
        {
            myManager.finishCurrentInput();
        }
    }
    
//    public void setBox(JFrame frame)
//    {
//        frame.setSize(150, 50);
//
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        
//        int w = frame.getSize().width;
//        int h = frame.getSize().height;
//        int x = (dim.width-w)/2;
//        int y = (dim.height-h)/2;
//         
//        frame.setLocation(x, y);
//    }
}
