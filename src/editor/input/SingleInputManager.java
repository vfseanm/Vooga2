package editor.input;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

import editor.input.inputTypes.InputType;


public class SingleInputManager extends InputManager{

    private JFrame frame;
    
    
    @SuppressWarnings("rawtypes")
	public SingleInputManager(Class c, DialogueController controller)
    {
        myController = controller;
        myClass = c;

    }
        
    public void run()
    {
       currentInput = null;
                   
                        try {
                            currentInput = (InputType) myClass.newInstance();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            frame = new JFrame("");
                            Dimension d = new Dimension(350, 150);
                            frame.setPreferredSize(d);
                            frame.getContentPane().add(new InputPopupBox(this, currentInput.getPrompt()));
                            frame.pack();
                            frame.setVisible(true);
                            
                            
                        } catch (SecurityException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (HeadlessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } 
                        
                    }
                    
    
    public void finishCurrentInput()
    {
        frame.setVisible(false);
        myController.constructObject(currentInput);
        
    }
    
}
