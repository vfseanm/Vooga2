package editor;


import java.awt.image.BufferedImage;

import java.util.ArrayList;

import sprite.Behavior;
import sprite.GameSprite;


public class EditorModel {
private ArrayList<GameSprite> sprites;
private ArrayList<EnemyFramework> myEnemyFrameworks;
private ArrayList<BlankButton> blankButtons;
private SetGame myView;

public EditorModel(SetGame view)
{
    myView = view;
    blankButtons = new ArrayList<BlankButton>();
    myEnemyFrameworks = new ArrayList<EnemyFramework>();
    sprites = new ArrayList<GameSprite>();
}

public ArrayList<GameSprite> getSprites()
{
    return sprites;
}

public void addSprite(GameSprite s)
{
    sprites.add(s);
}
public void clearSprites()
{
    sprites.clear();
}
public void addAllSprites(ArrayList<GameSprite> s)
{
    sprites.addAll(s);
}

public void addEnemy(BufferedImage image, String filePath, String name, ArrayList<Behavior> b)
{
    EnemyFramework enemyFrame = new EnemyFramework(image, name, b);
    myEnemyFrameworks.add(enemyFrame);
    System.out.println("Added new Enemy Frame:" + enemyFrame);
    
    Button newButton = new Button(name, 10, 540, 60, 40, image, filePath, "enemy", b);
    myView.addButton(newButton);
}


}
