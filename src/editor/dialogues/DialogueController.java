package editor.dialogues;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;
import editor.Reflection;
import editor.editorConstructor;
import editor.AttributeSelectionPanel.CheckBoxListener;
import editor.input.CustomizedInput;

public class DialogueController {
    private DialogueBox myBox;
    protected ArrayList<AnimatedGameSprite> selectedSprites;
    private CustomizedInput currentInput;
    private CheckBoxListener currentOutput;
    
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
        currentInput.setRightClickSprite(sprite);
    }
    public void setLeftClickSprite(AnimatedGameSprite sprite)
    {
        currentInput.setLeftClickSprite(sprite);
    }
    
    
    /*
     * This is called when a user clicks on a check-box referencing the given class. 
     * 
     * We now prompt for details: the parameters of this input as defined by the constructor of this class.
     */
    public void promptForInput(Class c, CheckBoxListener toNotify)  
    {
        currentOutput = toNotify;
        currentInput = new CustomizedInput(c, this);
        currentInput.run();
        
        Constructor constructor = Reflection.getAnnotatedConstructor(c);
        Annotation a = constructor.getAnnotation(editorConstructor.class);
        String[] paramNames = ((editorConstructor) a).parameterNames();
        Class[] paramTypes = constructor.getParameterTypes();
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
    
}
