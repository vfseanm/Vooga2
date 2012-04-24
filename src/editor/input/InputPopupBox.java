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


public class InputPopupBox extends JPanel{
    InputManager myManager;
    
    public InputPopupBox(InputManager inputManager, String message)
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

}
