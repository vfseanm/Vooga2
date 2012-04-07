package editor;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

import platforms.AbstractPlatform;
import powerUps.PowerUp;

import enemies.Enemy;


import sprite.Behavior;


import sprite.GameSprite;


public class EditorModel {


private EditorView myView;

private Level myLevel;

public EditorModel(EditorView view)
{
    myView = view;
    

}



public void addEnemy(Enemy s)
{
    myLevel.addEnemy(s);
}

public void addPlatform(AbstractPlatform s)
{
    myLevel.addPlatform(s);
}

public void addPowerUp(PowerUp s)
{
    myLevel.addPowerUp(s);
}

public void clearSprites()
{
    myLevel.clear();
}


public void addAllSprites(ArrayList<GameSprite> s)
{
    sprites.addAll(s);
}

public List<GameSprite> getAllSprites()
{
    return myLevel.getSprites();
}



public void addEnemy(BufferedImage image, String filePath, String name, ArrayList<Behavior> b)
{
    Button newButton = new Button(name, 10, 540, 60, 40, image, filePath, "enemy", b);
    myView.addButton(newButton);
}


}
