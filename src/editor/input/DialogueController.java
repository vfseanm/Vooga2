package editor.input;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;
import editor.Framework;
import editor.InputListener;
import editor.ReflectionUtil;
import editor.dialogues.DialogueBox;
import editor.input.inputTypes.InputType;

public class DialogueController {
    @SuppressWarnings("unused")
	private DialogueBox myBox;
    protected ArrayList<AnimatedGameSprite> selectedSprites;
    private InputManager currentInputManager;
    private InputListener currentOutput;
    
    public DialogueController(DialogueBox box )
    {
     myBox = box; 
     selectedSprites = new ArrayList<AnimatedGameSprite>();
    }
    
    public void setXY(int x, int y)
    {
        if(currentInputManager!=null)
            currentInputManager.giveXY(x,y);
    }
    
    public void setRightClickSprite(AnimatedGameSprite sprite)
    {
        if(currentInputManager!=null)
            currentInputManager.setRightClickSprite(sprite);
    }
    public void setLeftClickSprite(AnimatedGameSprite sprite)
    {
        if(currentInputManager!=null)
            currentInputManager.setLeftClickSprite(sprite);
    }
    public void setLeftClickFramework(Framework f)
    {
        if(currentInputManager!=null)
            currentInputManager.setLeftClickFramework(f);
    }
    public void setRightClickFramework(Framework f)
    {
        if(currentInputManager!=null)
            currentInputManager.setRightClickFramework(f);
    }
    
    
    /*
     * This is called when a user clicks on a check-box referencing the given class. 
     * 
     * We now prompt for details: the parameters of this input as defined by the constructor of this class.
     * 
     * This makes a SingleInputManager if the class requested implements InputType. Otherwise, it must be an object with a constructor
     * taking in classes that implement InputType or are one of: boolean, String, int, double
     */
    @SuppressWarnings({ "rawtypes", "unused" })
	public void promptForInput(Class c, InputListener toNotify)  
    {
        currentOutput = toNotify;
        
        for(Class cl: c.getInterfaces())
        {
            if (cl.equals(InputType.class))
            {
                try {
                    InputType input = (InputType) c.newInstance();  
                    currentInputManager = new SingleInputManager(c, this);
                    currentInputManager.run();
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
        
        currentInputManager = new CustomInputManager(c, this);
        currentInputManager.run();
    }
    
    @SuppressWarnings("rawtypes")
	public void constructObject(Object[] argList)
    {
        Constructor constructor = ReflectionUtil.getAnnotatedConstructor(currentInputManager.getAssociatedClass());
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
