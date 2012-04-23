package editor.input;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import sprite.AnimatedGameSprite;

import editor.AttributeSelectionPanel;
import editor.Reflection;
import editor.editorConstructor;
import editor.AttributeSelectionPanel.CheckBoxListener;
import editor.dialogues.DialogueController;

import attributes.Attribute;

public class CustomInputManager {

    private Class myClass;
    private JFrame frame;
    private CheckBoxListener myPanel;
    private InputType currentInput;
    private DialogueController myController;
    
    private Object[] argList;
    private int currentArgumentCounter;
    
    private Class[] paramTypes;
    
    
    public CustomInputManager(Class c, DialogueController controller)
    {
        myController = controller;
        myClass = c;
        
        Constructor constructor = Reflection.getAnnotatedConstructor(myClass);
        Annotation a = constructor.getAnnotation(editorConstructor.class);
        String[] paramNames = ((editorConstructor) a).parameterNames();
        paramTypes = constructor.getParameterTypes();
        
            argList = new Object[paramNames.length];
            currentArgumentCounter = 0;
        
    }
    public void setRightClickSprite(AnimatedGameSprite sprite)
    {
        currentInput.setRightClickedSprite(sprite);
    }
    public void setLeftClickSprite(AnimatedGameSprite sprite)
    {
        currentInput.setLeftClickedSprite(sprite);
    }
    
    public void giveXY(int x, int y)
    {
        System.out.println("calling giveXY in currentInput");
        if(currentInput!=null)
        {
            System.out.println("giving x and y to current input" + x + " and " + y);
            currentInput.setXY(x, y);
        }
    }
    
    public void run()
    {
            Constructor constructor = Reflection.getAnnotatedConstructor(myClass);
            Annotation a = constructor.getAnnotation(editorConstructor.class);
            String[] paramNames = ((editorConstructor) a).parameterNames();
            
            
                    currentInput = null;
                    if(!(paramTypes[currentArgumentCounter].equals(int.class) || paramTypes[currentArgumentCounter].equals(int.class) || paramTypes[currentArgumentCounter].equals(String.class) || paramTypes[currentArgumentCounter].equals(double.class) ||paramTypes[currentArgumentCounter].toString().equals("boolean") ))
                    {
                        try {
                            currentInput = (InputType) paramTypes[currentArgumentCounter].newInstance();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println("prompting for" + currentInput);
                        try {
                            frame = new JFrame("");
                            frame = new JFrame("Edit Enemies");
                            Dimension d = new Dimension(200, 100);
                            frame.setPreferredSize(d);
                            frame.getContentPane().add(new InputPopopBox(this, currentInput.getPrompt()));
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
                    else{
                    // it is one of the basic 4 inputs
                    String selectedValue = JOptionPane
                            .showInputDialog("What would you like the "
                                    + paramNames[currentArgumentCounter] + " to be?");

                    if (paramTypes[currentArgumentCounter].equals(int.class))
                    {
                        argList[currentArgumentCounter] = Integer.parseInt(selectedValue);
                        checkAndRun();
                    }
                    if (paramTypes[currentArgumentCounter].equals(String.class))
                    {
                        argList[currentArgumentCounter] = selectedValue;
                        checkAndRun();
                    }
                    if (paramTypes[currentArgumentCounter].equals(double.class))
                    {
                        argList[currentArgumentCounter] = Double.parseDouble(selectedValue);
                        checkAndRun();
                    }
                    if (paramTypes[currentArgumentCounter].toString().equals("boolean"))
                    {
                        argList[currentArgumentCounter] = Boolean.parseBoolean(selectedValue);
                        checkAndRun();
                    }
                    }
                
    }
    
    public void finishCurrentInput()
    {
        frame.setVisible(false);
        System.out.println("input just entered:" + currentInput);
        argList[currentArgumentCounter] = currentInput;
        checkAndRun();
        
    }
    private void checkAndRun()
    {
        System.out.println("counter" + currentArgumentCounter);
        System.out.println("# of arguments" + argList.length);
        if(currentArgumentCounter== (argList.length-1)) { // the last argument has been created
            myController.constructObject(argList);
            return;
        }
        currentArgumentCounter+=1;
        run();
    }
    public Class getAssociatedClass()
    {
        return myClass;
    }
    
}
