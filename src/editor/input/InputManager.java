package editor.input;

import editor.Framework;
import editor.input.inputTypes.InputType;
import sprite.AnimatedGameSprite;

public abstract class InputManager {
    @SuppressWarnings("rawtypes")
	protected Class myClass;
    protected InputType currentInput;
    protected DialogueController myController;

    
    public abstract void run();
    public abstract void finishCurrentInput();
    
    public void setRightClickSprite(AnimatedGameSprite sprite)
    {
        currentInput.setRightClickedSprite(sprite);
    }
    public void setLeftClickSprite(AnimatedGameSprite sprite)
    {
        currentInput.setLeftClickedSprite(sprite);
    }
    public void setLeftClickFramework(Framework f)
    {
        currentInput.setLeftClickedFramework(f);
    }
    public void setRightClickFramework(Framework f)
    {
        currentInput.setRightClickedFramework(f);
    }
    
    public void giveXY(int x, int y)
    {
        if(currentInput!=null)
        {
            
            currentInput.setXY(x, y);
        }
    }
    
    @SuppressWarnings("rawtypes")
	public Class getAssociatedClass()
    {
        return myClass;
    }
}
