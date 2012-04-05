package editor;

import java.awt.Color;
import java.util.List;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

import platforms.AbstractPlatform;

import sprite.Enemy;

public class Level {
    
    private String name;
    
    private List<Enemy> enemies;
    
    private List<AbstractPlatform> platforms;
    
    public void setUp()
    {
        BaseLoader loader = new BaseLoader(new BaseIO(this.getClass()), Color.PINK);
        for(Enemy e: enemies)
        {
            e.setImage(loader.getImage(e.getImageName()));
        }
        
        for(AbstractPlatform p: platforms)
        {
            p.setImage(loader.getImage(p.getImageName()));
        }
    }
    
}
