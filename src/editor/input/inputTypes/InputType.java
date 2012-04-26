package editor.input.inputTypes;

import editor.Framework;
import sprite.AnimatedGameSprite;

public interface InputType {
    
    /*
     * Returns the message the user wants to appear in the prompt box. 
     */
    public String getPrompt();
    
    public void setXY(int x, int y);
    public void setRightClickedSprite(AnimatedGameSprite sprite);
    public void setLeftClickedSprite(AnimatedGameSprite sprite);
    public void setLeftClickedFramework(Framework f);
    public void setRightClickedFramework(Framework f);
}
