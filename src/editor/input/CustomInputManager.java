package editor.input;

import java.awt.Dimension;

import java.awt.HeadlessException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import editor.ReflectionUtil;
import editor.editorConstructor;
import editor.input.inputTypes.InputType;

public class CustomInputManager extends InputManager{


    private JFrame frame;
    
    private Object[] argList;
    private int currentArgumentCounter;
    
    @SuppressWarnings("rawtypes")
	private Class[] paramTypes;
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public CustomInputManager(Class c, DialogueController controller)
    {
        myController = controller;
        myClass = c;
        
        Constructor constructor = ReflectionUtil.getAnnotatedConstructor(myClass);
        Annotation a = constructor.getAnnotation(editorConstructor.class);
        String[] paramNames = ((editorConstructor) a).parameterNames();
        paramTypes = constructor.getParameterTypes();
        
        if(paramTypes.length ==0)
        {
            argList = null;
        }
        else
        {
            argList = new Object[paramNames.length];
        }
        currentArgumentCounter = 0;
        
    }

    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void run()
    {
            Constructor constructor = ReflectionUtil.getAnnotatedConstructor(myClass);
            Annotation a = constructor.getAnnotation(editorConstructor.class);
            String[] paramNames = ((editorConstructor) a).parameterNames();
            if(paramTypes.length ==0)
            {
                checkAndRun();
                return;
            }
            
                    currentInput = null;
                    if(!(paramTypes[currentArgumentCounter].equals(int.class) ||  paramTypes[currentArgumentCounter].equals(String.class) || paramTypes[currentArgumentCounter].equals(double.class) ||paramTypes[currentArgumentCounter].toString().equals("boolean") ))
                    {
                        try {
                            currentInput = (InputType) paramTypes[currentArgumentCounter].newInstance();
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
                    else{
                    // it is one of the basic 4 inputs
                    String selectedValue = JOptionPane
                            .showInputDialog("What would you like the "
                                    + paramNames[currentArgumentCounter] + " to be?");

                    if (paramTypes[currentArgumentCounter].equals(int.class))
                    {
                        try{
                        argList[currentArgumentCounter] = Integer.parseInt(selectedValue);
                        checkAndRun();
                        }
                        catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Bad input" );
                            run();
                            return;
                        }
                    }
                    if (paramTypes[currentArgumentCounter].equals(String.class))
                    {
                        argList[currentArgumentCounter] = selectedValue;
                        checkAndRun();
                    }
                    if (paramTypes[currentArgumentCounter].equals(double.class))
                    {
                        try{
                            argList[currentArgumentCounter] = Double.parseDouble(selectedValue);
                            checkAndRun();
                            }
                            catch(NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "Bad input" );
                                run();
                                return;
                            }
                    }
                    if (paramTypes[currentArgumentCounter].toString().equals("boolean"))
                    {
                        if(!(selectedValue.equalsIgnoreCase("true") || selectedValue.equalsIgnoreCase("false")) )
                        {
                            JOptionPane.showMessageDialog(null, "Bad input" );
                            run();
                            return;
                        }
                        else{
                            argList[currentArgumentCounter] = Boolean.parseBoolean(selectedValue);
                            checkAndRun();
                            
                            }
                    }
                    }
                
    }
    
    public void finishCurrentInput()
    {
        frame.setVisible(false);
        argList[currentArgumentCounter] = currentInput;
        checkAndRun();
        
    }
    private void checkAndRun()
    {
        if(paramTypes.length ==0)
        {
            myController.constructObject(argList);
            return;
        }
        
        if(currentArgumentCounter== (argList.length-1)) { // the last argument has been created
            myController.constructObject(argList);
            return;
        }
        currentArgumentCounter+=1;
        run();
    }

    
}
