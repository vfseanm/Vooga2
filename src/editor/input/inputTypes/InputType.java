package editor.input.inputTypes;

import editor.Framework;
import sprite.AnimatedGameSprite;

public interface InputType {
    public String getPrompt();
    public void setXY(int x, int y);
    public void setRightClickedSprite(AnimatedGameSprite sprite);
    public void setLeftClickedSprite(AnimatedGameSprite sprite);
    public void setLeftClickedFramework(Framework f);
    public void setRightClickedFramework(Framework f);
}
