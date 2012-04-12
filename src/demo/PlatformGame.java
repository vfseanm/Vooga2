package demo;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import sprite.AnimatedGameSprite;   

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

import editor.Level;
import enemies.Enemy;

public abstract  class PlatformGame extends Game {
    
    protected List<AnimatedGameSprite> sprites;
    private Level level;
    
    PlatformGame()
    {
        sprites = new ArrayList<AnimatedGameSprite>();
    }
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
