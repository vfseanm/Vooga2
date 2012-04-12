package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import sprite.AnimatedGameSprite;

import com.golden.gamedev.Game;

import editor.Level;

public abstract class PlatformGame extends Game {
    
    List<AnimatedGameSprite> sprites;
    Level level;
    
    public void loadLevel(String filename)
    {
        File file = new File(filename);
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try
        {
          fis = new FileInputStream(file);
          in = new ObjectInputStream(fis);
          level = (Level)in.readObject();
          in.close();
        }
        catch(IOException ex)
        {
          ex.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }

        sprites = level.getSprites();
    }

}
