package editor.input;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;
import editor.InputListener;
import editor.Reflection;
import editor.editorConstructor;
import editor.AttributeSelectionPanel.CheckBoxListener;
import editor.dialogues.DialogueBox;

public class DialogueController {
    private DialogueBox myBox;
    protected ArrayList<AnimatedGameSprite> selectedSprites;
    private InputManager currentInput;
    private InputListener currentOutput;
    
    public DialogueController(DialogueBox box )
    {
     myBox = box; 
     selectedSprites = new ArrayList<AnimatedGameSprite>();
    }
    
    public void setXY(int x, int y)
    {
        if(currentInput!=null)
            currentInput.giveXY(x,y);
    }
    
    public void setRightClickSprite(AnimatedGameSprite sprite)
    {
        if(currentInput!=null)
            currentInput.setRightClickSprite(sprite);
    }
    public void setLeftClickSprite(AnimatedGameSprite sprite)
    {
        if(currentInput!=null)
            currentInput.setLeftClickSprite(sprite);
    }
    
    
    /*
     * This is called when a user clicks on a check-box referencing the given class. 
     * 
     * We now prompt for details: the parameters of this input as defined by the constructor of this class.
     * 
     * This makes a SingleInputManager if the class requested implements InputType. Otherwise, it must be an object with a constructor
     * taking in classes that implement InputType or are one of: boolean, String, int, double
     */
    public void promptForInput(Class c, InputListener toNotify)  
    {
        currentOutput = toNotify;
        
        for(Class cl: c.getInterfaces())
        {
            if (cl.equals(InputType.class))
            {
                try {
                    InputType input = (InputType) c.newInstance();  
                    currentInput = new SingleInputManager(c, this);
                    currentInput.run();
                    return;
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        currentInput = new CustomInputManager(c, this);
        currentInput.run();
    }
    
    public void constructObject(Object[] argList)
    {
        Constructor constructor = Reflection.getAnnotatedConstructor(currentInput.getAssociatedClass());
        try {
            Object object = constructor.newInstance(argList);
            currentOutput.setObject(object);                    // give this object to the attribute selection panel
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
    }
    public void constructObject(Object o)
    {
        currentOutput.setObject(o);
    }
}
