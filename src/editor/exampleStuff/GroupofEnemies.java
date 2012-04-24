package editor.exampleStuff;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.engine.BaseInput;

import editor.dialogues.DialogueBox;
import editor.input.InputType;

public class GroupofEnemies implements InputType, Serializable{
private ArrayList<AnimatedGameSprite> mySprites;

public String getPrompt()
{
    return "Click on the enemies that you'd like to add to the group";
}

public ArrayList<AnimatedGameSprite> getEnemies()
{
    System.out.println("returning enemies:" + mySprites);
    return mySprites;
}


public void setXY(int x, int y) {
    return;
}
public void setEnemies(ArrayList<AnimatedGameSprite> sprites)
{
    mySprites = sprites;
}


public void setRightClickedSprite(AnimatedGameSprite sprite) {
    return;
}


public void setLeftClickedSprite(AnimatedGameSprite sprite) {

    System.out.println("clicking on an enemy and giving it to the object!" + sprite);
    if (mySprites==null)
        mySprites = new ArrayList<AnimatedGameSprite>();
    mySprites.add(sprite);
}

}
