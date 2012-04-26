package editor.input;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class InputPopupBox extends JPanel{
    InputManager myManager;
    
    public InputPopupBox(InputManager inputManager, String message)
    {
        myManager = inputManager;
        
        setLayout(new BorderLayout());
        
        
        JPanel frame = new JPanel();
        frame.setPreferredSize(new Dimension(350, 150));
        JLabel label1 = new JLabel(message);
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
