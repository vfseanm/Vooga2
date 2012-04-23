package editor.input;

import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.engine.BaseInput;

public interface InputType {
    public String getPrompt();
    public void setXY(int x, int y);
    public void setRightClickedSprite(AnimatedGameSprite sprite);
    public void setLeftClickedSprite(AnimatedGameSprite sprite);
}
